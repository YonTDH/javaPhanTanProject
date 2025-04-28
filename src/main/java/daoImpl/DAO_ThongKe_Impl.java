package daoImpl;

import dao.DAO_ThongKe;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.MonthlyRevenueInfo;
import entity.ProductInfo;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DAO_ThongKe_Impl extends UnicastRemoteObject implements DAO_ThongKe {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5609314847746075990L;
	private EntityManager em;
	
	public DAO_ThongKe_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
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
            //
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
                int totalQuantity =  (int) result[1];
                topProducts.add(new ProductInfo(productId, totalQuantity));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return topProducts;
    }
	
//	public List<ProductInfo> getTopSellingProducts() {
//        ConnectDB.getInstance();
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement state = null;
//        ResultSet resultSet = null;
//        List<ProductInfo> topProducts = new ArrayList<>();
//        try {
//            // Lấy ngày bắt đầu và kết thúc của tháng hiện tại
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.DAY_OF_MONTH, 1);
//            Date startDate = calendar.getTime();
//            calendar.add(Calendar.MONTH, 1);
//            calendar.add(Calendar.DATE, -1);
//            Date endDate = calendar.getTime();
//            //
//            // Tạo câu truy vấn SQL
//            String sql = "SELECT TOP 5 od.sanPham, SUM(od.soLuong) AS total_quantity "
//                    + "FROM ChiTietHoaDon od "
//                    + "JOIN HoaDon o ON od.hoaDon = o.maHoaDon "
//                    + "WHERE o.ngayLap BETWEEN ? AND ? "
//                    + "GROUP BY od.sanPham "
//                    + "ORDER BY total_quantity DESC ";
//            // Chuẩn bị và thực thi câu truy vấn
//            state = con.prepareStatement(sql);
//            state.setDate(1, new java.sql.Date(startDate.getTime()));
//            state.setDate(2, new java.sql.Date(endDate.getTime()));
//            resultSet = state.executeQuery();
//
//            while (resultSet.next()) {
//                String productId = resultSet.getString("sanPham");
//                int totalQuantity = resultSet.getInt("total_quantity");
//                topProducts.add(new ProductInfo(productId, totalQuantity));
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return topProducts;
//    }
	
	public double tongDoanhThu(int currentYear) {
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
            // Xử lý ngoại lệ nếu cần
        }
        return totalRevenue;
    }

//    public double tongDoanhThu(int currentYear) {
//        ConnectDB.getInstance();
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement state = null;
//        ResultSet resultSet = null;
//        double totalRevenue = 0.0;
//        try {
//            // Lấy ngày bắt đầu và kết thúc của tháng hiện tại
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.MONTH, Calendar.JANUARY);
//            calendar.set(Calendar.DAY_OF_MONTH, 1);
//            Date startDate = calendar.getTime();
//            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
//            calendar.set(Calendar.DAY_OF_MONTH, 31);
//            Date endDate = calendar.getTime();
//            //
//            // Tạo câu truy vấn SQL
//            String sql = "SELECT SUM(tongTien) AS total_revenue FROM hoaDon "
//                    + "WHERE YEAR(ngayLap) = ? "
//                    + "GROUP BY MONTH(ngayLap)";
//            // Chuẩn bị và thực thi câu truy vấ   
//            state = con.prepareStatement(sql);
//            state.setInt(1, currentYear);
//            resultSet = state.executeQuery();
//            // Xử lý kết quả
//
//            while (resultSet.next()) {
//                totalRevenue = resultSet.getDouble("total_revenue");
//                System.out.println("Total Revenue for the year: " + totalRevenue);
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return totalRevenue;
//    }

    public double tongHoanTra(int currentYear) {
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
            // Xử lý ngoại lệ nếu cần
        }
        return totalRefund;
    }
    
//    public double tongHoanTra(int currentYear) {
//        ConnectDB.getInstance();
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement state = null;
//        ResultSet resultSet = null;
//        double totalRevenue = 0.0;
//        try {
//            // Lấy ngày bắt đầu và kết thúc của tháng hiện tại
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.MONTH, Calendar.JANUARY);
//            calendar.set(Calendar.DAY_OF_MONTH, 1);
//            Date startDate = calendar.getTime();
//            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
//            calendar.set(Calendar.DAY_OF_MONTH, 31);
//            Date endDate = calendar.getTime();
//            //
//            // Tạo câu truy vấn SQL
//            String sql = "SELECT SUM(tienHoanTra) AS total_revenue FROM hoaDonHoanTra "
//                    + "WHERE YEAR(ngayHoan) = ? "
//                    + "GROUP BY MONTH(ngayHoan)";
//            // Chuẩn bị và thực thi câu truy vấn
//            // Chuẩn bị và thực thi câu truy vấ   
//            state = con.prepareStatement(sql);
//            state.setInt(1, currentYear);
//            resultSet = state.executeQuery();
//            // Xử lý kết quả
//
//            while (resultSet.next()) {
//                totalRevenue = resultSet.getDouble("total_revenue");
//                System.out.println("Total hoan tra " + totalRevenue);
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return totalRevenue;
//    }

    public List<MonthlyRevenueInfo> tongTienTheoThang() {
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
            // Xử lý ngoại lệ nếu cần
        }
        return monthlyRevenueList;
    }
    
//    public List<MonthlyRevenueInfo> tongTienTheoThang() {
//        ConnectDB.getInstance();
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement state = null;
//        ResultSet resultSet = null;
//        List<MonthlyRevenueInfo> monthlyRevenueList = new ArrayList<>();
//        // Tạo danh sách đầy đủ với 12 tháng
//        for (int month = 1; month <= 12; month++) {
//            monthlyRevenueList.add(new MonthlyRevenueInfo(month, 0.0));
//        }
//        try {
//            // Lấy ngày bắt đầu và kết thúc của tháng hiện tại
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.MONTH, Calendar.JANUARY);
//            calendar.set(Calendar.DAY_OF_MONTH, 1);
//            Date startDate = calendar.getTime();
//            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
//            calendar.set(Calendar.DAY_OF_MONTH, 31);
//            Date endDate = calendar.getTime();
//            //
//            // Tạo câu truy vấn SQL
//            String sql = "SELECT MONTH(ngayLap) AS month, COALESCE(SUM(tongTien), 0.0) AS total_revenue "
//                    + "FROM hoaDon "
//                    + "WHERE YEAR(ngayLap) = ? "
//                    + "GROUP BY MONTH(ngayLap)";
//            // Lấy năm hiện tại
//            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
//            // Chuẩn bị và thực thi câu truy vấ   
//            state = con.prepareStatement(sql);
//            state.setInt(1, currentYear);
//            resultSet = state.executeQuery();
//            // Xử lý kết quả
//
//            while (resultSet.next()) {
//                int month = resultSet.getInt("month");
//                double totalRevenue = resultSet.getDouble("total_revenue");
//
//                // Thêm thông tin vào danh sách
//                monthlyRevenueList.add(new MonthlyRevenueInfo(month, totalRevenue));
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
////        finally {
////            try {
////                // Đóng các tài nguyên
////                if (resultSet != null) resultSet.close();
////                if (state != null) state.close();
////                if (con != null) con.close();
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        }
//        return monthlyRevenueList;
//    }

    public List<MonthlyRevenueInfo> tienHoanTheoThang() {
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
            // Xử lý ngoại lệ nếu cần
        }
        return monthlyRevenueList;
    }
    
//    public List<MonthlyRevenueInfo> tienHoanTheoThang() {
//        ConnectDB.getInstance();
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement state = null;
//        ResultSet resultSet = null;
//        List<MonthlyRevenueInfo> monthlyRevenueList = new ArrayList<>();
//        try {
//            // Lấy ngày bắt đầu và kết thúc của tháng hiện tại
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.MONTH, Calendar.JANUARY);
//            calendar.set(Calendar.DAY_OF_MONTH, 1);
//            Date startDate = calendar.getTime();
//            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
//            calendar.set(Calendar.DAY_OF_MONTH, 31);
//            Date endDate = calendar.getTime();
//            //
//            // Tạo câu truy vấn SQL
//            String sql = "SELECT MONTH(ngayHoan) AS month, SUM(tienHoanTra) AS total_revenue "
//                    + "FROM hoaDonHoanTra "
//                    + "WHERE YEAR(ngayHoan) = ? "
//                    + "GROUP BY MONTH(ngayHoan)";
//            // Lấy năm hiện tại
//            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
//            // Chuẩn bị và thực thi câu truy vấ   
//            state = con.prepareStatement(sql);
//            state.setInt(1, currentYear);
//            resultSet = state.executeQuery();
//            // Xử lý kết quả
//
//            while (resultSet.next()) {
//                int month = resultSet.getInt("month");
//                double totalRevenue = resultSet.getDouble("total_revenue");
//
//                // Thêm thông tin vào danh sách
//                monthlyRevenueList.add(new MonthlyRevenueInfo(month, totalRevenue));
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
////        finally {
////            try {
////                // Đóng các tài nguyên
////                if (resultSet != null) resultSet.close();
////                if (state != null) state.close();
////                if (con != null) con.close();
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        }
//        return monthlyRevenueList;
//    }

    
    
    
    public double thongKeDoanhThu(Date ngayBatDau, Date ngayKetThuc) {
        double tongDoanhThu = 0.0;

        try {
            String jpql = "SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE hd.ngayLap BETWEEN ? AND ?";

            TypedQuery<Double> query = (TypedQuery<Double>) em.createNativeQuery(jpql, Double.class);
            query.setParameter(1, new java.sql.Date(ngayBatDau.getTime()));
            query.setParameter(2, new java.sql.Date(ngayKetThuc.getTime()));

            // Lấy tổng doanh thu từ kết quả truy vấn
            Double sumTongTien = query.getSingleResult();
            if (sumTongTien != null) {
                tongDoanhThu = sumTongTien;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tongDoanhThu;
    }
    
    // Thực hiện thống kê theo ngày từ ngayBatDau đến ngayKetThuc 
//    public double thongKeDoanhThu(Date ngayBatDau, Date ngayKetThuc) {
//        double tongDoanhThu = 0.0;
//
//        try {
//            Connection con = ConnectDB.getInstance().getConnection();
//            String query = "SELECT tongTien FROM HoaDon WHERE ngayLap BETWEEN ? AND ?";
//            PreparedStatement pst = con.prepareStatement(query);
//
//            // Thiết lập tham số cho PreparedStatement
//            pst.setObject(1, new java.sql.Timestamp(ngayBatDau.getTime()));
//            pst.setObject(2, new java.sql.Timestamp(ngayKetThuc.getTime()));
//
//            ResultSet rs = pst.executeQuery();
//
//            while (rs.next()) {
//                // Lấy giá trị từ cột 'tongTien' của mỗi hóa đơn và cộng vào tổng doanh thu
//                double tongTien = rs.getDouble("tongTien");
//                tongDoanhThu += tongTien;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return tongDoanhThu;
//    }
    
    
    public int thongKeSoHoaDon(Date ngayBatDau, Date ngayKetThuc) {
        int soLuongHoaDon = 0;

        try {
            String jpql = "SELECT COUNT(*) AS SoLuongHoaDon FROM HoaDon WHERE ngayLap BETWEEN ? AND ?";

            TypedQuery<Long> query = (TypedQuery<Long>) em.createNativeQuery(jpql, Long.class);
            query.setParameter(1, new java.sql.Date(ngayBatDau.getTime()));
            query.setParameter(2, new java.sql.Date(ngayKetThuc.getTime()));

            // Lấy số lượng hóa đơn từ kết quả truy vấn
            Long count = query.getSingleResult();
            soLuongHoaDon = count.intValue();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return soLuongHoaDon;
    }
    
    //Lấy số lượng hóa đơn từ ngayBatDau đến ngayKetThuc 
//    public int thongKeSoHoaDon(Date ngayBatDau, Date ngayKetThuc) {
//        int soLuongHoaDon = 0;
//
//        try {
//            Connection con = ConnectDB.getInstance().getConnection();
//
//            // Sử dụng PreparedStatement để tránh SQL injection
//            String query = "SELECT COUNT(*) AS SoLuongHoaDon FROM HoaDon WHERE ngayLap BETWEEN ? AND ?";
//            PreparedStatement pst = con.prepareStatement(query);
//
//            // Thiết lập tham số cho PreparedStatement
//            pst.setObject(1, new java.sql.Timestamp(ngayBatDau.getTime()));
//            pst.setObject(2, new java.sql.Timestamp(ngayKetThuc.getTime()));
//
//            ResultSet rs = pst.executeQuery();
//
//            if (rs.next()) {
//                soLuongHoaDon = rs.getInt("SoLuongHoaDon");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return soLuongHoaDon;
//    }
    
    
    public int thongKeSoHoaDonHoanTra(Date ngayBatDau, Date ngayKetThuc) {
        int soLuongHoaDonHoanTra = 0;

        try {
            String jpql = "SELECT COUNT(*) AS SoLuongHoaDonHoanTra FROM HoaDonHoanTra WHERE ngayHoan BETWEEN ? AND ?";

            TypedQuery<Long> query = (TypedQuery<Long>) em.createNativeQuery(jpql, Long.class);
            query.setParameter(1, new java.sql.Date(ngayBatDau.getTime()));
            query.setParameter(2, new java.sql.Date(ngayKetThuc.getTime()));

            // Lấy số lượng hóa đơn hoàn trả từ kết quả truy vấn
            Long count = query.getSingleResult();
            soLuongHoaDonHoanTra = count.intValue();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return soLuongHoaDonHoanTra;
    }
    
    //Lấy số lượng hóa đơn hoàn trả từ ngayBatDau đến ngayKetThuc 
//    public int thongKeSoHoaDonHoanTra(Date ngayBatDau, Date ngayKetThuc) {
//        int soLuongHoaDonHoanTra = 0;
//
//        try {
//            Connection con = ConnectDB.getInstance().getConnection();
//
//            String query = "SELECT COUNT(*) AS SoLuongHoaDonHoanTra FROM HoaDonHoanTra WHERE ngayHoan BETWEEN ? AND ?";
//            PreparedStatement pst = con.prepareStatement(query);
//
//            pst.setObject(1, new java.sql.Timestamp(ngayBatDau.getTime()));
//            pst.setObject(2, new java.sql.Timestamp(ngayKetThuc.getTime()));
//
//            ResultSet rs = pst.executeQuery();
//
//            if (rs.next()) {
//                soLuongHoaDonHoanTra = rs.getInt("SoLuongHoaDonHoanTra");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return soLuongHoaDonHoanTra;
//    }
    
    public int thongKeSoLuongSanPhamDaBan(Date ngayBatDau, Date ngayKetThuc) {
        int soLuongSanPhamDaBan = 0;

        try {
            String jpql = "SELECT SUM(soLuong) AS SoLuongSanPhamDaBan FROM ChiTietHoaDon WHERE hoaDon IN "
                  + "(SELECT maHoaDon FROM HoaDon WHERE ngayLap BETWEEN ? AND ?)";

            TypedQuery<Long> query = (TypedQuery<Long>) em.createNativeQuery(jpql, Long.class);
            query.setParameter(1, new java.sql.Date(ngayBatDau.getTime()));
            query.setParameter(2, new java.sql.Date(ngayKetThuc.getTime()));
            // Lấy số lượng sản phẩm đã bán từ kết quả truy vấn
            Long sumSoLuong = query.getSingleResult();
            if (sumSoLuong != null) {
                soLuongSanPhamDaBan = sumSoLuong.intValue();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return soLuongSanPhamDaBan;
    }
    
    //Lấy số lượng sản phẩm đã bán từ ngayBatDau đến ngayKetThuc 
//    public int thongKeSoLuongSanPhamDaBan(Date ngayBatDau, Date ngayKetThuc) {
//        int soLuongSanPhamDaBan = 0;
//
//        try {
//            Connection con = ConnectDB.getInstance().getConnection();
//
//            String query = "SELECT SUM(soLuong) AS SoLuongSanPhamDaBan FROM ChiTietHoaDon WHERE hoaDon IN "
//                    + "(SELECT maHoaDon FROM HoaDon WHERE ngayLap BETWEEN ? AND ?)";
//            PreparedStatement pst = con.prepareStatement(query);
//
//            pst.setObject(1, new java.sql.Timestamp(ngayBatDau.getTime()));
//            pst.setObject(2, new java.sql.Timestamp(ngayKetThuc.getTime()));
//
//            ResultSet rs = pst.executeQuery();
//
//            if (rs.next()) {
//                soLuongSanPhamDaBan = rs.getInt("SoLuongSanPhamDaBan");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return soLuongSanPhamDaBan;
//    }
    
    public double ThongKeTongDoanhThu() {
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
        } 
        return tongDoanhThu;
    }
    
    //thống kê toàn bộ doanh thu 
//    public double ThongKeTongDoanhThu() {
//        double tongDoanhThu = 0;
//
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            String sql = "SELECT SUM(tongTien) FROM HoaDon";
//            statement = con.prepareStatement(sql);
//            resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                tongDoanhThu = resultSet.getDouble(1);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } 
//        return tongDoanhThu;
//    }
    
    
    public int thongKeTongSoHoaDon() {
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
        } 
        return tongSoHoaDon;
    }
    
    // thống kê toàn bộ số hóa đơn
//    public int thongKeTongSoHoaDon() {
//        int tongSoHoaDon = 0;
//
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            String sql = "SELECT COUNT(*) FROM HoaDon";
//            statement = con.prepareStatement(sql);
//            resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                tongSoHoaDon = resultSet.getInt(1);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } 
//        return tongSoHoaDon;
//    }
    
    public int thongKeTongSoHoaDonHoanTra() {
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
        } 
        return tongSoHoaDonHoanTra;
    }
    
    //Thống kê toàn bộ số hóa đơn hoàn trả
//    public int thongKeTongSoHoaDonHoanTra() {
//        int tongSoHoaDonHoanTra = 0;
//
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            String sql = "SELECT COUNT(*) FROM HoaDonHoanTra";
//            statement = con.prepareStatement(sql);
//            resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                tongSoHoaDonHoanTra = resultSet.getInt(1);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return tongSoHoaDonHoanTra;
//    }
    
    
    public int thongKeTongSoLuongSanPhamDaBan() {
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
        } 
        return tongSoLuongSanPhamDaBan;
    }
    
    //Thống kê toàn bộ số số lượng sản phẩm đã bán
//    public int thongKeTongSoLuongSanPhamDaBan() {
//        int tongSoLuongSanPhamDaBan = 0;
//
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            String sql = "SELECT SUM(soLuong) FROM ChiTietHoaDon";
//            statement = con.prepareStatement(sql);
//            resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                tongSoLuongSanPhamDaBan = resultSet.getInt(1);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } 
//        return tongSoLuongSanPhamDaBan;
//    }
    
    public List<Integer> getDistinctYears() {
        List<Integer> years = new ArrayList<>();

        try {
            String jpql = "SELECT DISTINCT YEAR(hd.ngayLap) FROM HoaDon hd";
            Query query = em.createQuery(jpql);
            List<Integer> resultList = query.getResultList();
            
            years.addAll(resultList);

        } catch (Exception e) {
            e.printStackTrace();
        } 

        return years;
    }
    
    //Hàm Lấy ds năm 
//    public List<Integer> getDistinctYears() {
//        List<Integer> years = new ArrayList<>();
//
//        String query = "SELECT DISTINCT YEAR(ngayLap) AS nam FROM HoaDon";
//        try {
//            Statement statement = ConnectDB.getConnection().createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                int year = resultSet.getInt("nam");
//                years.add(year);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return years;
//    }
       
}


//class MonthlyRevenueInfo {
//
//    private int month;
//    private double totalRevenue;
//
//    public MonthlyRevenueInfo(int month, double totalRevenue) {
//        this.month = month;
//        this.totalRevenue = totalRevenue;
//    }
//
//    public int getMonth() {
//        return month;
//    }
//
//    public double getTotalRevenue() {
//        return totalRevenue;
//    }
//}
