package iuh.fit.dao;

import iuh.fit.models.KhuyenMai;
import iuh.fit.models.NhomKhuyenMai;
import iuh.fit.models.NhomSanPham;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;



public interface DAO_NhomKhuyenMai extends Remote {
	public List<NhomKhuyenMai> getAllNhomKM() throws RemoteException;
	public boolean createNhomKM(KhuyenMai s, NhomSanPham nsp) throws RemoteException;
	public boolean deleteNhomKM(KhuyenMai s) throws RemoteException;

}
