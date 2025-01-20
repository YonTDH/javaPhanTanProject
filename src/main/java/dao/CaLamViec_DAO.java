package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.CaLamViec;
import model.TaiKhoan;

import java.util.List;

@AllArgsConstructor
public class CaLamViec_DAO {
    private EntityManager em;

    public boolean addNewCaLamViec(CaLamViec caLamViec) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(caLamViec);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean updateCaLamViec(CaLamViec caLamViec) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            CaLamViec existingCaLam = em.find(CaLamViec.class, caLamViec.getMaCa());
            if (existingCaLam != null) {
                em.merge(caLamViec);
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

    public boolean deleteCaLamViec(int maCa) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            CaLamViec caLamViec = em.find(CaLamViec.class, maCa);
            if (caLamViec != null) {
                em.remove(caLamViec);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public CaLamViec findById(int maCa) {
        try {
            return em.find(CaLamViec.class, maCa);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CaLamViec> findAll() {
        try {
            return em.createQuery("SELECT c FROM CaLamViec c", CaLamViec.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CaLamViec findByTenCa(String tenCa) {
        try {
            return em.createQuery("SELECT c FROM CaLamViec c WHERE c.tenCa = :tenCa", CaLamViec.class)
                    .setParameter("tenCa", tenCa)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
