/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import dao.DAO_ChiTietHoaDonHoanTra;
import entity.ChiTietHoanTra;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DAO_ChiTietHoaDonHoanTra_Impl extends UnicastRemoteObject implements DAO_ChiTietHoaDonHoanTra {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3114920457768973935L;
	private EntityManager em;
	
	public DAO_ChiTietHoaDonHoanTra_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
	}

	@Override
	public List<ChiTietHoanTra> getAllChiTietHoanTra() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("ChiTietHoanTra.getAllChiTietHoanTra", ChiTietHoanTra.class).getResultList();
	}

	@Override
	public boolean createChiTietHoanTra(ChiTietHoanTra cthd) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
            tx.begin();
            
            em.persist(cthd);
            
            tx.commit();
            
            return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteChiTietHoanTra(String maHD) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.remove(em.find(ChiTietHoanTra.class, maHD));

			tx.commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ChiTietHoanTra getHoaDontheoMa(String maHD, String maSP) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("ChiTietHoanTra.getHoaDontheoMa", ChiTietHoanTra.class).setParameter("maHD", maHD)
				.setParameter("maSP", maSP).getSingleResult();
	}
    
}
