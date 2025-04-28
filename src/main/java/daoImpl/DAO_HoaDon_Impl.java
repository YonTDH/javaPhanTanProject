/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

/**
 *
 * @author nguyen chau tai
 */
import dao.DAO_HoaDon;
import entity.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DAO_HoaDon_Impl extends UnicastRemoteObject implements DAO_HoaDon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 714446006887423242L;
	private EntityManager em;
	
	public DAO_HoaDon_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
	}
	
	@Override
	public List<HoaDon> getAllHoaDon_InDay() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("HoaDon.getAllHoaDon_InDay", HoaDon.class).getResultList();
	}

	@Override
	public List<HoaDon> getAllHoaDon() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("HoaDon.getAllHoaDon", HoaDon.class).getResultList();
	}

	@Override
	public boolean createHoaDon(HoaDon hd) throws RemoteException {
		try {
			EntityTransaction tx = em.getTransaction();
            tx.begin();
            
            em.persist(hd);
            
            tx.commit();
            
            return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}	

	@Override
	public boolean updateHoaDon(HoaDon hd) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			em.merge(hd);
			
			tx.commit();
			
			return true;
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public HoaDon getHoaDontheoMa(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		return em.find(HoaDon.class, ma);
	}

	@Override
	public boolean deleteHoaDon(String maHD) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			HoaDon hd = em.find(HoaDon.class, maHD);
			em.remove(hd);

			tx.commit();

			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public String getMaHoaDonTheoNgay(String ngayLapHD) throws RemoteException {
		return "";
	}
}
