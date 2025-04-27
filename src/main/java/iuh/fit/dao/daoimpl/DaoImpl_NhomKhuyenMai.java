package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_NhomKhuyenMai;
import iuh.fit.models.KhuyenMai;
import iuh.fit.models.NhomKhuyenMai;
import iuh.fit.models.NhomSanPham;
import jakarta.persistence.EntityManager;

import java.rmi.RemoteException;
import java.util.List;

public class DaoImpl_NhomKhuyenMai implements DAO_NhomKhuyenMai {
    EntityManager em;
    @Override
    public List<NhomKhuyenMai> getAllNhomKM() throws RemoteException {
        try {
            return em.createQuery("SELECT n FROM NhomKhuyenMai n", NhomKhuyenMai.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Error fetching NhomKhuyenMai", e);
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
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    @Override
    public boolean deleteNhomKM(KhuyenMai s) throws RemoteException {
        try {
            em.getTransaction().begin();
            NhomKhuyenMai nhomKM = em.createQuery(
                            "SELECT n FROM NhomKhuyenMai n WHERE n.khuyenMai = :khuyenMai", NhomKhuyenMai.class)
                    .setParameter("khuyenMai", s)
                    .getSingleResult();
            if (nhomKM != null) {
                em.remove(nhomKM);
                em.getTransaction().commit();
                return true;
            }
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }
}
