package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_ChiTietHoaDonDoi;
import iuh.fit.models.ChiTietHoaDonDoi;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.util.List;

public class DAOImpl_ChiTietHoaDon implements DAO_ChiTietHoaDonDoi {
    private EntityManager em;

    public DAOImpl_ChiTietHoaDon() {
        em = AppUtil.getEntityManager(); // Giả sử AppUtil trả về EntityManager.
    }

    public DAOImpl_ChiTietHoaDon(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<ChiTietHoaDonDoi> getAllChiTietDonDoi() throws RemoteException {
        try {
            return em.createQuery("FROM ChiTietHoaDonDoi", ChiTietHoaDonDoi.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy tất cả chi tiết hóa đơn đổi.", e);
        }
    }

    @Override
    public boolean createChiTietDonDoi(ChiTietHoaDonDoi cthd) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(cthd); // Lưu chi tiết hóa đơn đổi
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi thêm chi tiết hóa đơn đổi.", e);
        }
    }

    @Override
    public boolean deleteChiTietDonDoi(String maHD) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            ChiTietHoaDonDoi cthd = em.find(ChiTietHoaDonDoi.class, maHD); // Tìm theo mã hóa đơn
            if (cthd != null) {
                em.remove(cthd); // Xóa chi tiết hóa đơn đổi
                tr.commit();
                return true;
            }
            tr.commit();
            return false;
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi xóa chi tiết hóa đơn đổi.", e);
        }
    }

    @Override
    public ChiTietHoaDonDoi getHoaDontheoMa(String maHDD, String maSP) throws RemoteException {
        try {
            return em.createQuery("FROM ChiTietHoaDonDoi c WHERE c. = :maHDD AND c. = :maSP", ChiTietHoaDonDoi.class)
                    .setParameter("maHDD", maHDD)
                    .setParameter("maSP", maSP)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy chi tiết hóa đơn đổi theo mã.", e);
        }
    }
}
