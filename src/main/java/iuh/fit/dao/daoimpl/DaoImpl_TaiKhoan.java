package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_TaiKhoan;
import iuh.fit.models.TaiKhoan;

import java.rmi.RemoteException;
import java.util.List;

public class DaoImpl_TaiKhoan implements DAO_TaiKhoan {
    @Override
    public String phanQuyen(String email) throws RemoteException {
        return "";
    }

    @Override
    public String getTenNguoiDung(String email) throws RemoteException {
        return "";
    }

    @Override
    public int sendEmail(String email) throws RemoteException {
        return 0;
    }

    @Override
    public boolean doiMatKhau(String email, String newPassword) throws RemoteException {
        return false;
    }

    @Override
    public List<TaiKhoan> getAllTaiKhoan() throws RemoteException {
        return List.of();
    }

    @Override
    public TaiKhoan getTaiKhoanByEmail(String email) throws RemoteException {
        return null;
    }
}
