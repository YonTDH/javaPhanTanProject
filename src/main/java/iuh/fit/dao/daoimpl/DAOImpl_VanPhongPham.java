package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_VanPhongPham;
import iuh.fit.models.VanPhongPham;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.util.List;

public class DAOImpl_VanPhongPham implements DAO_VanPhongPham {
    private EntityManager em;

    public DAOImpl_VanPhongPham() {
        em = AppUtil.getEntityManager(); // Giả sử AppUtil trả về EntityManager.
    }

    public DAOImpl_VanPhongPham(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<VanPhongPham> getAllVanPhongPhan_20() throws RemoteException {
        try {
            return em.createQuery("FROM VanPhongPham", VanPhongPham.class)
                    .setMaxResults(20)
                    .getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy danh sách văn phòng phẩm (20).", e);
        }
    }

    @Override
    public List<VanPhongPham> getAllVanPhongPhan() throws RemoteException {
        try {
            return em.createQuery("FROM VanPhongPham", VanPhongPham.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy tất cả văn phòng phẩm.", e);
        }
    }

    @Override
    public boolean update(VanPhongPham vpp) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(vpp); // Cập nhật văn phòng phẩm
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi cập nhật văn phòng phẩm.", e);
        }
    }

    @Override
    public VanPhongPham getVPPtheoMa(String ma) throws RemoteException {
        try {
            return em.find(VanPhongPham.class, ma); // Lấy văn phòng phẩm theo mã
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy văn phòng phẩm theo mã.", e);
        }
    }

    @Override
    public VanPhongPham getVpptheoTen(String ten) throws RemoteException {
        try {
            return em.createQuery("FROM VanPhongPham vpp WHERE vpp.tenSanPham = :ten", VanPhongPham.class)
                    .setParameter("ten", ten)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy văn phòng phẩm theo tên.", e);
        }
    }

    @Override
    public List<VanPhongPham> getDsVpptheoMa(String ma) throws RemoteException {
        try {
            return em.createQuery("FROM VanPhongPham vpp WHERE vpp.maSanPham LIKE :ma", VanPhongPham.class)
                    .setParameter("ma", "%" + ma + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy danh sách văn phòng phẩm theo mã.", e);
        }
    }

    @Override
    public boolean insertVpp(VanPhongPham vpp) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(vpp); // Thêm mới văn phòng phẩm
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi thêm mới văn phòng phẩm.", e);
        }
    }
}
