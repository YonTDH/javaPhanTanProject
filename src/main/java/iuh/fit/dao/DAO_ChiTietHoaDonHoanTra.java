package iuh.fit.dao;

import iuh.fit.models.ChiTietHoanTra;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;



public interface DAO_ChiTietHoaDonHoanTra extends Remote {
	public List<ChiTietHoanTra> getAllChiTietHoanTra() throws RemoteException;
	public boolean createChiTietHoanTra(ChiTietHoanTra cthd) throws RemoteException;
	public boolean deleteChiTietHoanTra(String maHD) throws RemoteException;
	public ChiTietHoanTra getHoaDontheoMa(String maHD, String maSP) throws RemoteException;
	
	
}
