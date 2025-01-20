package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.KhuyenMai;

import java.util.List;

@AllArgsConstructor
public class KhuyenMai_DAO {
    private EntityManager em;

    public boolean addNewKhuyenMai(KhuyenMai khuyenMai) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(khuyenMai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean updateKhuyenMai(KhuyenMai khuyenMai) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            KhuyenMai existingKhuyenMai = em.find(KhuyenMai.class, khuyenMai.getMaKhuyenMai());
            if (existingKhuyenMai == null) {
                transaction.rollback();
                return false;
            }
            em.merge(khuyenMai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean deleteKhuyenMai(int maKhuyenMai) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            KhuyenMai khuyenMai = em.find(KhuyenMai.class, maKhuyenMai);
            if (khuyenMai != null) {
                em.remove(khuyenMai);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public KhuyenMai findById(int maKhuyenMai) {
        try {
            return em.find(KhuyenMai.class, maKhuyenMai);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<KhuyenMai> findAll() {
        try {
            return em.createQuery("SELECT k FROM KhuyenMai k", KhuyenMai.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public KhuyenMai findByTenKhuyenMai(String tenKhuyenMai) {
        try {
            return em.createQuery("SELECT k FROM KhuyenMai k WHERE k.tenKhuyenMai = :tenKhuyenMai", KhuyenMai.class)
                    .setParameter("tenKhuyenMai", tenKhuyenMai)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
