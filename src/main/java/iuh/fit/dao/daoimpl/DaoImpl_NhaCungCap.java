package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_NhaCungCap;
import iuh.fit.models.NhaCungCap;

import java.rmi.RemoteException;
import java.util.List;

public class DaoImpl_NhaCungCap implements DAO_NhaCungCap {
    @Override
    public List<NhaCungCap> getALLNhaCungCap_20() throws RemoteException {
        return List.of();
    }

    @Override
    public List<NhaCungCap> getALLNhaCungCap() throws RemoteException {
        return List.of();
    }

    @Override
    public NhaCungCap getNCCTheoMa(String maNCC) throws RemoteException {
        return null;
    }

    @Override
    public NhaCungCap getNCCTheoTen(String tenNCC) throws RemoteException {
        return null;
    }

    @Override
    public boolean themNhaCungCap(NhaCungCap ncc) throws RemoteException {
        return false;
    }

    @Override
    public boolean updateNhaCungCap(String maSua, NhaCungCap nccMoi) throws RemoteException {
        return false;
    }

    @Override
    public List<NhaCungCap> locNhaCungCap(String duLieuTim) throws RemoteException {
        return List.of();
    }

    @Override
    public String getMaNhaCungCapDB() throws RemoteException {
        return "";
    }
}
