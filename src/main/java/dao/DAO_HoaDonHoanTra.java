package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import entity.HoaDonHoanTra;

public interface DAO_HoaDonHoanTra extends Remote {
	public List<HoaDonHoanTra> getAllHoaDonHoanTra_20() throws RemoteException;
	public List<HoaDonHoanTra> getAllHoaDonHoanTra() throws RemoteException;
	public boolean createHoaDonHoanTra(HoaDonHoanTra hd) throws RemoteException;
	public boolean updateHoaDonHoanTra(HoaDonHoanTra hd) throws RemoteException;
	public HoaDonHoanTra getHoaDonHoanTratheoMa(String ma) throws RemoteException;
	public boolean deleteHoaDonHoanTra(String ma) throws RemoteException;

}
