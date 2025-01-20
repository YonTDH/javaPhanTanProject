package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.NhaCungCap;

import java.util.List;

@AllArgsConstructor
public class NhaCungCap_DAO {
    private EntityManager em;

    public boolean addNewNhaCungCap(NhaCungCap nhaCungCap) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(nhaCungCap);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean updateNhaCungCap(NhaCungCap nhaCungCap) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            NhaCungCap existingNhaCungCap = em.find(NhaCungCap.class, nhaCungCap.getMaNCC());
            if (existingNhaCungCap == null) {
                transaction.rollback();
                return false;
            }
            em.merge(nhaCungCap);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean deleteNhaCungCap(int maNCC) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            NhaCungCap nhaCungCap = em.find(NhaCungCap.class, maNCC);
            if (nhaCungCap != null) {
                em.remove(nhaCungCap);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public NhaCungCap findById(int maNCC) {
        try {
            return em.find(NhaCungCap.class, maNCC);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NhaCungCap> findAll() {
        try {
            return em.createQuery("SELECT n FROM NhaCungCap n", NhaCungCap.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NhaCungCap findByTenNCC(String tenNCC) {
        try {
            return em.createQuery("SELECT n FROM NhaCungCap n WHERE n.tenNCC = :tenNCC", NhaCungCap.class)
                    .setParameter("tenNCC", tenNCC)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
