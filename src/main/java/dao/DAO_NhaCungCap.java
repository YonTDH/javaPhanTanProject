package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.NhaCungCap;

public interface DAO_NhaCungCap extends Remote {
	public List<NhaCungCap> getALLNhaCungCap_20() throws RemoteException;
	public List<NhaCungCap> getALLNhaCungCap() throws RemoteException;
	public NhaCungCap getNCCTheoMa(String maNCC) throws RemoteException;
	public NhaCungCap getNCCTheoTen(String tenNCC) throws RemoteException;
	public boolean themNhaCungCap(NhaCungCap ncc) throws RemoteException;
	public boolean updateNhaCungCap(String maSua, NhaCungCap nccMoi) throws RemoteException;
	public List<NhaCungCap> locNhaCungCap(String duLieuTim) throws RemoteException;
	public String getMaNhaCungCapDB() throws RemoteException;
}
