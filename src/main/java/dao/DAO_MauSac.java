package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.MauSac;

public interface DAO_MauSac extends Remote {
	public List<MauSac> getAlltbMauSac() throws RemoteException;
	public MauSac getMauSactheoMa(String ma) throws RemoteException;
	public MauSac getMauSactheoTen(String ten) throws RemoteException;

}
