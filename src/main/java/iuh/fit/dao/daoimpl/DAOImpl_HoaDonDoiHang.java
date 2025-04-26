package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_HoaDonDoiHang;
import iuh.fit.models.HoaDonDoiHang;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.util.List;

public class DAOImpl_HoaDonDoiHang implements DAO_HoaDonDoiHang {
    private EntityManager em;

    public DAOImpl_HoaDonDoiHang(EntityManager em) {
        this.em = em;
    }

    public DAOImpl_HoaDonDoiHang() {
        em = AppUtil.getEntityManager();
    }

    @Override
    public List<HoaDonDoiHang> getAllHoaDonDoiHang_20() throws RemoteException {
        return em.createQuery("FROM HoaDonDoiHang", HoaDonDoiHang.class)
                .setMaxResults(20)
                .getResultList();
    }

    @Override
    public List<HoaDonDoiHang> getAllHoaDonDoiHang() throws RemoteException {
        return em.createQuery("FROM HoaDonDoiHang", HoaDonDoiHang.class)
                .getResultList();
    }

    @Override
    public boolean createHoaDonDoiHang(HoaDonDoiHang hd) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(hd);
            tr.commit();
            return true;
        } catch (RuntimeException e) {
            tr.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateHoaDonDoiHang(HoaDonDoiHang hd) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(hd);
            tr.commit();
            return true;
        }
        catch (RuntimeException e) {
            tr.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public HoaDonDoiHang getHoaDonDoiHangtheoMa(String ma) throws RemoteException {
        return em.find(HoaDonDoiHang.class, ma);
    }

    @Override
    public HoaDonDoiHang getHoaDonDoiHangtheoMaHT(String maHT) throws RemoteException {
        return null;
    }

    @Override
    public boolean deleteHoaDonDoi(String ma) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            HoaDonDoiHang hd = em.find(HoaDonDoiHang.class, ma);
            if (hd != null) {
                em.remove(hd);
                tr.commit();
                return true;
            }
        }
        catch (RuntimeException e) {
            tr.rollback();
            throw new RuntimeException(e);
        }

        return false;
    }
}
