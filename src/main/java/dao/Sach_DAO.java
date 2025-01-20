package dao;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import model.Sach;

import java.util.List;

@AllArgsConstructor
public class Sach_DAO {
    private EntityManager em;

    public boolean addNewSach(Sach sach) {
        return new SanPham_DAO(em).addNewSanPham(sach);
    }

    public boolean updateSach(Sach sach) {
        return new SanPham_DAO(em).updateSanPham(sach);
    }

    public boolean deleteSach(int maSach) {
        return new SanPham_DAO(em).deleteSanPham(maSach);
    }

    public Sach findById(int maSach) {
        try {
            return em.find(Sach.class, maSach);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Sach> findAll() {
        try {
            return em.createQuery("SELECT s FROM Sach s", Sach.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Sach> findByTacGia(String tacGia) {
        try {
            return em.createQuery("SELECT s FROM Sach s WHERE s.tacGia = :tacGia", Sach.class)
                    .setParameter("tacGia", tacGia)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
