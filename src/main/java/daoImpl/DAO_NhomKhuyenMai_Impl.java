package daoImpl;

import dao.DAO_NhomKhuyenMai;
import entity.KhuyenMai;
import entity.NhomKhuyenMai;
import entity.NhomSanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;


public class DAO_NhomKhuyenMai_Impl extends UnicastRemoteObject implements DAO_NhomKhuyenMai {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2337545081784006114L;
	private EntityManager em;
	
	public DAO_NhomKhuyenMai_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
	}

	@Override
	public List<NhomKhuyenMai> getAllNhomKM() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("NhomKhuyenMai.getAllNhomKM", NhomKhuyenMai.class).getResultList();
	}

	@Override
	public boolean createNhomKM(KhuyenMai s, NhomSanPham nsp) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			NhomKhuyenMai nkm = new NhomKhuyenMai();
			nkm.setKhuyenMai(s);
			nkm.setNhomSanPham(nsp);

			em.persist(nkm);

			tx.commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteNhomKM(KhuyenMai s) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.createNamedQuery("deleteNhomKM", NhomKhuyenMai.class).setParameter("s", s).executeUpdate();	

			tx.commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}
	
}
