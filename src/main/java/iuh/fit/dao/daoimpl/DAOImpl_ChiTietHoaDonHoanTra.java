package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_ChiTietHoaDonHoanTra;
import iuh.fit.models.ChiTietHoanTra;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.util.List;

public class DAOImpl_ChiTietHoaDonHoanTra implements DAO_ChiTietHoaDonHoanTra {
    private EntityManager em;

    public DAOImpl_ChiTietHoaDonHoanTra() {
        em = AppUtil.getEntityManager();
    }

    public DAOImpl_ChiTietHoaDonHoanTra(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<ChiTietHoanTra> getAllChiTietHoanTra() throws RemoteException {
        try {
            return em.createQuery("FROM ChiTietHoanTra", ChiTietHoanTra.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy tất cả chi tiết hoàn trả.", e);
        }
    }

    @Override
    public boolean createChiTietHoanTra(ChiTietHoanTra ctht) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(ctht);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi thêm chi tiết hoàn trả.", e);
        }
    }

    @Override
    public boolean deleteChiTietHoanTra(String maHD) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            ChiTietHoanTra ctht = em.find(ChiTietHoanTra.class, maHD);
            if (ctht != null) {
                em.remove(ctht);
                tr.commit();
                return true;
            }
            tr.commit();
            return false;
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi xóa chi tiết hoàn trả.", e);
        }
    }

    @Override
    public ChiTietHoanTra getHoaDontheoMa(String maHD, String maSP) throws RemoteException {
        try {
            return em.createQuery(
                            "SELECT c FROM ChiTietHoanTra c WHERE c.hoaDonHoanTra.maHoaDonHoanTra = :maHD AND c.sanPham.maSanPham = :maSP",
                            ChiTietHoanTra.class
                    )
                    .setParameter("maHD", maHD)
                    .setParameter("maSP", maSP)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy chi tiết hoàn trả theo mã.", e);
        }
    }
}
