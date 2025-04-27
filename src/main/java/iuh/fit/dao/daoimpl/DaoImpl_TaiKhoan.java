package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_TaiKhoan;
import iuh.fit.models.ChucVu;
import iuh.fit.models.TaiKhoan;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl_TaiKhoan implements DAO_TaiKhoan {
    private EntityManager em;

    public DaoImpl_TaiKhoan() {
        this.em = AppUtil.getEntityManager();
    }

    @Override
    public String phanQuyen(String email) throws RemoteException {
        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT tk.chucVu FROM TaiKhoan tk WHERE tk.email = :email", String.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getTenNguoiDung(String email) throws RemoteException {
        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT tk.nhanVien.tenNhanVien FROM TaiKhoan tk WHERE tk.email = :email", String.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int sendEmail(String email) throws RemoteException {
        return 0;
    }

    @Override
    public boolean doiMatKhau(String email, String newPassword) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        try {

            tx.begin();
            TypedQuery<TaiKhoan> query = em.createQuery(
                    "SELECT tk FROM TaiKhoan tk WHERE tk.email = :email", TaiKhoan.class);
            query.setParameter("email", email);
            TaiKhoan taiKhoan = query.getSingleResult();
            if (taiKhoan != null) {
                taiKhoan.setMatKhau(newPassword);
                em.merge(taiKhoan);
                tx.commit();
                return true;
            } else {
                tx.rollback();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
            return false;
        }
    }

    @Override
    public List<TaiKhoan> getAllTaiKhoan() throws RemoteException {
        try {
            TypedQuery<TaiKhoan> query = em.createQuery(
                    "SELECT tk FROM TaiKhoan tk", TaiKhoan.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public TaiKhoan getTaiKhoanByEmail(String email) throws RemoteException {
        try {
            TypedQuery<TaiKhoan> query = em.createQuery(
                    "SELECT tk FROM TaiKhoan tk WHERE tk.email = :email", TaiKhoan.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
