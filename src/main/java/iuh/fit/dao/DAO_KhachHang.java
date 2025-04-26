package iuh.fit.dao;

import iuh.fit.models.KhachHang;
import iuh.fit.models.NhomKhachHang;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;



public interface DAO_KhachHang extends Remote {
	 public List<KhachHang> getAllKhachHang_20() throws RemoteException; //chua rõ ý đồ
	 public List<KhachHang> getAllKhachHang() throws RemoteException;
	 public KhachHang getKHTheoMa(String ma) throws RemoteException;
	 public KhachHang getKHTheoSDT(String soDienThoai) throws RemoteException;
	 public boolean themKhachHang(KhachHang cus) throws RemoteException;
	 public boolean updateKhachHang(KhachHang cus) throws RemoteException;
	 public List<KhachHang> timKhachHangTheoThongTin(String data) throws RemoteException;
	 public void capNhatNhomKhachHang(String maKhachHang, NhomKhachHang nhomMoi) throws RemoteException;
	 public List<KhachHang> getKhachHangTrong1Thang() throws RemoteException;
	 public List<KhachHang> locDuLieuKhachHang(String data) throws RemoteException;
	 
}
