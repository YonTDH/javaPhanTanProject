package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.NhomSanPham;

import java.util.List;

@AllArgsConstructor
public class NhomSanPham_DAO {
    private EntityManager em;

    public boolean addNewNhomSanPham(NhomSanPham nhomSanPham) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(nhomSanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean updateNhomSanPham(NhomSanPham nhomSanPham) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            NhomSanPham existingNhomSanPham = em.find(NhomSanPham.class, nhomSanPham.getMaNhomSanPham());
            if (existingNhomSanPham == null) {
                transaction.rollback();
                return false;
            }
            em.merge(nhomSanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean deleteNhomSanPham(int maNhomSanPham) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            NhomSanPham nhomSanPham = em.find(NhomSanPham.class, maNhomSanPham);
            if (nhomSanPham != null) {
                em.remove(nhomSanPham);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public NhomSanPham findById(int maNhomSanPham) {
        try {
            return em.find(NhomSanPham.class, maNhomSanPham);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<NhomSanPham> findAll() {
        try {
            return em.createQuery("SELECT n FROM NhomSanPham n", NhomSanPham.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NhomSanPham> findByTenNhomSanPham(String tenNhomSanPham) {
        try {
            return em.createQuery("SELECT n FROM NhomSanPham n WHERE n.tenNhomSanPham = :tenNhomSanPham", NhomSanPham.class)
                    .setParameter("tenNhomSanPham", tenNhomSanPham)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
