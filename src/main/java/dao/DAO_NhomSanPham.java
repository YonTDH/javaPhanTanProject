 package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.NhomSanPham;

public interface DAO_NhomSanPham extends Remote {
	public List<NhomSanPham> getAllNhomSanPham() throws RemoteException;
	public NhomSanPham getNsptheoTen(String ten) throws RemoteException;
	public NhomSanPham getNspTheoMa(String ma) throws RemoteException;

}
