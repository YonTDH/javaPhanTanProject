package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_Sach;
import iuh.fit.models.Sach;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.util.List;

public class DAOImpl_Sach implements DAO_Sach {
    private EntityManager em;

    public DAOImpl_Sach() {
        em = AppUtil.getEntityManager();
    }

    public DAOImpl_Sach(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Sach> getAlltbSach_20() throws RemoteException {
        try {
            return em.createQuery("SELECT s FROM Sach s ORDER BY s.maSanPham DESC", Sach.class)
                    .setMaxResults(20)
                    .getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy 20 sách mới nhất.", e);
        }
    }

    @Override
    public List<Sach> getAlltbSach() throws RemoteException {
        try {
            return em.createQuery("FROM Sach", Sach.class).getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy tất cả sách.", e);
        }
    }

    @Override
    public boolean createSach(Sach s) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(s);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi tạo sách.", e);
        }
    }

    @Override
    public void importExcel(String fileName, String sheetName) throws RemoteException {
        // Gợi ý: dùng Apache POI để đọc Excel và persist vào DB
        // Mình để khung sẵn cho bạn triển khai sau.
        throw new UnsupportedOperationException("Chưa triển khai import Excel.");
    }

    @Override
    public Sach getSachtheoMa(String ma) throws RemoteException {
        try {
            return em.find(Sach.class, ma);
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy sách theo mã.", e);
        }
    }

    @Override
    public boolean updateSach(Sach s) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(s);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi cập nhật sách.", e);
        }
    }
}
