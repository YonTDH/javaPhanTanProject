package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_ChiTietHoaDonDoi;
import iuh.fit.models.ChiTietHoaDonDoi;
import iuh.fit.models.ChiTietHoaDonDoiId;
import iuh.fit.models.HoaDonDoiHang;
import iuh.fit.models.SanPham;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.util.List;

class DAOImpl_ChiTietHoaDonDoi implements DAO_ChiTietHoaDonDoi {
    private EntityManager em;

    public DAOImpl_ChiTietHoaDonDoi() {
        this.em = AppUtil.getEntityManager();
    }

    public DAOImpl_ChiTietHoaDonDoi(EntityManager em) {
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
            em.persist(cthd);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            throw new RemoteException("Lỗi khi thêm chi tiết hóa đơn đổi.", e);
        }
    }

    @Override
    public boolean deleteChiTietDonDoi(String maHD) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            ChiTietHoaDonDoi cthd = em.find(ChiTietHoaDonDoi.class, maHD);
            if (cthd != null) {
                em.remove(cthd);
                tr.commit();
                return true;
            }
            tr.commit();
            return false;
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            throw new RemoteException("Lỗi khi xóa chi tiết hóa đơn đổi.", e);
        }
    }

    @Override
    public ChiTietHoaDonDoi getHoaDontheoMa(String maHDD, String maSP) throws RemoteException {
        try {
            // Tìm HoaDonDoiHang theo maHDD
            HoaDonDoiHang hoaDonDoiHang = em.createQuery(
                            "FROM HoaDonDoiHang h WHERE h.maHoaDonDoi = :maHDD", HoaDonDoiHang.class)
                    .setParameter("maHDD", maHDD)
                    .getSingleResult();

            // Tìm SanPham theo maSP
            SanPham sanPham = em.createQuery(
                            "FROM SanPham s WHERE s.maSanPham = :maSP", SanPham.class)
                    .setParameter("maSP", maSP)
                    .getSingleResult();

            // Tạo ChiTietHoaDonDoiId với các đối tượng vừa tìm được
            ChiTietHoaDonDoiId id = new ChiTietHoaDonDoiId(hoaDonDoiHang, sanPham);

            // Truy vấn ChiTietHoaDonDoi theo id
            return em.createQuery(
                            "FROM ChiTietHoaDonDoi c WHERE c.id = :id", ChiTietHoaDonDoi.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy chi tiết hóa đơn đổi theo mã.", e);
        }
    }
}