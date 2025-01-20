package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.MauSac;

import java.util.List;

@AllArgsConstructor
public class MauSac_DAO {
    private EntityManager em;

    public boolean addNewMauSac(MauSac mauSac) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(mauSac);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean updateMauSac(MauSac mauSac) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            MauSac existingMauSac = em.find(MauSac.class, mauSac.getMaMau());
            if (existingMauSac == null) {
                transaction.rollback();
                return false;
            }
            em.merge(mauSac);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean deleteMauSac(int maMau) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            MauSac mauSac = em.find(MauSac.class, maMau);
            if (mauSac != null) {
                em.remove(mauSac);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public MauSac findById(int maMau) {
        try {
            return em.find(MauSac.class, maMau);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MauSac> findAll() {
        try {
            return em.createQuery("SELECT m FROM MauSac m", MauSac.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
