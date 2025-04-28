/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import dao.DAO_KhuyenMai;
import entity.KhuyenMai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.List;

public class DAO_KhuyenMai_Impl extends UnicastRemoteObject implements DAO_KhuyenMai {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4973832062414930420L;
	private EntityManager em;
	
	public DAO_KhuyenMai_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
	}

	@Override
	public List<KhuyenMai> getAlltbKM() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("KhuyenMai.getAlltbKM", KhuyenMai.class).getResultList();
	}

	@Override
	public boolean createKhuyenMai(KhuyenMai s) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.persist(s);

			tx.commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}
	// Mã đang chạy là "Đang hoạt động"
	@Override
	public List<KhuyenMai> getAlltbKMTheoDangChay() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("KhuyenMai.getAlltbKMTheoDangChay", KhuyenMai.class).getResultList();
	}

	@Override
	public KhuyenMai getKMtheoMa(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		return em.find(KhuyenMai.class, ma);
	}

	@Override
	public boolean updateKhuyenMai(KhuyenMai s) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.merge(s);

			tx.commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String getMaKhuyenMaiTNDB() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	// Update trạng thái của khuyến mãi nếu ngày kết thúc < ngày hiện tại thì là "Đã ngừng"
	@Override
	public boolean updateTinhTrang(KhuyenMai km) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
	    try {
			tx.begin();
			
			LocalDateTime ngayHienTai = LocalDateTime.now();

			if (km.getNgayKetThuc().isBefore(ngayHienTai)) {
		        km.setTrangThai("Đã ngừng");
		        em.merge(km);
		    }

			tx.commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public KhuyenMai getKmTheoMa(String maKM) throws RemoteException {
		// TODO Auto-generated method stub
		return em.find(KhuyenMai.class, maKM);
	}
	
	
}
