/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import dao.DAO_HoaDonDoiHang;
import entity.HoaDonDoiHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DAO_HoaDonDoiHang_Impl extends UnicastRemoteObject implements DAO_HoaDonDoiHang {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8406624769011620410L;
	private EntityManager em;
	
	public DAO_HoaDonDoiHang_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
	}
	
	@Override
	public List<HoaDonDoiHang> getAllHoaDonDoiHang_20() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("HoaDonDoiHang.getAllHoaDonDoiHang_20", HoaDonDoiHang.class)
				.setMaxResults(20)
				.getResultList();
	}

	@Override
	public List<HoaDonDoiHang> getAllHoaDonDoiHang() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("HoaDonDoiHang.getAllHoaDonDoiHang_20", HoaDonDoiHang.class).getResultList();
	}

	@Override
	public boolean createHoaDonDoiHang(HoaDonDoiHang hd) throws RemoteException {
		// TODO Auto-generated method stub
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
	public boolean updateHoaDonDoiHang(HoaDonDoiHang hd) throws RemoteException {
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
	public HoaDonDoiHang getHoaDonDoiHangtheoMa(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		return em.find(HoaDonDoiHang.class, ma);
	}

	@Override
	public HoaDonDoiHang getHoaDonDoiHangtheoMaHT(String maHT) throws RemoteException {
		// TODO Auto-generated method stub
		try {
	        // Create a JPQL query to select a HoaDonDoiHang object based on the provided maHT
			return em.createNamedQuery("HoaDonDoiHang.getHoaDonDoiHangtheoMaHT", HoaDonDoiHang.class)
					.setParameter("maHT", maHT).getSingleResult();
	    } catch (NoResultException e) {
	        // Handle the case where no result is found
	        return null;
	    } catch (NonUniqueResultException e) {
	        // Handle the case where multiple results are found
	        // Log or throw an appropriate exception
	        System.out.println("Multiple HoaDonDoiHang found for maHT: " + maHT);
	        return null;
	    } catch (Exception e) {
	        // Handle other exceptions, like database connection issues
	        e.printStackTrace(); // Print the stack trace for debugging
	        return null;
	    }
	}

	@Override
	public boolean deleteHoaDonDoi(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			HoaDonDoiHang hd = em.find(HoaDonDoiHang.class, ma);
			em.remove(hd);
			
			tx.commit();
			
			return true;
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return false;
	}

    
}
