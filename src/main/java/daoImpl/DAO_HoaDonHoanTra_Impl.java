/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

/**
 *
 * @author nguyen chau tai
 */
import dao.DAO_HoaDonHoanTra;
import entity.HoaDonHoanTra;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DAO_HoaDonHoanTra_Impl extends UnicastRemoteObject implements DAO_HoaDonHoanTra {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1241752642037988363L;
	private EntityManager em;
	
	public DAO_HoaDonHoanTra_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
	}
	
	@Override
	public List<HoaDonHoanTra> getAllHoaDonHoanTra_20() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("HoaDonHoanTra.getAllHoaDonHoanTra_20", HoaDonHoanTra.class)
				.setMaxResults(20)
				.getResultList();
	}

	@Override
	public List<HoaDonHoanTra> getAllHoaDonHoanTra() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("HoaDonHoanTra.getAllHoaDonHoanTra", HoaDonHoanTra.class).getResultList();
	}

	@Override
	public boolean createHoaDonHoanTra(HoaDonHoanTra hd) throws RemoteException {
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
	public boolean updateHoaDonHoanTra(HoaDonHoanTra hd) throws RemoteException {
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
	public HoaDonHoanTra getHoaDonHoanTratheoMa(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		return em.find(HoaDonHoanTra.class, ma);
	}

	@Override
	public boolean deleteHoaDonHoanTra(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			HoaDonHoanTra hd = em.find(HoaDonHoanTra.class, ma);
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
