package iuh.fit.dao;

import iuh.fit.models.ChiTietHoaDonDoi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;



public interface DAO_ChiTietHoaDonDoi extends Remote {
	public List<ChiTietHoaDonDoi> getAllChiTietDonDoi() throws RemoteException;
	public boolean createChiTietDonDoi(ChiTietHoaDonDoi cthd) throws RemoteException;
	public boolean deleteChiTietDonDoi(String maHD) throws RemoteException;
	public ChiTietHoaDonDoi getHoaDontheoMa(String maHDD, String maSP) throws RemoteException;

}
