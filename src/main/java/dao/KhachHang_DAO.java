package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.KhachHang;

import java.util.List;

@AllArgsConstructor
public class KhachHang_DAO {
    private EntityManager em;

    public boolean addNewKhachHang(KhachHang khachHang) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(khachHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean updateKhachHang(KhachHang khachHang) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            KhachHang existingKhachHang = em.find(KhachHang.class, khachHang.getMaKhachHang());
            if (existingKhachHang == null) {
                transaction.rollback();
                return false;
            }
            em.merge(khachHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean deleteKhachHang(int maKhachHang) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            KhachHang khachHang = em.find(KhachHang.class, maKhachHang);
            if (khachHang != null) {
                em.remove(khachHang);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public KhachHang findById(int maKhachHang) {
        try {
            return em.find(KhachHang.class, maKhachHang);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<KhachHang> findAll() {
        try {
            return em.createQuery("SELECT k FROM KhachHang k", KhachHang.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public KhachHang findByTenKhachHang(String tenKhachHang) {
        try {
            return em.createQuery("SELECT k FROM KhachHang k WHERE k.tenKhachHang = :tenKhachHang", KhachHang.class)
                    .setParameter("tenKhachHang", tenKhachHang)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
