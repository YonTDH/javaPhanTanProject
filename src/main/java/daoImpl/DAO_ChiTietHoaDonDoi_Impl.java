package daoImpl;

import dao.DAO_ChiTietHoaDonDoi;
import entity.ChiTietHoaDonDoi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DAO_ChiTietHoaDonDoi_Impl extends UnicastRemoteObject implements DAO_ChiTietHoaDonDoi {

	private static final long serialVersionUID = -8761746815486691066L;
	private EntityManager em;
	
	public DAO_ChiTietHoaDonDoi_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
	}

	@Override
	public List<ChiTietHoaDonDoi> getAllChiTietDonDoi() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("ChiTietHoaDonDoi.getAllChiTietDonDoi", ChiTietHoaDonDoi.class)
                .getResultList();
	}

	@Override
	public boolean createChiTietDonDoi(ChiTietHoaDonDoi cthd) throws RemoteException {
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
	public boolean deleteChiTietDonDoi(String maHD) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.remove(em.find(ChiTietHoaDonDoi.class, maHD));

			tx.commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ChiTietHoaDonDoi getHoaDontheoMa(String maHDD, String maSP) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("ChiTietHoaDonDoi.getHoaDontheoMa", ChiTietHoaDonDoi.class)
				.setParameter("maHDD", maHDD).setParameter("maSP", maSP).getSingleResult();
		
	}
    
}
