package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.SanPham;

public interface DAO_SanPham extends Remote{
	public boolean createSanPham(SanPham sp) throws RemoteException;
	public boolean deleteSanPham(String maSP) throws RemoteException;
	public boolean updateSanPham(SanPham sp)	throws RemoteException;
	public SanPham getSanPhamtheoMa(String maSP) throws RemoteException;
	public SanPham getSanPhamtheoTen(String tenSP) throws RemoteException;
	public List<SanPham> getAllSanPham() throws RemoteException;
	
	

}
