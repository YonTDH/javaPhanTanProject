package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_ChiTietHoaDon;  // Import đúng interface
import iuh.fit.models.ChiTietHoaDon;
import iuh.fit.models.HoaDon;
import iuh.fit.models.SanPham;
import iuh.fit.models.VanPhongPham;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DAOImpl_ChiTietHoaDon extends UnicastRemoteObject implements DAO_ChiTietHoaDon {  // Implement đúng interface DAO_ChiTietHoaDon
    private EntityManager em;

    // Constructor mặc định
    public DAOImpl_ChiTietHoaDon() throws RemoteException {
        em = AppUtil.getEntityManager();  // Giả sử AppUtil trả về EntityManager.
    }

    // Constructor với EntityManager
    public DAOImpl_ChiTietHoaDon(EntityManager em) throws RemoteException {
        this.em = em;
    }

    // Lấy tất cả chi tiết hóa đơn
    @Override
    public List<ChiTietHoaDon> getAllChiTietHoaDon() throws RemoteException {
        try {
            return em.createQuery("FROM ChiTietHoaDon", ChiTietHoaDon.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy tất cả chi tiết hóa đơn.", e);
        }
    }

    @Override
    public boolean createChiTietHoaDonVPP(ChiTietHoaDon cthd) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();

            // Kiểm tra xem sản phẩm có phải là VanPhongPham không
            if (cthd.getSanPham() instanceof VanPhongPham) {
                // Tiến hành lưu chi tiết hóa đơn
                em.persist(cthd);  // Lưu chi tiết hóa đơn vào database
                tr.commit();
                return true;
            } else {
                throw new RemoteException("Sản phẩm không phải là loại Văn Phòng Phẩm.");
            }
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi tạo chi tiết hóa đơn cho sản phẩm VPP.", e);
        }
    }


    // Tạo mới chi tiết hóa đơn
    @Override
    public boolean createChiTietHoaDon(ChiTietHoaDon cthd) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(cthd);  // Lưu chi tiết hóa đơn
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi thêm chi tiết hóa đơn.", e);
        }
    }

    @Override
    public boolean deleteChiTietHoaDonVaSanPham(String maHD, String maSP) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();

            // Tìm hóa đơn theo mã hóa đơn
            HoaDon hoaDon = em.find(HoaDon.class, maHD);
            if (hoaDon == null) {
                throw new RemoteException("Hóa đơn không tồn tại.");
            }

            // Tìm chi tiết hóa đơn theo mã hóa đơn và mã sản phẩm
            ChiTietHoaDon chiTietHoaDon = em.createQuery("SELECT c FROM ChiTietHoaDon c " +
                            "WHERE c.hoaDon = :hoaDon AND c.sanPham.maSanPham = :maSP", ChiTietHoaDon.class)
                    .setParameter("hoaDon", hoaDon)
                    .setParameter("maSP", maSP)
                    .getSingleResult();

            if (chiTietHoaDon == null) {
                throw new RemoteException("Không tìm thấy chi tiết hóa đơn với sản phẩm này.");
            }

            // Cập nhật lại số lượng tồn kho của sản phẩm
            SanPham sanPham = chiTietHoaDon.getSanPham();
            sanPham.setSoLuongTon(sanPham.getSoLuongTon() + chiTietHoaDon.getSoLuong());  // Thêm lại số lượng sản phẩm vào tồn kho

            // Xóa chi tiết hóa đơn
            em.remove(chiTietHoaDon);

            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi xóa chi tiết hóa đơn và sản phẩm.", e);
        }
    }


    // Xóa chi tiết hóa đơn theo mã hóa đơn
    @Override
    public boolean deleteChiTietHoaDon(String maHD) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            ChiTietHoaDon cthd = em.find(ChiTietHoaDon.class, maHD);  // Tìm chi tiết hóa đơn theo mã hóa đơn
            if (cthd != null) {
                em.remove(cthd);  // Xóa chi tiết hóa đơn
                tr.commit();
                return true;
            }
            tr.commit();
            return false;  // Nếu không tìm thấy, trả về false
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi xóa chi tiết hóa đơn.", e);
        }
    }

    @Override
    public ChiTietHoaDon getCTHoaDontheoMa(String maHD, String maSP) throws RemoteException {
        try {
            HoaDon hoaDon = em.find(HoaDon.class, maHD);
            if (hoaDon == null) {
                throw new RemoteException("Không tìm thấy hóa đơn với mã: " + maHD);
            }

            return em.createQuery("SELECT c FROM ChiTietHoaDon c " +
                            "WHERE c.hoaDon = :hoaDon " +
                            "AND c.sanPham.maSanPham = :maSP", ChiTietHoaDon.class)
                    .setParameter("hoaDon", hoaDon)
                    .setParameter("maSP", maSP)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi tìm chi tiết hóa đơn theo mã hóa đơn và mã sản phẩm", e);
        }
    }

    @Override
    public boolean updateCTHoaDon(ChiTietHoaDon cthd) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            // Check if the ChiTietHoaDon exists before updating
            ChiTietHoaDon existingCTHD = getCTHoaDontheoMa(
                    cthd.getHoaDon().getMaHoaDon(),
                    cthd.getSanPham().getMaSanPham()
            );

            if (existingCTHD == null) {
                throw new RemoteException("Chi tiết hóa đơn không tồn tại");
            }

            // Update by merging the entity
            em.merge(cthd);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            throw new RemoteException("Lỗi khi cập nhật chi tiết hóa đơn", e);
        }
    }

    // Lấy chi tiết hóa đơn theo mã hóa đơn và mã sản phẩm
    @Override
    public ChiTietHoaDon getHoaDontheoMa(String maHDD, String maSP) throws RemoteException {
        try {
            return em.createQuery("FROM ChiTietHoaDon c WHERE c.id = :maHDD AND c.sanPham.id = :maSP", ChiTietHoaDon.class)
                    .setParameter("maHDD", maHDD)
                    .setParameter("maSP", maSP)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy chi tiết hóa đơn theo mã.", e);
        }
    }
}
