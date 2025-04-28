package daoImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.List;

import dao.DAO_ChiTietHoaDon;
import dao.DAO_SanPham;
import entity.NhaCungCap;
import entity.NhomSanPham;
import entity.SanPham;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Persistence;

public class DAO_SanPham_Impl extends UnicastRemoteObject implements DAO_SanPham {
	private static final long serialVersionUID = -61621119853449674L;
	
	private EntityManager em;
	public DAO_SanPham_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
	}
	
	@Override
	public boolean createSanPham(SanPham sp) throws RemoteException {
		
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.persist(sp);

			tx.commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean deleteSanPham(String maSP) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean updateSanPham(SanPham sp) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.merge(sp);

			tx.commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public SanPham getSanPhamtheoMa(String maSP) throws RemoteException {
		// TODO Auto-generated method stub
		return em.find(SanPham.class, maSP);
	}
	@Override
	public SanPham getSanPhamtheoTen(String tenSP) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<SanPham> getAllSanPham() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("SanPham.getAllSanPham", SanPham.class).getResultList();
	}
	

}
