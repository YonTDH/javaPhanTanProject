package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.ChiTietHoanTra;

public interface DAO_ChiTietHoaDonHoanTra extends Remote {
	public List<ChiTietHoanTra> getAllChiTietHoanTra() throws RemoteException;
	public boolean createChiTietHoanTra(ChiTietHoanTra cthd) throws RemoteException;
	public boolean deleteChiTietHoanTra(String maHD) throws RemoteException;
	public ChiTietHoanTra getHoaDontheoMa(String maHD, String maSP) throws RemoteException;
	
	
}
