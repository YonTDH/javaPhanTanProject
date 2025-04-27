package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_MauSac;
import iuh.fit.models.MauSac;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;

import java.rmi.RemoteException;
import java.util.List;

public class DAOImpl_MauSac implements DAO_MauSac {
    private EntityManager em;

    public DAOImpl_MauSac() {
        em = AppUtil.getEntityManager();
    }

    public DAOImpl_MauSac(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<MauSac> getAlltbMauSac() throws RemoteException {
        try {
            return em.createQuery("FROM MauSac", MauSac.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy tất cả màu sắc.", e);
        }
    }

    @Override
    public MauSac getMauSactheoMa(String ma) throws RemoteException {
        try {
            return em.find(MauSac.class, ma);
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi tìm màu sắc theo mã.", e);
        }
    }

    @Override
    public MauSac getMauSactheoTen(String ten) throws RemoteException {
        try {
            return em.createQuery("FROM MauSac m WHERE m.tenMauSac = :ten", MauSac.class)
                    .setParameter("ten", ten)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi tìm màu sắc theo tên.", e);
        }
    }
}
