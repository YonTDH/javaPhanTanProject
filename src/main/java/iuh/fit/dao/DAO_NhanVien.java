package iuh.fit.dao;

import iuh.fit.models.ChucVu;
import iuh.fit.models.NhanVien;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;






public interface DAO_NhanVien extends Remote {
	public List<NhanVien> getAllNhanVien_20() throws RemoteException;
	public List<NhanVien> getAllNhanVien() throws RemoteException;
	public List<NhanVien> getAllNhanVienTheoCa(String caLv) throws RemoteException;
	public NhanVien getNVTheoMa(String maNV) throws RemoteException;
	public boolean themNhanVien(NhanVien nv) throws RemoteException;
	public boolean updateNhanVien(String maNVSua, NhanVien nvMoi) throws RemoteException;
	public List<NhanVien> locNhanVien(String duLieuTim) throws RemoteException;
	public List<NhanVien> locNhanVienTheoChucVu(ChucVu duLieuTim) throws RemoteException;
	public String getMaNhanVienQLDB() throws RemoteException;
	public String getMaNhanVienTNDB() throws RemoteException;
	public String getNhanVienTheoCa(String email) throws RemoteException;
}
