package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_HoaDonHoanTra;
import iuh.fit.models.HoaDonHoanTra;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DAOImpl_HoaDonHoanTra extends UnicastRemoteObject implements DAO_HoaDonHoanTra {

    private EntityManager em;

    public DAOImpl_HoaDonHoanTra() throws RemoteException {
        em = AppUtil.getEntityManager();
    }

    public DAOImpl_HoaDonHoanTra(EntityManager em) throws RemoteException {
        this.em = em;
    }

    @Override
    public List<HoaDonHoanTra> getAllHoaDonHoanTra_20() throws RemoteException {
        try {
            return em.createQuery("FROM HoaDonHoanTra", HoaDonHoanTra.class)
                    .setMaxResults(20)
                    .getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy 20 hóa đơn hoàn trả đầu tiên.", e);
        }
    }

    @Override
    public List<HoaDonHoanTra> getAllHoaDonHoanTra() throws RemoteException {
        try {
            return em.createQuery("FROM HoaDonHoanTra", HoaDonHoanTra.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy tất cả hóa đơn hoàn trả.", e);
        }
    }

    @Override
    public boolean createHoaDonHoanTra(HoaDonHoanTra hd) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(hd);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive()) tr.rollback();
            throw new RemoteException("Lỗi khi tạo hóa đơn hoàn trả.", e);
        }
    }

    @Override
    public boolean updateHoaDonHoanTra(HoaDonHoanTra hd) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(hd);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive()) tr.rollback();
            throw new RemoteException("Lỗi khi cập nhật hóa đơn hoàn trả.", e);
        }
    }

    @Override
    public HoaDonHoanTra getHoaDonHoanTratheoMa(String ma) throws RemoteException {
        try {
            return em.find(HoaDonHoanTra.class, ma);
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi tìm hóa đơn hoàn trả theo mã.", e);
        }
    }

    @Override
    public boolean deleteHoaDonHoanTra(String ma) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            HoaDonHoanTra hd = em.find(HoaDonHoanTra.class, ma);
            if (hd != null) {
                em.remove(hd);
                tr.commit();
                return true;
            }
            tr.commit();
            return false;
        } catch (Exception e) {
            if (tr.isActive()) tr.rollback();
            throw new RemoteException("Lỗi khi xóa hóa đơn hoàn trả.", e);
        }
    }
}
