package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_Sach;
import iuh.fit.models.Sach;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DaoImpl_Sach extends UnicastRemoteObject implements DAO_Sach {
    private static final long serialVersionUID = 1L;

    private EntityManager em;
    public DaoImpl_Sach() throws RemoteException {
        em = AppUtil.getEntityManager();
    }
    public DaoImpl_Sach(EntityManager em)throws RemoteException {
        this.em = em;
    }

    @Override
    public List<Sach> getAlltbSach_20() throws RemoteException {
        return em.createNamedQuery("Sach.getAlltbSach_20", Sach.class).getResultList();
    }

    @Override
    public List<Sach> getAlltbSach() throws RemoteException {
        return em.createQuery("SELECT s FROM Sach s", Sach.class).getResultList();
    }

    @Override
    public boolean createSach(Sach s) throws RemoteException {
        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public void importExcel(String fileName, String sheetName) throws RemoteException {

    }

    @Override
    public Sach getSachtheoMa(String ma) throws RemoteException {
        return em.find(Sach.class, ma);
    }

    @Override
    public boolean updateSach(Sach s) throws RemoteException {
        try {
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            return false;
        }
    }
}
