package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_NhomSanPham;
import iuh.fit.models.NhomSanPham;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;

import java.rmi.RemoteException;
import java.util.List;

public class DAOImpl_NhomSanPham implements DAO_NhomSanPham {
    private EntityManager em;

    public DAOImpl_NhomSanPham() {
        em = AppUtil.getEntityManager();
    }

    public DAOImpl_NhomSanPham(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<NhomSanPham> getAllNhomSanPham() throws RemoteException {
        try {
            return em.createQuery("FROM NhomSanPham", NhomSanPham.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy tất cả nhóm sản phẩm.", e);
        }
    }

    @Override
    public NhomSanPham getNsptheoTen(String ten) throws RemoteException {
        try {
            return em.createQuery("FROM NhomSanPham n WHERE n.tenNhom = :ten", NhomSanPham.class)
                    .setParameter("ten", ten)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy nhóm sản phẩm theo tên.", e);
        }
    }

    @Override
    public NhomSanPham getNspTheoMa(String ma) throws RemoteException {
        try {
            return em.find(NhomSanPham.class, ma);
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy nhóm sản phẩm theo mã.", e);
        }
    }
}
