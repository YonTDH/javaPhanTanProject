package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.KhuyenMai;

public interface DAO_KhuyenMai extends Remote {
	public List<KhuyenMai> getAlltbKM() throws RemoteException;
	public boolean createKhuyenMai(KhuyenMai s) throws RemoteException;
	public List<KhuyenMai> getAlltbKMTheoDangChay() throws RemoteException;
	public KhuyenMai getKMtheoMa(String ma) throws RemoteException;
	public boolean updateKhuyenMai(KhuyenMai s) throws RemoteException;
	public String getMaKhuyenMaiTNDB() throws RemoteException;
	public boolean updateTinhTrang(KhuyenMai km) throws RemoteException;
	public KhuyenMai getKmTheoMa(String maKM) throws RemoteException;

}
