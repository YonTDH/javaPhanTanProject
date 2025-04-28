package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_NhomSanPham;
import iuh.fit.models.NhomSanPham;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DAOImpl_NhomSanPham extends UnicastRemoteObject implements DAO_NhomSanPham {
    private static final long serialVersionUID = 1L;
    private EntityManager em;

    public DAOImpl_NhomSanPham() throws RemoteException {
        em = AppUtil.getEntityManager();
    };

    public DAOImpl_NhomSanPham(EntityManager em) throws RemoteException{
        this.em = em;
    }

    @Override
    public List<NhomSanPham> getAllNhomSanPham() throws RemoteException {
        return em.createNamedQuery("NhomSanPham.getAll", NhomSanPham.class)
                .getResultList();
    }

    @Override
    public NhomSanPham getNsptheoTen(String ten) throws RemoteException {
        try {
            return em.createNamedQuery("NhomSanPham.findByTen", NhomSanPham.class)
                    .setParameter("tenNhom", ten)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public NhomSanPham getNspTheoMa(String ma) throws RemoteException {
        try {
            return em.find(NhomSanPham.class, ma);
        } catch (Exception e) {
            return null;
        }    }
}
