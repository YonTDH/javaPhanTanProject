package iuh.fit.dao;

import iuh.fit.models.Sach;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface DAO_Sach extends Remote {
	public List<Sach> getAlltbSach_20() throws RemoteException;
	public List<Sach> getAlltbSach() throws RemoteException;
	public boolean createSach(Sach s) throws RemoteException;
	public void importExcel(String fileName, String sheetName) throws RemoteException;
	public Sach getSachtheoMa(String ma) throws RemoteException;
	public boolean updateSach(Sach s) throws RemoteException;
}
