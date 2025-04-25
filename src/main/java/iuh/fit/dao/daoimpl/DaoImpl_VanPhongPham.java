package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_VanPhongPham;
import iuh.fit.models.VanPhongPham;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;

import java.rmi.RemoteException;
import java.util.List;

public class DaoImp_VanPhongPham implements DAO_VanPhongPham {
    private static final long serialVersionUID = 1L;
    private EntityManager em;
    public DaoImp_VanPhongPham() throws RemoteException {
        em = AppUtil.getEntityManager();
    }
    @Override
    public List<VanPhongPham> getAllVanPhongPhan_20() throws RemoteException {
        try {
            return em.createNamedQuery("VanPhongPham.getAllVanPhongPham_20", VanPhongPham.class).getResultList();
        } catch (Exception e) {
            throw new RemoteException("Error retrieving all VanPhongPham", e);
        }
    }

    @Override
    public List<VanPhongPham> getAllVanPhongPhan() throws RemoteException {
        try {
            return em.createNamedQuery("VanPhongPham.getAllVanPhongPham", VanPhongPham.class).getResultList();
        } catch (Exception e) {
            throw new RemoteException("Error retrieving all VanPhongPham", e);
        }
    }

    @Override
    public boolean update(VanPhongPham vpp) throws RemoteException {
        try {
            if (vpp != null && vpp.getMaSanPham() != null) {
                em.merge(vpp);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RemoteException("Error updating VanPhongPham", e);
        }
    }

    @Override
    public VanPhongPham getVPPtheoMa(String ma) throws RemoteException {
        try {
            return em.createNamedQuery("VanPhongPham.getVPPtheoMa", VanPhongPham.class)
                    .setParameter("ma", ma)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RemoteException("Error retrieving VanPhongPham by code", e);
        }
    }

    @Override
    public VanPhongPham getVpptheoTen(String ten) throws RemoteException {
        try {
            return em.createNamedQuery("VanPhongPham.getVpptheoTen", VanPhongPham.class)
                    .setParameter("ten", ten)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RemoteException("Error retrieving VanPhongPham by name", e);
        }
    }

    @Override
    public List<VanPhongPham> getDsVpptheoMa(String ma) throws RemoteException {
        try {
            return em.createNamedQuery("VanPhongPham.getVPPtheoMa", VanPhongPham.class)
                    .setParameter("ma", ma)
                    .getResultList();
        } catch (Exception e) {
            throw new RemoteException("Error retrieving VanPhongPham list by code", e);
        }
    }

    @Override
    public boolean insertVpp(VanPhongPham vpp) throws RemoteException {
        try {
            if (vpp != null) {
                em.persist(vpp);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RemoteException("Error inserting new VanPhongPham", e);
        }
    }
}
