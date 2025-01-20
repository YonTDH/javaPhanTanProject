package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.SanPham;

import java.util.List;

@AllArgsConstructor
public class SanPham_DAO {
    private EntityManager em;

    public boolean addNewSanPham(SanPham sanPham) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(sanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean updateSanPham(SanPham sanPham) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            SanPham existingSanPham = em.find(SanPham.class, sanPham.getMaSanPham());
            if (existingSanPham == null) {
                transaction.rollback();
                return false;
            }
            em.merge(sanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean deleteSanPham(int maSanPham) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            SanPham sanPham = em.find(SanPham.class, maSanPham);
            if (sanPham != null) {
                em.remove(sanPham);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public SanPham findById(int maSanPham) {
        try {
            return em.find(SanPham.class, maSanPham);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SanPham> findAll() {
        try {
            return em.createQuery("SELECT s FROM SanPham s", SanPham.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SanPham> findByNhomSanPham(int maNhomSanPham) {
        try {
            return em.createQuery("SELECT s FROM SanPham s WHERE s.nhomSanPham.maNhomSanPham = :maNhomSanPham", SanPham.class)
                    .setParameter("maNhomSanPham", maNhomSanPham)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
