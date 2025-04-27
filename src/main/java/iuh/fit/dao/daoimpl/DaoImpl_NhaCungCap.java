package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_NhaCungCap;
import iuh.fit.models.NhaCungCap;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;

import java.rmi.RemoteException;
import java.util.List;

public class DaoImpl_NhaCungCap implements DAO_NhaCungCap {
    private EntityManager entityManager;

    public DaoImpl_NhaCungCap() {
        entityManager = AppUtil.getEntityManager();
    }

    public DaoImpl_NhaCungCap(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public List<NhaCungCap> getALLNhaCungCap_20() throws RemoteException {

        List<NhaCungCap> nhaCungCapList = null;

        try {
            entityManager = AppUtil.getEntityManager();

            nhaCungCapList = entityManager.createQuery("SELECT n FROM NhaCungCap n", NhaCungCap.class)
                    .setMaxResults(20) // Limit the result to 20 records
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return nhaCungCapList;
    }

    @Override
    public List<NhaCungCap> getALLNhaCungCap() throws RemoteException {
        List<NhaCungCap> nhaCungCapList = null;

        try {
            entityManager = AppUtil.getEntityManager();

            nhaCungCapList = entityManager.createQuery("SELECT n FROM NhaCungCap n", NhaCungCap.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return nhaCungCapList;
    }

    @Override
    public NhaCungCap getNCCTheoMa(String maNCC) throws RemoteException {
        NhaCungCap nhaCungCap = null;

        try {
            entityManager = AppUtil.getEntityManager();

            // Query to find NhaCungCap by maNCC
            nhaCungCap = entityManager.createQuery("SELECT n FROM NhaCungCap n WHERE n.maNhaCungCap = :maNCC", NhaCungCap.class)
                    .setParameter("maNCC", maNCC)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return nhaCungCap;
    }

    @Override
    public NhaCungCap getNCCTheoTen(String tenNCC) throws RemoteException {

        NhaCungCap nhaCungCap = null;

        try {
            entityManager = AppUtil.getEntityManager();

            // Query to find NhaCungCap by tenNCC
            nhaCungCap = entityManager.createQuery("SELECT n FROM NhaCungCap n WHERE n.tenNhaCungCap = :tenNCC", NhaCungCap.class)
                    .setParameter("tenNCC", tenNCC)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return nhaCungCap;
    }

    @Override
    public boolean themNhaCungCap(NhaCungCap ncc) throws RemoteException {
        boolean isAdded = false;

        try {
            entityManager = AppUtil.getEntityManager();
            entityManager.getTransaction().begin(); // Start transaction

            entityManager.persist(ncc); // Persist the NhaCungCap entity
            entityManager.getTransaction().commit(); // Commit transaction

            isAdded = true; // Indicate success
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Rollback transaction in case of error
            }
        } finally {
            if (entityManager != null) {
                entityManager.close(); // Close EntityManager
            }
        }

        return isAdded;
    }

    @Override
    public boolean updateNhaCungCap(String maSua, NhaCungCap nccMoi) throws RemoteException {
        boolean isUpdated = false;

        try {
            entityManager = AppUtil.getEntityManager();
            entityManager.getTransaction().begin(); // Start transaction


            NhaCungCap existingNCC = entityManager.find(NhaCungCap.class, maSua);
            if (existingNCC != null) {

                existingNCC.setTenNhaCungCap(nccMoi.getTenNhaCungCap());
                existingNCC.setDiaChi(nccMoi.getDiaChi());
                existingNCC.setSoDienThoai(nccMoi.getSoDienThoai());

                entityManager.merge(existingNCC);
                entityManager.getTransaction().commit();
                isUpdated = true;
            } else {
                entityManager.getTransaction().rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return isUpdated;
    }

    @Override
    public List<NhaCungCap> locNhaCungCap(String duLieuTim) throws RemoteException {
        List<NhaCungCap> nhaCungCapList = null;

        try {
            entityManager = AppUtil.getEntityManager();

            // Query to find NhaCungCap by maNhaCungCap, tenNhaCungCap, or soDienThoai
            nhaCungCapList = entityManager.createQuery(
                            "SELECT n FROM NhaCungCap n WHERE n.maNhaCungCap LIKE :duLieuTim " +
                                    "OR n.tenNhaCungCap LIKE :duLieuTim OR n.soDienThoai LIKE :duLieuTim",
                            NhaCungCap.class)
                    .setParameter("duLieuTim", "%" + duLieuTim + "%") // Use wildcard for partial match
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return nhaCungCapList;
    }

    //hàm này lấy mã nhà cung cấp lớn nhất trong database
    @Override
    public String getMaNhaCungCapDB() throws RemoteException {
        String maNhaCungCap = null;

        try {
            entityManager = AppUtil.getEntityManager();

            // Query to get the maximum maNhaCungCap
            maNhaCungCap = entityManager.createQuery(
                            "SELECT MAX(n.maNhaCungCap) FROM NhaCungCap n", String.class)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return maNhaCungCap;
    }
}
