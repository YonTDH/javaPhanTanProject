package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import entity.HoaDon;

public interface DAO_HoaDon extends Remote {
	public List<HoaDon> getAllHoaDon_InDay() throws RemoteException;
	public List<HoaDon> getAllHoaDon() throws RemoteException;
	public boolean createHoaDon(HoaDon hd) throws RemoteException;
	public boolean updateHoaDon(HoaDon hd) throws RemoteException;	
	public HoaDon getHoaDontheoMa(String ma) throws RemoteException;
	public boolean deleteHoaDon(String maHD) throws RemoteException;
	public String getMaHoaDonTheoNgay(String ngayLapHD) throws RemoteException;
}
