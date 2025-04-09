package iuh.fit.dao;

import iuh.fit.models.VanPhongPham;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;



public interface DAO_VanPhongPham extends Remote {
	public List<VanPhongPham> getAllVanPhongPhan_20() throws RemoteException;
	public List<VanPhongPham> getAllVanPhongPhan() throws RemoteException;
	public boolean update(VanPhongPham vpp) throws RemoteException;
	public VanPhongPham getVPPtheoMa(String ma) throws RemoteException;
	public VanPhongPham getVpptheoTen(String ten) throws RemoteException;
	public List<VanPhongPham> getDsVpptheoMa(String ma) throws RemoteException;
	public boolean insertVpp(VanPhongPham vpp) throws RemoteException;
}
