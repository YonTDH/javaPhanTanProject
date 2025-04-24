package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_KhuyenMai;
import iuh.fit.models.KhuyenMai;

import java.rmi.RemoteException;
import java.util.List;

public class DaoImpl_KhuyenMai implements DAO_KhuyenMai {
    @Override
    public List<KhuyenMai> getAlltbKM() throws RemoteException {
        return List.of();
    }

    @Override
    public boolean createKhuyenMai(KhuyenMai s) throws RemoteException {
        return false;
    }

    @Override
    public List<KhuyenMai> getAlltbKMTheoDangChay() throws RemoteException {
        return List.of();
    }

    @Override
    public KhuyenMai getKMtheoMa(String ma) throws RemoteException {
        return null;
    }

    @Override
    public boolean updateKhuyenMai(KhuyenMai s) throws RemoteException {
        return false;
    }

    @Override
    public String getMaKhuyenMaiTNDB() throws RemoteException {
        return "";
    }

    @Override
    public boolean updateTinhTrang(KhuyenMai km) throws RemoteException {
        return false;
    }

    @Override
    public KhuyenMai getKmTheoMa(String maKM) throws RemoteException {
        return null;
    }
}
