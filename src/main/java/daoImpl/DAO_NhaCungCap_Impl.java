package daoImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.DAO_NhaCungCap;
import entity.NhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DAO_NhaCungCap_Impl extends UnicastRemoteObject implements DAO_NhaCungCap {

	private static final long serialVersionUID = -1333726291988341144L;
	private EntityManager em;
	public DAO_NhaCungCap_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
	}
	@Override
	public List<NhaCungCap> getALLNhaCungCap_20() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("NhaCungCap.getALLNhaCungCap_20", NhaCungCap.class)
				.setMaxResults(20)
				.getResultList();
	}
	@Override
	public List<NhaCungCap> getALLNhaCungCap() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("NhaCungCap.getALLNhaCungCap_20", NhaCungCap.class).getResultList();
	}
	@Override
	public NhaCungCap getNCCTheoMa(String maNCC) throws RemoteException {
		// TODO Auto-generated method stub
		return em.find(NhaCungCap.class, maNCC);
	}
	@Override
	public NhaCungCap getNCCTheoTen(String tenNCC) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("NhaCungCap.getNCCTheoTen", NhaCungCap.class).setParameter("tenNCC", tenNCC)
				.getSingleResult();
	}
	@Override
	public boolean themNhaCungCap(NhaCungCap ncc) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.persist(ncc);

			tx.commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateNhaCungCap(String maSua, NhaCungCap nccMoi) throws RemoteException {
		// TODO Auto-generated method stub
		NhaCungCap ncc = em.find(NhaCungCap.class, maSua);
		ncc.setTenNCC(nccMoi.getTenNCC());
		ncc.setDiaChiNCC(nccMoi.getDiaChiNCC());
		ncc.setSoDienThoai(nccMoi.getSoDienThoai());
		ncc.setEmail(nccMoi.getEmail());
		ncc.setGhiChu(nccMoi.getGhiChu());

		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.merge(ncc);
			transaction.commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	//  private String maNCC;
	//	private String tenNCC;
	//	private String diaChiNCC;
	//	private String soDienThoai;
	//	private String email;
	//	private String ghiChu;
	@Override
	public List<NhaCungCap> locNhaCungCap(String duLieuTim) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("NhaCungCap.locNhaCungCap", NhaCungCap.class).setParameter("duLieuTim", "%"+duLieuTim+"%")
				.getResultList();
	}
//	@NamedNativeQuery(name = "NhaCungCap.getMaNhaCungCapDB", query = "SELECT top 1 maNCC \n" +
//	        "FROM NhaCungCap\n" +
//	        "ORDER BY \n" +
//	        "CONVERT(DATE, SUBSTRING(maNCC, 4, 2) + '/' + SUBSTRING(maNCC, 6, 2) + '/' + SUBSTRING(maNCC, 8, 4), 103) DESC,\n" +
//	        "CAST(SUBSTRING(maNCC, 13, LEN(maNCC)) AS INT) DESC;", resultClass = String.class)
	@Override
	public String getMaNhaCungCapDB() throws RemoteException {
		return em.createNativeQuery("NhaCungCap.getMaNhaCungCapDB", String.class).getSingleResult().toString();
    }
}
