package daoImpl;

import entity.ChiTietHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.DAO_ChiTietHoaDon;


public class DAO_ChiTietHoaDon_Impl extends UnicastRemoteObject implements DAO_ChiTietHoaDon {
	private static final long serialVersionUID = -2886598129078393935L;
	private EntityManager em;
	
	public DAO_ChiTietHoaDon_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
	}

	@Override
	public List<ChiTietHoaDon> getAllChiTietHoaDon() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("ChiTietHoaDon.getAllChiTietHoaDon", ChiTietHoaDon.class).getResultList();	
	}

	@Override
	public boolean createChiTietHoaDonVPP(ChiTietHoaDon cthd) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createChiTietHoaDon(ChiTietHoaDon cthd) throws RemoteException {
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
	public boolean deleteChiTietHoaDonVaSanPham(String maHD, String maSP) throws RemoteException {
		// TODO Auto-generated method stub	
		return em.createNamedQuery("ChiTietHoaDon.deleteChiTietHoaDonVaSanPham").setParameter("maHD", maHD)
				.setParameter("maSP", maSP).executeUpdate() > 0;
		
	}

	@Override
	public boolean deleteChiTietHoaDon(String maHD) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			ChiTietHoaDon cthd = em.find(ChiTietHoaDon.class, maHD);
			em.remove(cthd);
			
			tx.commit();
			
			return true;
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return false;
		
	}

	@Override
	public ChiTietHoaDon getCTHoaDontheoMa(String maHoaDon, String maSanPham) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("ChiTietHoaDon.getCTHoaDontheoMa", ChiTietHoaDon.class)
				.setParameter("maHoaDon", maHoaDon).setParameter("maSanPham", maSanPham).getSingleResult();
	}

	@Override
	public boolean updateCTHoaDon(ChiTietHoaDon cthd) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			em.merge(cthd);
			
			tx.commit();
			
			return true;
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return false;
	}
    
}
	