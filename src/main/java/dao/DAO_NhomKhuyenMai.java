package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.KhuyenMai;
import entity.NhomKhuyenMai;
import entity.NhomSanPham;

public interface DAO_NhomKhuyenMai extends Remote {
	public List<NhomKhuyenMai> getAllNhomKM() throws RemoteException;
	public boolean createNhomKM(KhuyenMai s, NhomSanPham nsp) throws RemoteException;
	public boolean deleteNhomKM(KhuyenMai s) throws RemoteException;

}
