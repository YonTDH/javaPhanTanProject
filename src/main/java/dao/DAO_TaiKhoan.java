package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.TaiKhoan;

public interface DAO_TaiKhoan extends Remote {
//	public static boolean xacThucNguoiDung(String tenDangNhap, String matKhau) throws RemoteException; Looix
	public String phanQuyen(String email) throws RemoteException;
	public String getTenNguoiDung(String email) throws RemoteException;
	public int sendEmail(String email) throws RemoteException;
	public boolean doiMatKhau(String email, String newPassword) throws RemoteException;
	public List<TaiKhoan> getAllTaiKhoan() throws RemoteException;
	public TaiKhoan getTaiKhoanByEmail(String email) throws RemoteException;

}
