package daoImpl;

import dao.DAO_MauSac;
import entity.MauSac;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DAO_MauSac_Impl extends UnicastRemoteObject implements DAO_MauSac {

	private static final long serialVersionUID = -5502941866464467312L;
	private EntityManager em;
	
	public DAO_MauSac_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
	}

	@Override
	public List<MauSac> getAlltbMauSac() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("MauSac.getAlltbMauSac", MauSac.class).getResultList();
	}

	@Override
	public MauSac getMauSactheoMa(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		return em.find(MauSac.class, ma);
	}

	@Override
	public MauSac getMauSactheoTen(String ten) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("MauSac.getMauSactheoTen", MauSac.class).setParameter("ten", ten).getSingleResult();
	}
    
}
