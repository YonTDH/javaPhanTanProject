package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_NhanVien;
import iuh.fit.models.ChucVu;
import iuh.fit.models.NhanVien;

import java.rmi.RemoteException;
import java.util.List;

public class DaoImpl_NhanVien implements DAO_NhanVien {
    @Override
    public List<NhanVien> getAllNhanVien_20() throws RemoteException {
        return List.of();
    }

    @Override
    public List<NhanVien> getAllNhanVien() throws RemoteException {
        return List.of();
    }

    @Override
    public List<NhanVien> getAllNhanVienTheoCa(String caLv) throws RemoteException {
        return List.of();
    }

    @Override
    public NhanVien getNVTheoMa(String maNV) throws RemoteException {
        return null;
    }

    @Override
    public boolean themNhanVien(NhanVien nv) throws RemoteException {
        return false;
    }

    @Override
    public boolean updateNhanVien(String maNVSua, NhanVien nvMoi) throws RemoteException {
        return false;
    }

    @Override
    public List<NhanVien> locNhanVien(String duLieuTim) throws RemoteException {
        return List.of();
    }

    @Override
    public List<NhanVien> locNhanVienTheoChucVu(ChucVu duLieuTim) throws RemoteException {
        return List.of();
    }

    @Override
    public String getMaNhanVienQLDB() throws RemoteException {
        return "";
    }

    @Override
    public String getMaNhanVienTNDB() throws RemoteException {
        return "";
    }

    @Override
    public String getNhanVienTheoCa(String email) throws RemoteException {
        return "";
    }
}
