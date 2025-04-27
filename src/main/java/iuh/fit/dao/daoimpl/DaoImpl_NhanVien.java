package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_NhanVien;
import iuh.fit.models.ChucVu;
import iuh.fit.models.NhanVien;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.rmi.RemoteException;
import java.util.List;

public class DaoImpl_NhanVien implements DAO_NhanVien {
    private EntityManager em;

    public DaoImpl_NhanVien() {
        this.em = AppUtil.getEntityManager();
    }

    public DaoImpl_NhanVien(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<NhanVien> getAllNhanVien_20() throws RemoteException {
        try {
            TypedQuery<NhanVien> query = em.createQuery("SELECT nv FROM NhanVien nv ", NhanVien.class);
            query.setMaxResults(20);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<NhanVien> getAllNhanVien() throws RemoteException {
        try {
            TypedQuery<NhanVien> query = em.createQuery("SELECT nv FROM NhanVien nv", NhanVien.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<NhanVien> getAllNhanVienTheoCa(String caLv) throws RemoteException {
        try {
            TypedQuery<NhanVien> query = em.createQuery(
                    "SELECT nv FROM NhanVien nv WHERE nv.caLamViec.maCa = :caLv", NhanVien.class);
            query.setParameter("caLv", caLv);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public NhanVien getNVTheoMa(String maNV) throws RemoteException {
        try {
            return em.find(NhanVien.class, maNV);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean themNhanVien(NhanVien nv) throws RemoteException {
        try {
            em.getTransaction().begin();
            em.persist(nv);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateNhanVien(String maNVSua, NhanVien nvMoi) throws RemoteException {
        try {
            em.getTransaction().begin();
            NhanVien nvCu = em.find(NhanVien.class, maNVSua);
            if (nvCu != null) {
                nvCu.setHoTenNV(nvMoi.getHoTenNV());
                nvCu.setSoDienThoai(nvMoi.getSoDienThoai());
                nvCu.setEmail(nvMoi.getEmail());
                nvCu.setCaLamViec(nvMoi.getCaLamViec());
                nvCu.setTinhTrangLamViec(nvMoi.getTinhTrangLamViec());
                nvCu.setChucVu(nvMoi.getChucVu());
                nvCu.setTaiKhoan(nvMoi.getTaiKhoan());
                em.merge(nvCu);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<NhanVien> locNhanVien(String duLieuTim) throws RemoteException {
        try {
            TypedQuery<NhanVien> query = em.createQuery(
                    "SELECT nv FROM NhanVien nv WHERE nv.hoTenNV " +
                            "LIKE :duLieuTim OR nv.soDienThoai LIKE :duLieuTim", NhanVien.class);
            query.setParameter("duLieuTim", "%" + duLieuTim + "%");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<NhanVien> locNhanVienTheoChucVu(ChucVu duLieuTim) throws RemoteException {
        try {
            TypedQuery<NhanVien> query = em.createQuery(
                    "SELECT nv FROM NhanVien nv WHERE nv.chucVu = :chucVu", NhanVien.class);
            query.setParameter("chucVu", duLieuTim);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public String getMaNhanVienQLDB() throws RemoteException {
        try {
            Query query = em.createQuery(
                    "SELECT nv.maNhanVien FROM NhanVien nv WHERE nv.chucVu = :chucVu");
            query.setParameter("chucVu", ChucVu.QUANLY);
            query.setMaxResults(1);
            return (String) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String getMaNhanVienTNDB() throws RemoteException {
        try {
            Query query = em.createQuery(
                    "SELECT nv.maNhanVien FROM NhanVien nv WHERE nv.chucVu = :chucVu");
            query.setParameter("chucVu", ChucVu.THUNGAN);
            query.setMaxResults(1);
            return (String) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String getNhanVienTheoCa(String email) throws RemoteException {
        try {
            Query query = em.createQuery(
                    "SELECT nv.caLamViec.maCa FROM NhanVien nv WHERE nv.email = :email");
            query.setParameter("email", email);
            return (String) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
