package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_TaiKhoan;
import iuh.fit.models.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Random;

public class DaoImpl_TaiKhoan implements DAO_TaiKhoan {
    private EntityManager em;

    // Constructor để khởi tạo EntityManager
    public DaoImpl_TaiKhoan(EntityManager em) {
        this.em = em;
    }

    @Override
    public String phanQuyen(String email) {
        String tenDN = "";
        try {
            TypedQuery<String> query = em.createNamedQuery("TaiKhoan.phanQuyen", String.class)
                    .setParameter("email", email);
            tenDN = query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không tìm thấy người dùng");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn");
        }
        return tenDN;
    }

    @Override
    public String getTenNguoiDung(String email) {
        String tenND = "";
        try {
            TypedQuery<String> query = em.createNamedQuery("TaiKhoan.getTenNguoiDung", String.class)
                    .setParameter("email", email);
            tenND = query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không tìm thấy người dùng");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn");
        }
        return tenND;
    }

    @Override
    public int sendEmail(String email) throws RemoteException {
        try {
            // Tạo mã OTP ngẫu nhiên
            int OTP = 100000 + new Random().nextInt(900000);


            System.out.println("Gửi email đến: " + email);
            System.out.println("Mã OTP của bạn là: " + OTP);

            TypedQuery<TaiKhoan> query = em.createQuery(
                    "SELECT tk FROM TaiKhoan tk WHERE tk.email = :email", TaiKhoan.class
            );
            query.setParameter("email", email);
            TaiKhoan taiKhoan = query.getSingleResult();

            if (taiKhoan != null) {
            } else {
                throw new RemoteException("Không tìm thấy tài khoản với email: " + email);
            }

            return OTP;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi giả lập gửi email", e);
        }
    }

    @Override
    public boolean doiMatKhau(String email, String newPassword) throws RemoteException {
        try {
            TypedQuery<TaiKhoan> query = em.createQuery(
                    "SELECT tk FROM TaiKhoan tk WHERE tk.email = :email", TaiKhoan.class
            );
            query.setParameter("email", email);
            TaiKhoan taiKhoan = query.getSingleResult();

            if (taiKhoan == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy tài khoản với email: " + email);
                return false;
            }

            em.getTransaction().begin();
            taiKhoan.setMatKhau(newPassword);
            em.merge(taiKhoan);
            em.getTransaction().commit();

            return true;
        } catch (NoResultException e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy tài khoản với email: " + email);
            return false;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi đổi mật khẩu: " + e.getMessage());
            throw new RemoteException("Lỗi khi đổi mật khẩu", e);
        }
    }

    @Override
    public List<TaiKhoan> getAllTaiKhoan() throws RemoteException {
        try {
            TypedQuery<TaiKhoan> query = em.createQuery(
                    "SELECT tk FROM TaiKhoan tk", TaiKhoan.class
            );
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi lấy danh sách tài khoản", e);
        }
    }

    @Override
    public TaiKhoan getTaiKhoanByEmail(String email) throws RemoteException {
        try {
            TypedQuery<TaiKhoan> query = em.createQuery(
                    "SELECT tk FROM TaiKhoan tk WHERE tk.email = :email", TaiKhoan.class
            );
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy tài khoản với email: " + email);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi lấy tài khoản theo email", e);
        }
    }
}