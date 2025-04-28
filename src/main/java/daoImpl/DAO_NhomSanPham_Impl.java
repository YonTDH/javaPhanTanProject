/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import dao.DAO_NhomSanPham;
import entity.NhomSanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author nguyen chau tai
 */
public class DAO_NhomSanPham_Impl  extends UnicastRemoteObject implements DAO_NhomSanPham {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2992187727791305003L;
	private EntityManager em;
	
	public DAO_NhomSanPham_Impl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("QuanLiNhaSachONEEIGHTServer")
				.createEntityManager();
	}

	@Override
	public List<NhomSanPham> getAllNhomSanPham() throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("NhomSanPham.getAllNhomSanPham", NhomSanPham.class).getResultList();
	}

	@Override
	public NhomSanPham getNsptheoTen(String ten) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("NhomSanPham.getNsptheoTen", NhomSanPham.class).setParameter("ten", ten)
				.getSingleResult();
	}

	@Override
	public NhomSanPham getNspTheoMa(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("NhomSanPham.getNspTheoMa", NhomSanPham.class).setParameter("ma", ma)
				.getSingleResult();
	}
	
}
