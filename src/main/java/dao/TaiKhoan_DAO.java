package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.TaiKhoan;

import java.util.List;

@AllArgsConstructor
public class TaiKhoan_DAO {
    private EntityManager em;

    public boolean add(TaiKhoan taiKhoan) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(taiKhoan);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean delete(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            TaiKhoan taiKhoan = em.find(TaiKhoan.class, id);
            if (taiKhoan != null) {
                em.remove(taiKhoan);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean update(TaiKhoan taiKhoan) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            TaiKhoan existingTaiKhoan = em.find(TaiKhoan.class, taiKhoan.getId());
            if (existingTaiKhoan != null) {
                em.merge(taiKhoan);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public TaiKhoan findById(int id) {
        try {
            return em.find(TaiKhoan.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TaiKhoan> findAll() {
        try {
            return em.createQuery("SELECT t FROM TaiKhoan t", TaiKhoan.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TaiKhoan findByUsername(String username) {
        try {
            return em.createQuery("SELECT t FROM TaiKhoan t WHERE t.tenDangNhap = :username", TaiKhoan.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
