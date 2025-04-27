package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_NhomKhuyenMai;
import iuh.fit.models.KhuyenMai;
import iuh.fit.models.NhomKhuyenMai;
import iuh.fit.models.NhomSanPham;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DaoImpl_NhomKhuyenMai extends UnicastRemoteObject implements DAO_NhomKhuyenMai {
    private EntityManager em;

    public DaoImpl_NhomKhuyenMai() throws RemoteException {
        this.em = AppUtil.getEntityManager();
    }

    public DaoImpl_NhomKhuyenMai(EntityManager em) throws RemoteException {
        this.em = em;
    }


    @Override
    public List<NhomKhuyenMai> getAllNhomKM() throws RemoteException {
        try {
            TypedQuery<NhomKhuyenMai> query = em.createNamedQuery("NhomKhuyenMai.getAllNhomKM", NhomKhuyenMai.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean createNhomKM(KhuyenMai s, NhomSanPham nsp) throws RemoteException {
        try {
            em.getTransaction().begin();

            NhomKhuyenMai nhomKM = new NhomKhuyenMai();
            nhomKM.setKhuyenMai(s);
            nhomKM.setNhomSanPham(nsp);

            em.persist(nhomKM);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteNhomKM(KhuyenMai s) throws RemoteException {
        try {
            em.getTransaction().begin();

            Query query = em.createNamedQuery("deleteNhomKM");
            query.setParameter("s", s);
            query.executeUpdate();

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
