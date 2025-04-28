/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import dao.DAO_VanPhongPham;
import entity.VanPhongPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class DAO_VanPhongPham_Impl extends UnicastRemoteObject implements DAO_VanPhongPham {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2702375686001373943L;
	private EntityManager em;
	
	public DAO_VanPhongPham_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
	}
	
	@Override
	public List<VanPhongPham> getAllVanPhongPhan_20() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("VanPhongPham.getAllVanPhongPham_20", VanPhongPham.class)
				.setMaxResults(20)
				.getResultList();
	}

	@Override
	public List<VanPhongPham> getAllVanPhongPhan() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("VanPhongPham.getAllVanPhongPham", VanPhongPham.class).getResultList();
	}

	@Override
	public boolean update(VanPhongPham vpp) throws RemoteException {
		// TODO Auto-generated method stub
		VanPhongPham vppMoi = em.find(VanPhongPham.class, vpp.getMaSanPham());
		vppMoi.setTenSanPham(vpp.getTenSanPham());
		vppMoi.setNhomSanPham(vpp.getNhomSanPham());
		vppMoi.setNhaCungCap(vpp.getNhaCungCap());
		vppMoi.setSoLuongTon(vpp.getSoLuongTon());
		vppMoi.setDonGiaNhap(vpp.getDonGiaNhap());
		vppMoi.setMoTa(vpp.getMoTa());
		vppMoi.setDonGiaBan(vpp.getDonGiaBan());
		vppMoi.setMauSac(vpp.getMauSac());
		vppMoi.setNoiSanXuat(vpp.getNoiSanXuat());
		vppMoi.setGiamGia(vpp.getGiamGia());
		
		EntityTransaction tx = em.getTransaction();
		try {
            tx.begin();
            
            em.persist(vppMoi);
            
            tx.commit();
            
            return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public VanPhongPham getVPPtheoMa(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		return em.find(VanPhongPham.class, ma);
	}

	@Override
	public VanPhongPham getVpptheoTen(String ten) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("VanPhongPham.getVpptheoTen", VanPhongPham.class).setParameter("ten", ten)
				.getSingleResult();
	}

	@Override
	public List<VanPhongPham> getDsVpptheoMa(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("VanPhongPham.getVPPtheoMa", VanPhongPham.class).setParameter("ma", ma)
				.getResultList();
	}

	@Override
	public boolean insertVpp(VanPhongPham vpp) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.persist(vpp);

			tx.commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}
    
}
