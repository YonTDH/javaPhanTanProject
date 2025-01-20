package dao;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import model.VanPhongPham;

import java.util.List;

@AllArgsConstructor
public class VanPhongPham_DAO {
    private EntityManager em;

    public boolean addNewVanPhongPham(VanPhongPham vanPhongPham) {
        return new SanPham_DAO(em).addNewSanPham(vanPhongPham);
    }

    public boolean updateVanPhongPham(VanPhongPham vanPhongPham) {
        return new SanPham_DAO(em).updateSanPham(vanPhongPham);
    }

    public boolean deleteVanPhongPham(int maVanPhongPham) {
        return new SanPham_DAO(em).deleteSanPham(maVanPhongPham);
    }

    public VanPhongPham findById(int maVanPhongPham) {
        try {
            return em.find(VanPhongPham.class, maVanPhongPham);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<VanPhongPham> findAll() {
        try {
            return em.createQuery("SELECT v FROM VanPhongPham v", VanPhongPham.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<VanPhongPham> findByNoiSanXuat(String noiSanXuat) {
        try {
            return em.createQuery("SELECT v FROM VanPhongPham v WHERE v.noiSanXuat = :noiSanXuat", VanPhongPham.class)
                    .setParameter("noiSanXuat", noiSanXuat)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
