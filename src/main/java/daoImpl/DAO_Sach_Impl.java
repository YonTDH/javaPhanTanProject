package daoImpl;

import dao.DAO_Sach;
import entity.Sach;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DAO_Sach_Impl extends UnicastRemoteObject implements DAO_Sach {

	private static final long serialVersionUID = -8887115316213807751L;
	private EntityManager em;
	
	public DAO_Sach_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
	}
	
	@Override
	public List<Sach> getAlltbSach_20() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Sach.getAlltbSach_20", Sach.class)
				.setMaxResults(20)
				.getResultList();
	}

	@Override
	public List<Sach> getAlltbSach() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Sach.getAlltbSach_20", Sach.class).getResultList();
	}

//	  protected String maSanPham;
//	  protected String tenSanPham;
//    protected NhomSanPham nhomSanPham;
//    protected NhaCungCap nhaCungCap;
//    protected int soLuongTon;
//    protected double donGiaNhap;
//    protected String moTa;
//    protected String tinhTrang;
//    protected double donGiaBan;
//    protected double VAT;
//    protected LocalDateTime ngayTao;
//    protected double giamGia;
//	  private String tacGia;
//    private int namXuatBan;
//    private String nhaXuatBan;
//    private int soTrang;
    
	@Override
	public boolean createSach(Sach s) throws RemoteException {
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

	@Override
	public void importExcel(String fileName, String sheetName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sach getSachtheoMa(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		return em.find(Sach.class, ma);
	}

	@Override
	public boolean updateSach(Sach s) throws RemoteException {
		// TODO Auto-generated method stub
		Sach sMoi = em.find(Sach.class, s.getMaSanPham());
		sMoi.setTenSanPham(s.getTenSanPham());
		sMoi.setNhomSanPham(s.getNhomSanPham());
		sMoi.setTacGia(s.getTacGia());
		sMoi.setSoLuongTon(s.getSoLuongTon());
		sMoi.setDonGiaNhap(s.getDonGiaNhap());
		sMoi.setMoTa(s.getMoTa());
		sMoi.setNamXuatBan(s.getNamXuatBan());
		sMoi.setSoTrang(s.getSoTrang());
		sMoi.setNhaXuatBan(s.getNhaXuatBan());
		sMoi.setGiamGia(s.getGiamGia());
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
    
}
