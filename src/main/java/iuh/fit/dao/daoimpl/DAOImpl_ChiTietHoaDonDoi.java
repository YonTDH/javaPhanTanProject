package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_ChiTietHoaDonDoi;
import iuh.fit.models.ChiTietHoaDonDoi;
import jakarta.persistence.EntityManager;

import java.rmi.RemoteException;
import java.util.List;

public class DAOImpl_ChiTietDonDoi implements DAO_ChiTietHoaDonDoi {
    private EntityManager em;

    public DAOImpl_ChiTietDonDoi
    @Override
    public List<ChiTietHoaDonDoi> getAllChiTietDonDoi() throws RemoteException {
        return em;
    }

    @Override
    public boolean createChiTietDonDoi(ChiTietHoaDonDoi cthd) throws RemoteException {
        return false;
    }

    @Override
    public boolean deleteChiTietDonDoi(String maHD) throws RemoteException {
        return false;
    }

    @Override
    public ChiTietHoaDonDoi getHoaDontheoMa(String maHDD, String maSP) throws RemoteException {
        return null;
    }
}
