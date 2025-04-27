package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_ThongKe;
import iuh.fit.models.MonthlyRevenueInfo;
import iuh.fit.models.ProductInfo;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

public class DAOImpl_ThongKe extends UnicastRemoteObject implements DAO_ThongKe {
    private EntityManager em;

    public DAOImpl_ThongKe(EntityManager em) throws RemoteException{
        this.em = em;
    }

    public DAOImpl_ThongKe()throws RemoteException{
        em= AppUtil.getEntityManager();
    }

    @Override
    public List<ProductInfo> getTopSellingProducts() throws RemoteException {
        List<ProductInfo> topProducts = new ArrayList<>();
        try {
            // Lấy ngày bắt đầu và kết thúc của tháng hiện tại
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date startDate = calendar.getTime();
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            Date endDate = calendar.getTime();

            // Tạo câu truy vấn SQL
            String sql = "SELECT od.sanPham, SUM(od.soLuong) AS total_quantity "
                    + "FROM ChiTietHoaDon od "
                    + "JOIN HoaDon o ON od.hoaDon = o.maHoaDon "
                    + "WHERE o.ngayLap BETWEEN ? AND ? "
                    + "GROUP BY od.sanPham "
                    + "ORDER BY total_quantity DESC ";

            // Chuẩn bị và thực thi câu truy vấn
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, new java.sql.Date(startDate.getTime()));
            query.setParameter(2, new java.sql.Date(endDate.getTime()));
            query.setMaxResults(5);

            List<Object[]> results = query.getResultList();
            for (Object[] result : results) {
                String productId = (String) result[0];
                int totalQuantity = ((Number) result[1]).intValue();
                topProducts.add(new ProductInfo(productId, totalQuantity));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi lấy danh sách sản phẩm bán chạy", e);
        }
        return topProducts;
    }

    @Override
    public double tongDoanhThu(int currentYear) throws RemoteException {
        double totalRevenue = 0.0;
        try {
            LocalDate startDate = LocalDate.of(currentYear, 1, 1);
            LocalDate endDate = LocalDate.of(currentYear, 12, 31);

            String jpql = "SELECT SUM(hd.tongTien) FROM HoaDon hd " +
                    "WHERE YEAR(hd.ngayLap) = :year " +
                    "GROUP BY MONTH(hd.ngayLap)";

            TypedQuery<Double> query = em.createQuery(jpql, Double.class);
            query.setParameter("year", currentYear);

            // Lấy danh sách tổng doanh thu của từng tháng
            List<Double> monthlyRevenues = query.getResultList();

            // Tính tổng doanh thu của cả năm
            for (Double revenue : monthlyRevenues) {
                totalRevenue += revenue != null ? revenue : 0.0;
            }

            System.out.println("Total Revenue for the year " + currentYear + ": " + totalRevenue);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi tính tổng doanh thu năm " + currentYear, e);
        }
        return totalRevenue;
    }

    @Override
    public double tongHoanTra(int currentYear) throws RemoteException {
        double totalRefund = 0.0;
        try {
            LocalDate startDate = LocalDate.of(currentYear, 1, 1);
            LocalDate endDate = LocalDate.of(currentYear, 12, 31);

            String jpql = "SELECT SUM(hh.tienHoanTra) FROM HoaDonHoanTra hh " +
                    "WHERE YEAR(hh.ngayHoan) = :year " +
                    "GROUP BY MONTH(hh.ngayHoan)";

            TypedQuery<Double> query = em.createQuery(jpql, Double.class);
            query.setParameter("year", currentYear);

            // Lấy danh sách tổng hoàn trả của từng tháng
            List<Double> monthlyRefunds = query.getResultList();

            // Tính tổng hoàn trả của cả năm
            for (Double refund : monthlyRefunds) {
                totalRefund += refund != null ? refund : 0.0;
            }

            System.out.println("Total Refund for the year " + currentYear + ": " + totalRefund);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi tính tổng hoàn trả năm " + currentYear, e);
        }
        return totalRefund;
    }

    @Override
    public List<MonthlyRevenueInfo> tongTienTheoThang() throws RemoteException {
        List<MonthlyRevenueInfo> monthlyRevenueList = new ArrayList<>();
        // Tạo danh sách đầy đủ với 12 tháng
        for (int month = 1; month <= 12; month++) {
            monthlyRevenueList.add(new MonthlyRevenueInfo(month, 0.0));
        }
        try {
            LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
            LocalDate endDate = LocalDate.of(LocalDate.now().getYear(), 12, 31);

            String jpql = "SELECT MONTH(hd.ngayLap), COALESCE(SUM(hd.tongTien), 0.0) " +
                    "FROM HoaDon hd " +
                    "WHERE YEAR(hd.ngayLap) = :year " +
                    "GROUP BY MONTH(hd.ngayLap)";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("year", LocalDate.now().getYear());

            List<Object[]> results = query.getResultList();

            // Cập nhật doanh thu cho từng tháng trong danh sách
            for (Object[] result : results) {
                int month = (int) result[0];
                double totalRevenue = (double) result[1];
                monthlyRevenueList.get(month - 1).setTotalRevenue(totalRevenue);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi tính tổng tiền theo tháng", e);
        }
        return monthlyRevenueList;
    }

    @Override
    public List<MonthlyRevenueInfo> tienHoanTheoThang() throws RemoteException {
        List<MonthlyRevenueInfo> monthlyRevenueList = new ArrayList<>();
        try {
            LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
            LocalDate endDate = LocalDate.of(LocalDate.now().getYear(), 12, 31);

            String jpql = "SELECT MONTH(hh.ngayHoan), COALESCE(SUM(hh.tienHoanTra), 0.0) " +
                    "FROM HoaDonHoanTra hh " +
                    "WHERE YEAR(hh.ngayHoan) = :year " +
                    "GROUP BY MONTH(hh.ngayHoan)";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("year", LocalDate.now().getYear());

            List<Object[]> results = query.getResultList();

            // Tạo danh sách đầy đủ với 12 tháng
            for (int month = 1; month <= 12; month++) {
                monthlyRevenueList.add(new MonthlyRevenueInfo(month, 0.0));
            }

            // Cập nhật doanh thu hoàn trả cho từng tháng trong danh sách
            for (Object[] result : results) {
                int month = (int) result[0];
                double totalRevenue = (double) result[1];
                monthlyRevenueList.get(month - 1).setTotalRevenue(totalRevenue);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi tính tiền hoàn trả theo tháng", e);
        }
        return monthlyRevenueList;
    }

    @Override
    public double thongKeDoanhThu(Date ngayBatDau, Date ngayKetThuc) throws RemoteException {
        double tongDoanhThu = 0.0;
        try {
            String jpql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE hd.ngayLap BETWEEN :ngayBatDau AND :ngayKetThuc";

            TypedQuery<Double> query = em.createQuery(jpql, Double.class);
            query.setParameter("ngayBatDau", ngayBatDau);
            query.setParameter("ngayKetThuc", ngayKetThuc);

            // Lấy tổng doanh thu từ kết quả truy vấn
            Double sumTongTien = query.getSingleResult();
            if (sumTongTien != null) {
                tongDoanhThu = sumTongTien;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi thống kê doanh thu từ " + ngayBatDau + " đến " + ngayKetThuc, e);
        }
        return tongDoanhThu;
    }

    @Override
    public int thongKeSoHoaDon(Date ngayBatDau, Date ngayKetThuc) throws RemoteException {
        int soLuongHoaDon = 0;
        try {
            String jpql = "SELECT COUNT(hd) FROM HoaDon hd WHERE hd.ngayLap BETWEEN :ngayBatDau AND :ngayKetThuc";

            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("ngayBatDau", ngayBatDau);
            query.setParameter("ngayKetThuc", ngayKetThuc);

            // Lấy số lượng hóa đơn từ kết quả truy vấn
            Long count = query.getSingleResult();
            soLuongHoaDon = count.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi thống kê số hóa đơn từ " + ngayBatDau + " đến " + ngayKetThuc, e);
        }
        return soLuongHoaDon;
    }

    @Override
    public int thongKeSoHoaDonHoanTra(Date ngayBatDau, Date ngayKetThuc) throws RemoteException {
        int soLuongHoaDonHoanTra = 0;
        try {
            String jpql = "SELECT COUNT(hh) FROM HoaDonHoanTra hh WHERE hh.ngayHoan BETWEEN :ngayBatDau AND :ngayKetThuc";

            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("ngayBatDau", ngayBatDau);
            query.setParameter("ngayKetThuc", ngayKetThuc);

            // Lấy số lượng hóa đơn hoàn trả từ kết quả truy vấn
            Long count = query.getSingleResult();
            soLuongHoaDonHoanTra = count.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi thống kê số hóa đơn hoàn trả từ " + ngayBatDau + " đến " + ngayKetThuc, e);
        }
        return soLuongHoaDonHoanTra;
    }

    @Override
    public int thongKeSoLuongSanPhamDaBan(Date ngayBatDau, Date ngayKetThuc) throws RemoteException {
        int soLuongSanPhamDaBan = 0;
        try {
            String jpql = "SELECT SUM(cthd.soLuong) FROM ChiTietHoaDon cthd WHERE cthd.hoaDon IN " +
                    "(SELECT hd FROM HoaDon hd WHERE hd.ngayLap BETWEEN :ngayBatDau AND :ngayKetThuc)";

            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("ngayBatDau", ngayBatDau);
            query.setParameter("ngayKetThuc", ngayKetThuc);

            // Lấy số lượng sản phẩm đã bán từ kết quả truy vấn
            Long sumSoLuong = query.getSingleResult();
            if (sumSoLuong != null) {
                soLuongSanPhamDaBan = sumSoLuong.intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi thống kê số lượng sản phẩm đã bán từ " + ngayBatDau + " đến " + ngayKetThuc, e);
        }
        return soLuongSanPhamDaBan;
    }

    @Override
    public double ThongKeTongDoanhThu() throws RemoteException {
        double tongDoanhThu = 0;
        try {
            String jpql = "SELECT SUM(hd.tongTien) FROM HoaDon hd";
            Query query = em.createQuery(jpql);
            Object result = query.getSingleResult();

            if (result != null) {
                tongDoanhThu = (double) result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi thống kê tổng doanh thu", e);
        }
        return tongDoanhThu;
    }

    @Override
    public int thongKeTongSoHoaDon() throws RemoteException {
        int tongSoHoaDon = 0;
        try {
            String jpql = "SELECT COUNT(hd) FROM HoaDon hd";
            Query query = em.createQuery(jpql);
            Object result = query.getSingleResult();

            if (result != null) {
                tongSoHoaDon = ((Long) result).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi thống kê tổng số hóa đơn", e);
        }
        return tongSoHoaDon;
    }

    @Override
    public int thongKeTongSoHoaDonHoanTra() throws RemoteException {
        int tongSoHoaDonHoanTra = 0;
        try {
            String jpql = "SELECT COUNT(hh) FROM HoaDonHoanTra hh";
            Query query = em.createQuery(jpql);
            Object result = query.getSingleResult();

            if (result != null) {
                tongSoHoaDonHoanTra = ((Long) result).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi thống kê tổng số hóa đơn hoàn trả", e);
        }
        return tongSoHoaDonHoanTra;
    }

    @Override
    public int thongKeTongSoLuongSanPhamDaBan() throws RemoteException {
        int tongSoLuongSanPhamDaBan = 0;
        try {
            String jpql = "SELECT SUM(cthd.soLuong) FROM ChiTietHoaDon cthd";
            Query query = em.createQuery(jpql);
            Object result = query.getSingleResult();

            if (result != null) {
                tongSoLuongSanPhamDaBan = ((Long) result).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi thống kê tổng số lượng sản phẩm đã bán", e);
        }
        return tongSoLuongSanPhamDaBan;
    }

    @Override
    public List<Integer> getDistinctYears() throws RemoteException {
        List<Integer> years = new ArrayList<>();
        try {
            String jpql = "SELECT DISTINCT YEAR(hd.ngayLap) FROM HoaDon hd";
            Query query = em.createQuery(jpql);
            List<Integer> resultList = query.getResultList();

            years.addAll(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi lấy danh sách năm", e);
        }
        return years;
    }
}