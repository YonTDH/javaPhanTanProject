package iuh.fit.dao;

import iuh.fit.models.MonthlyRevenueInfo;
import iuh.fit.models.ProductInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;



public interface DAO_ThongKe extends Remote {
	public List<ProductInfo> getTopSellingProducts() throws RemoteException;
	public double tongDoanhThu(int currentYear) throws RemoteException;
	public double tongHoanTra(int currentYear) throws RemoteException;
	public List<MonthlyRevenueInfo> tongTienTheoThang() throws RemoteException;
	public List<MonthlyRevenueInfo> tienHoanTheoThang() throws RemoteException;
	public double thongKeDoanhThu(Date ngayBatDau, Date ngayKetThuc) throws RemoteException;
	public int thongKeSoHoaDon(Date ngayBatDau, Date ngayKetThuc) throws RemoteException;
	public int thongKeSoHoaDonHoanTra(Date ngayBatDau, Date ngayKetThuc) throws RemoteException;
	public int thongKeSoLuongSanPhamDaBan(Date ngayBatDau, Date ngayKetThuc) throws RemoteException;
	public double ThongKeTongDoanhThu() throws RemoteException;
	public int thongKeTongSoHoaDon() throws RemoteException;
	public int thongKeTongSoHoaDonHoanTra() throws RemoteException;	
	public int thongKeTongSoLuongSanPhamDaBan() throws RemoteException;
	public List<Integer> getDistinctYears() throws RemoteException;

}
