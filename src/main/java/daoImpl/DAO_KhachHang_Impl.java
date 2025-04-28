/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import dao.DAO_KhachHang;
import entity.KhachHang;
import entity.NhomKhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author nguyen chau tai
 */
public class DAO_KhachHang_Impl extends UnicastRemoteObject implements DAO_KhachHang {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5008687694217238743L;
	private EntityManager em;

	public DAO_KhachHang_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
	}

	@Override
	public List<KhachHang> getAllKhachHang_20() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("KhachHang.getAllKhachHang_20", KhachHang.class)
				.setMaxResults(20)
				.getResultList();}

	@Override
	public List<KhachHang> getAllKhachHang() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("KhachHang.getAllKhachHang", KhachHang.class).getResultList();
	}

	@Override
	public KhachHang getKHTheoMa(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		return em.find(KhachHang.class, ma);
	}

	@Override
	public KhachHang getKHTheoSDT(String soDienThoai) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("KhachHang.getKHTheoSDT", KhachHang.class).setParameter("sdt", soDienThoai)
				.getSingleResult();
	}

	@Override
	public boolean themKhachHang(KhachHang cus) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.persist(cus);

			tx.commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateKhachHang(KhachHang cus) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.merge(cus);

			tx.commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}
	//maKhachHang,tenKhachHang,soDienThoai,nhomKhachHang,tongTienMua,soLuongHoaDon
	@Override
	public List<KhachHang> timKhachHangTheoThongTin(String data) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("KhachHang.timKhachHangTheoThongTin", KhachHang.class).setParameter("data", "%"+data+"%")
				.getResultList();
	}

	// Khi khách hàng có tổng tiền mua >= 1000000 thì sẽ được nâng cấp lên nhóm khách hàng VIP
	@Override
	public void capNhatNhomKhachHang() throws RemoteException {
		// TODO Auto-generated method stub

		List<Object[]> results = em.createNamedQuery("KhachHang.capNhatNhomKhachHang", Object[].class).getResultList();


		for (Object[] result : results) {
			String maKhachHang = (String) result[0];
			Double tongTienMuaDouble = (Double) result[1];

			// Convert Double to BigDecimal
			BigDecimal tongTienMua = BigDecimal.valueOf(tongTienMuaDouble);

			// Kiểm tra nếu tổng tiền mua đạt hoặc vượt quá 1,000,000 đồng
			if (tongTienMua.compareTo(new BigDecimal("1000000")) >= 0) {
				// Cập nhật nhóm khách hàng lên VIP
				KhachHang khachHang = em.find(KhachHang.class, maKhachHang);
				khachHang.setNhomKhachHang(NhomKhachHang.KHACHVIP);
				em.merge(khachHang);
			}
		}
	}

	@Override
	public List<KhachHang> getKhachHangTrong1Thang() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KhachHang> locDuLieuKhachHang(String data) throws RemoteException {

		TypedQuery<KhachHang> query = em.createNamedQuery("KhachHang.locDuLieuKhachHang", KhachHang.class);
		query.setParameter("data", "%" + data + "%");
		return query.getResultList();
	}


}











