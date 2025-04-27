package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_KhachHang;
import iuh.fit.models.KhachHang;
import iuh.fit.models.NhomKhachHang;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public class DaoImpl_KhachHang implements DAO_KhachHang {
    private EntityManager em;

    public DaoImpl_KhachHang(){
        em = AppUtil.getEntityManager();
    }

    public DaoImpl_KhachHang(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<KhachHang> getAllKhachHang_20() throws RemoteException {
        return em.createQuery("SELECT k FROM KhachHang k", KhachHang.class)
                .setMaxResults(20).getResultList();
    }

    @Override
    public List<KhachHang> getAllKhachHang() throws RemoteException {
        return em.createQuery("SELECT k FROM KhachHang k", KhachHang.class).getResultList();
    }

    @Override
    public KhachHang getKHTheoMa(String ma) throws RemoteException {
        return em.createQuery("SELECT k FROM KhachHang k WHERE k.maKhachHang = :ma", KhachHang.class)
                .setParameter("ma", ma).getSingleResult();
    }

    @Override
    public KhachHang getKHTheoSDT(String soDienThoai) throws RemoteException {
        return em.createQuery("SELECT k FROM KhachHang k WHERE k.soDienThoai = :sdt", KhachHang.class)
                .setParameter("sdt", soDienThoai).getSingleResult();
    }

    @Override
    public boolean themKhachHang(KhachHang cus) throws RemoteException {
        try {
            em.getTransaction().begin();
            em.persist(cus);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean updateKhachHang(KhachHang cus) throws RemoteException {
        try {
            em.getTransaction().begin();
            em.merge(cus);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public List<KhachHang> timKhachHangTheoThongTin(String data) throws RemoteException {
        return em.createQuery(
                        "SELECT k FROM KhachHang k WHERE k.hoTenKH LIKE :data OR k.email LIKE :data OR k.soDienThoai LIKE :data",
                        KhachHang.class)
                .setParameter("data", "%" + data + "%").getResultList();
    }

    @Override
    public void capNhatNhomKhachHang(String maKhachHang, NhomKhachHang nhomMoi) throws RemoteException {
        try {
            em.getTransaction().begin();

            if (maKhachHang != null && !maKhachHang.isEmpty()) {
                KhachHang khachHang = em.createQuery("SELECT k FROM KhachHang k WHERE k.maKhachHang = :ma", KhachHang.class)
                        .setParameter("ma", maKhachHang)
                        .getSingleResult();
                if (khachHang != null) {
                    khachHang.setNhomKhachHang(nhomMoi);
                    em.merge(khachHang);
                }
            } else {
                List<KhachHang> danhSachKhachHang = em.createQuery("SELECT k FROM KhachHang k", KhachHang.class).getResultList();
                for (KhachHang khachHang : danhSachKhachHang) {
                    khachHang.setNhomKhachHang(nhomMoi);
                    em.merge(khachHang);
                }
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RemoteException("Cập nhật nhóm khách hàng thất bại", e);
        }
    }


    @Override
    public List<KhachHang> getKhachHangTrong1Thang() throws RemoteException {
       return null;
    }

    @Override
    public List<KhachHang> locDuLieuKhachHang(String data) throws RemoteException {
        return em.createQuery("SELECT k FROM KhachHang k WHERE k.nhomKhachHang = :data", KhachHang.class)
                .setParameter("data", Enum.valueOf(iuh.fit.models.NhomKhachHang.class, data))
                .getResultList();
    }
}
