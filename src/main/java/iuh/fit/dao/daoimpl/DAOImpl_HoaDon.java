package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_HoaDon;
import iuh.fit.models.HoaDon;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DAOImpl_HoaDon extends UnicastRemoteObject implements DAO_HoaDon {
    private EntityManager em;

    public DAOImpl_HoaDon() throws RemoteException {
        this.em = AppUtil.getEntityManager();
    }

    public DAOImpl_HoaDon(EntityManager em) throws RemoteException {
        this.em = em;
    }

    @Override
    public List<HoaDon> getAllHoaDon_InDay() throws RemoteException {
        try {
            LocalDate today = LocalDate.now();
            return em.createQuery(
                            "SELECT hd FROM HoaDon hd WHERE DATE(hd.ngayLap) = :today",
                            HoaDon.class)
                    .setParameter("today", today)
                    .getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy danh sách hóa đơn trong ngày", e);
        }
    }

    @Override
    public List<HoaDon> getAllHoaDon() throws RemoteException {
        try {
            return em.createQuery("FROM HoaDon", HoaDon.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy tất cả hóa đơn", e);
        }
    }

    @Override
    public boolean createHoaDon(HoaDon hd) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(hd);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            throw new RemoteException("Lỗi khi tạo hóa đơn", e);
        }
    }

    @Override
    public boolean updateHoaDon(HoaDon hd) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(hd);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            throw new RemoteException("Lỗi khi cập nhật hóa đơn", e);
        }
    }

    @Override
    public HoaDon getHoaDontheoMa(String ma) throws RemoteException {
        try {
            return em.find(HoaDon.class, ma);
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy hóa đơn theo mã", e);
        }
    }

    @Override
    public boolean deleteHoaDon(String maHD) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            HoaDon hd = em.find(HoaDon.class, maHD);
            if (hd != null) {
                em.remove(hd);
                tr.commit();
                return true;
            }
            tr.commit();
            return false;
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            throw new RemoteException("Lỗi khi xóa hóa đơn", e);
        }
    }

    @Override
    public List<String> getMaHoaDonTheoNgay(String ngayLapHD) throws RemoteException {
        try {
            // Chuyển đổi chuỗi ngày thành đối tượng Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(ngayLapHD);

            // Tạo ngày bắt đầu và kết thúc để lọc theo ngày
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date startDate = cal.getTime();

            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            Date endDate = cal.getTime();

            // Truy vấn các mã hóa đơn trong ngày
            TypedQuery<String> query = em.createQuery(
                    "SELECT hd.maHoaDon FROM HoaDon hd " +
                            "WHERE hd.ngayLap BETWEEN :startDate AND :endDate " +
                            "ORDER BY hd.ngayLap",
                    String.class);

            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);

            return query.getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy mã hóa đơn theo ngày", e);
        }
    }
}