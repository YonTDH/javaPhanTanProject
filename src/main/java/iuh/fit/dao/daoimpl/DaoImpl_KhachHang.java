package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_KhachHang;
import iuh.fit.models.KhachHang;

import java.rmi.RemoteException;
import java.util.List;

public class DaoImpl_KhachHang implements DAO_KhachHang {
    @Override
    public List<KhachHang> getAllKhachHang_20() throws RemoteException {
        return List.of();
    }

    @Override
    public List<KhachHang> getAllKhachHang() throws RemoteException {
        return List.of();
    }

    @Override
    public KhachHang getKHTheoMa(String ma) throws RemoteException {
        return null;
    }

    @Override
    public KhachHang getKHTheoSDT(String soDienThoai) throws RemoteException {
        return null;
    }

    @Override
    public boolean themKhachHang(KhachHang cus) throws RemoteException {
        return false;
    }

    @Override
    public boolean updateKhachHang(KhachHang cus) throws RemoteException {
        return false;
    }

    @Override
    public List<KhachHang> timKhachHangTheoThongTin(String data) throws RemoteException {
        return List.of();
    }

    @Override
    public void capNhatNhomKhachHang() throws RemoteException {

    }

    @Override
    public List<KhachHang> getKhachHangTrong1Thang() throws RemoteException {
        return List.of();
    }

    @Override
    public List<KhachHang> locDuLieuKhachHang(String data) throws RemoteException {
        return List.of();
    }
}
