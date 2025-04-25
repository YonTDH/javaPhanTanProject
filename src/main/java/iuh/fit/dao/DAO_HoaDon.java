package iuh.fit.dao;

import iuh.fit.models.HoaDon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface DAO_HoaDon extends Remote {
	public List<HoaDon> getAllHoaDon_InDay() throws RemoteException;
	public List<HoaDon> getAllHoaDon() throws RemoteException;
	public boolean createHoaDon(HoaDon hd) throws RemoteException;
	public boolean updateHoaDon(HoaDon hd) throws RemoteException;	
	public HoaDon getHoaDontheoMa(String ma) throws RemoteException;
	public boolean deleteHoaDon(String maHD) throws RemoteException;
	public List<String> getMaHoaDonTheoNgay(String ngayLapHD) throws RemoteException;
}
