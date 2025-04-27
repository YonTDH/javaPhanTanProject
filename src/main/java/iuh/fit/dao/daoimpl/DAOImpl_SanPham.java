package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_SanPham;
import iuh.fit.models.SanPham;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.util.List;

public class DAOImpl_SanPham implements DAO_SanPham {
    private EntityManager em;

    public DAOImpl_SanPham() {
        em = AppUtil.getEntityManager();
    }

    public DAOImpl_SanPham(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean createSanPham(SanPham sp) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(sp);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi tạo sản phẩm.", e);
        }
    }

    @Override
    public boolean deleteSanPham(String maSP) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            SanPham sp = em.find(SanPham.class, maSP);
            if (sp != null) {
                em.remove(sp);
                tr.commit();
                return true;
            }
            tr.commit();
            return false;
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi xóa sản phẩm.", e);
        }
    }

    @Override
    public boolean updateSanPham(SanPham sp) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(sp);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi cập nhật sản phẩm.", e);
        }
    }

    @Override
    public SanPham getSanPhamtheoMa(String maSP) throws RemoteException {
        try {
            return em.find(SanPham.class, maSP);
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy sản phẩm theo mã.", e);
        }
    }

    @Override
    public SanPham getSanPhamtheoTen(String tenSP) throws RemoteException {
        try {
            return em.createQuery("FROM SanPham s WHERE s.tenSanPham = :ten", SanPham.class)
                    .setParameter("ten", tenSP)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy sản phẩm theo tên.", e);
        }
    }

    @Override
    public List<SanPham> getAllSanPham() throws RemoteException {
        try {
            return em.createQuery("FROM SanPham", SanPham.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy tất cả sản phẩm.", e);
        }
    }
}
