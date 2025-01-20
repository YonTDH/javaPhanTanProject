package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.NhanVien;

import java.util.List;

@AllArgsConstructor
public class NhanVien_DAO {
    private EntityManager em;

    public boolean addNewNhanVien(NhanVien nhanVien) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(nhanVien);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean updateNhanVien(NhanVien nhanVien) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            NhanVien existingNhanVien = em.find(NhanVien.class, nhanVien.getMaNhanVien());
            if (existingNhanVien == null) {
                transaction.rollback();
                return false;
            }
            em.merge(nhanVien);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }


    public boolean deleteNhanVien(int maNhanVien) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            NhanVien nhanVien = em.find(NhanVien.class, maNhanVien);
            if (nhanVien != null) {
                em.remove(nhanVien);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public NhanVien findById(int maNhanVien) {
        try {
            return em.find(NhanVien.class, maNhanVien);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NhanVien> findAll() {
        try {
            return em.createQuery("SELECT n FROM NhanVien n", NhanVien.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NhanVien findByHoTen(String hoTenNV) {
        try {
            return em.createQuery("SELECT n FROM NhanVien n WHERE n.hoTenNV = :hoTenNV", NhanVien.class)
                    .setParameter("hoTenNV", hoTenNV)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
