package daoImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import dao.DAO_NhanVien;
import entity.ChucVu;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class DAO_NhanVien_Impl extends UnicastRemoteObject implements DAO_NhanVien {

	private static final long serialVersionUID = -8896963786098893491L;
	private EntityManager em;

	public DAO_NhanVien_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer").createEntityManager();
	}
	
	@Override
	public List<NhanVien> getAllNhanVien_20() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("NhanVien.getAllNhanVien_20", NhanVien.class)
				.setMaxResults(20)
				.getResultList();
	}

	@Override
	public List<NhanVien> getAllNhanVien() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("NhanVien.getAllNhanVien", NhanVien.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NhanVien> getAllNhanVienTheoCa(String caLv) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNativeQuery("NhanVien.getAllNhanVienTheoCa", NhanVien.class).setParameter(1, caLv)
				.getResultList();
	}

	@Override
	public NhanVien getNVTheoMa(String maNV) throws RemoteException {
		// TODO Auto-generated method stub
		return em.find(NhanVien.class, maNV);
	}

	@Override
	public boolean themNhanVien(NhanVien nv) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.persist(nv);

			tx.commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateNhanVien(String maNVSua, NhanVien nvMoi) throws RemoteException {
		// TODO Auto-generated method stub
		NhanVien nv = em.find(NhanVien.class, maNVSua);
		nv.setHoTenNV(nvMoi.getHoTenNV());
		nv.setSoDienThoai(nvMoi.getSoDienThoai());
		nv.setGioiTinh(nvMoi.getGioiTinh());
		nv.setEmail(nv.getEmail());
		nv.setTinhTrangLamViec(nvMoi.getTinhTrangLamViec());
		nv.setCaLamViec(nvMoi.getCaLamViec());
		nv.setChucVu(nvMoi.getChucVu());

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.merge(nv);

			tx.commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

//	private String maNhanVien;
//	private String hoTenNV;
//	private LocalDate ngaySinh;
//	private String soDienThoai;
//	private String gioiTinh;
//	private String email;
//	private TaiKhoan taiKhoan;
//	private int tinhTrangLamViec;
//	private CaLamViec caLamViec;
//	private ChucVu chucVu;

	@Override
	public List<NhanVien> locNhanVien(String duLieuTim) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("NhanVien.locNhanVien", NhanVien.class).setParameter("value", "%"+duLieuTim+"%")
				.getResultList();
	}

//	private ChucVu chucVu;
	@Override
	public List<NhanVien> locNhanVienTheoChucVu(ChucVu duLieuTim) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("NhanVien.locNhanVienTheoChucVu", NhanVien.class).setParameter("value", duLieuTim)
				.getResultList();
	}

	@Override
	public String getMaNhanVienQLDB() throws RemoteException {
		// TODO Auto-generated method stub
		String queryString = "SELECT top 1 maNhanVien\n" + "FROM NhanVien\n" + "WHERE maNhanVien like 'QL%'\n"
				+ "ORDER BY CONVERT(DATE, RIGHT(LEFT(maNhanVien, 8), 4) + SUBSTRING(maNhanVien, 5, 2) \n"
				+ "+ RIGHT(maNhanVien, 2), 112) DESC, CAST(SUBSTRING(maNhanVien, 11, LEN(maNhanVien)) AS INT) \n"
				+ "DESC";
		Query query = em.createQuery(queryString, NhanVien.class);
		return query.toString();
	}

	@Override
	public String getMaNhanVienTNDB() throws RemoteException {
		// TODO Auto-generated method stub
		String queryString = "SELECT top 1 maNhanVien\n" + "FROM NhanVien\n" + "WHERE maNhanVien like 'TN%'\n"
				+ "ORDER BY CONVERT(DATE, RIGHT(LEFT(maNhanVien, 8), 4) + SUBSTRING(maNhanVien, 5, 2) \n"
				+ "+ RIGHT(maNhanVien, 2), 112) DESC, CAST(SUBSTRING(maNhanVien, 11, LEN(maNhanVien)) AS INT) \n"
				+ "DESC";
		Query query = em.createNativeQuery(queryString, NhanVien.class);
		return query.toString();
	}

//	private CaLamViec caLamViec;
	@Override
	public String getNhanVienTheoCa(String caLamViec) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("NhanVien.getNhanVienTheoCa", NhanVien.class).setParameter("value", caLamViec)
				.toString();
	}

}
