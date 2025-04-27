package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_SanPham;
import iuh.fit.models.SanPham;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.rmi.RemoteException;
import java.util.List;

public class DaoImpl_SanPham implements DAO_SanPham {
    private static final long serialVersionUID = 1L;
    private EntityManager em;
    public DaoImpl_SanPham() throws RemoteException {
        em = AppUtil.getEntityManager();
    }

    @Override
    public boolean createSanPham(SanPham sp) throws RemoteException {
        try {
            em.getTransaction().begin();
            em.persist(sp);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean deleteSanPham(String maSP) throws RemoteException {
        try {
            SanPham sp = em.find(SanPham.class, maSP);
            if (sp != null) {
                em.getTransaction().begin();
                em.remove(sp);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean updateSanPham(SanPham sp) throws RemoteException {
        try {
            em.getTransaction().begin();
            em.merge(sp);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public SanPham getSanPhamtheoMa(String maSP) throws RemoteException {
        return em.find(SanPham.class, maSP);
    }

    @Override
    public SanPham getSanPhamtheoTen(String tenSP) throws RemoteException {
        try {
            TypedQuery<SanPham> query = em.createQuery(
                    "SELECT sp FROM SanPham sp WHERE sp.tenSanPham = :ten", SanPham.class);
            query.setParameter("ten", tenSP);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<SanPham> getAllSanPham() throws RemoteException {
        return em.createNamedQuery("SanPham.getAllSanPham", SanPham.class).getResultList();
    }
}