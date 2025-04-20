package iuh.fit;

import iuh.fit.models.NhaCungCap;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManager entityManager = null;

        try {
            // Lấy EntityManager từ AppUtil
            entityManager = AppUtil.getEntityManager();
            // Bắt đầu transaction
            entityManager.getTransaction().begin();

            // Commit transaction
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            AppUtil.closeEntityManagerFactory();
        }
    }
}