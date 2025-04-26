package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_ThongKe;
import iuh.fit.models.MonthlyRevenueInfo;
import iuh.fit.models.ProductInfo;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public class DAOImpl_ThongKe implements DAO_ThongKe {
    @Override
    public List<ProductInfo> getTopSellingProducts() throws RemoteException {
        return List.of();
    }

    @Override
    public double tongDoanhThu(int currentYear) throws RemoteException {
        return 0;
    }

    @Override
    public double tongHoanTra(int currentYear) throws RemoteException {
        return 0;
    }

    @Override
    public List<MonthlyRevenueInfo> tongTienTheoThang() throws RemoteException {
        return List.of();
    }

    @Override
    public List<MonthlyRevenueInfo> tienHoanTheoThang() throws RemoteException {
        return List.of();
    }

    @Override
    public double thongKeDoanhThu(Date ngayBatDau, Date ngayKetThuc) throws RemoteException {
        return 0;
    }

    @Override
    public int thongKeSoHoaDon(Date ngayBatDau, Date ngayKetThuc) throws RemoteException {
        return 0;
    }

    @Override
    public int thongKeSoHoaDonHoanTra(Date ngayBatDau, Date ngayKetThuc) throws RemoteException {
        return 0;
    }

    @Override
    public int thongKeSoLuongSanPhamDaBan(Date ngayBatDau, Date ngayKetThuc) throws RemoteException {
        return 0;
    }

    @Override
    public double ThongKeTongDoanhThu() throws RemoteException {
        return 0;
    }

    @Override
    public int thongKeTongSoHoaDon() throws RemoteException {
        return 0;
    }

    @Override
    public int thongKeTongSoHoaDonHoanTra() throws RemoteException {
        return 0;
    }

    @Override
    public int thongKeTongSoLuongSanPhamDaBan() throws RemoteException {
        return 0;
    }

    @Override
    public List<Integer> getDistinctYears() throws RemoteException {
        return List.of();
    }
}
