package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_KhuyenMai;
import iuh.fit.models.KhachHang;
import iuh.fit.models.KhuyenMai;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;

import java.rmi.RemoteException;
import java.util.List;

public class DaoImpl_KhuyenMai implements DAO_KhuyenMai {

    private EntityManager em;

    public DaoImpl_KhuyenMai() {
        em = AppUtil.getEntityManager();
    }
    public DaoImpl_KhuyenMai(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<KhuyenMai> getAlltbKM() throws RemoteException {
        return em.createQuery("SELECT k FROM KhuyenMai k", KhuyenMai.class).getResultList();
    }

    @Override
    public boolean createKhuyenMai(KhuyenMai khuyenMai) throws RemoteException {
        try {
            em.getTransaction().begin();
            em.persist(khuyenMai);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    //Get ds KM đang hoạt động?
    @Override
    public List<KhuyenMai> getAlltbKMTheoDangChay() throws RemoteException {
        return em.createQuery("SELECT k FROM KhuyenMai k WHERE k.trangThai = 'Đang hoạt động' ", KhuyenMai.class)
             .getResultList();
    }

    @Override
    public KhuyenMai getKMtheoMa(String ma) throws RemoteException {
        return em.createQuery("SELECT k FROM KhuyenMai k WHERE k.maKhuyenMai = :ma", KhuyenMai.class)
                .setParameter("ma", ma).getSingleResult();
    }

    @Override
    public boolean updateKhuyenMai(KhuyenMai s) throws RemoteException {
        try {
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }


    //Đổi tên function rõ hơn
    @Override
    public String getMaKhuyenMaiTNDB() throws RemoteException {
        return "";
    }

    //Trùng -- gộp chung vào update Khuyến mãi
    @Override
    public boolean updateTinhTrang(KhuyenMai km) throws RemoteException {
        return false;
    }

    //Trùng
    @Override
    public KhuyenMai getKmTheoMa(String maKM) throws RemoteException {
        return null;
    }
}
