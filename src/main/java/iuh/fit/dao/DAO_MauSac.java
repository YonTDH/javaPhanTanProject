package iuh.fit.dao;

import iuh.fit.models.MauSac;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;



public interface DAO_MauSac extends Remote {
	public List<MauSac> getAlltbMauSac() throws RemoteException;
	public MauSac getMauSactheoMa(String ma) throws RemoteException;
	public MauSac getMauSactheoTen(String ten) throws RemoteException;

}
