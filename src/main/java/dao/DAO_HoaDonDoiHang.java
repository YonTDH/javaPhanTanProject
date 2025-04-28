package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.HoaDonDoiHang;

public interface DAO_HoaDonDoiHang extends Remote {
	public List<HoaDonDoiHang> getAllHoaDonDoiHang_20() throws RemoteException;
	public List<HoaDonDoiHang> getAllHoaDonDoiHang() throws RemoteException;
	public boolean createHoaDonDoiHang(HoaDonDoiHang hd) throws RemoteException;
	public boolean updateHoaDonDoiHang(HoaDonDoiHang hd) throws RemoteException;
	public HoaDonDoiHang getHoaDonDoiHangtheoMa(String ma) throws RemoteException;
	public HoaDonDoiHang getHoaDonDoiHangtheoMaHT(String maHT) throws RemoteException;
	public boolean deleteHoaDonDoi(String ma) throws RemoteException;
	
	
}
