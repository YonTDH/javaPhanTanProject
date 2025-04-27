package iuh.fit.RMIserver;

import iuh.fit.dao.*;
import iuh.fit.dao.daoimpl.*;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BookStoreServer {
    public static void main(String[] args) {
        try {
            // Khởi tạo EntityManagerFactory
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadb-pu");
            EntityManager em = emf.createEntityManager();

            // Khởi tạo các DAO
            DAO_TaiKhoan taiKhoanDAO = new DaoImpl_TaiKhoan(em);
            DAO_ThongKe thongKeDAO = new DAOImpl_ThongKe();
            // Khởi tạo các DAO khác nếu cần...

            // Khởi tạo RMI Registry
            Registry registry = LocateRegistry.createRegistry(1099); // Port mặc định của RMI

            // Tạo và đăng ký các dịch vụ RMI
            DAO_TaiKhoan taiKhoanService = new DaoImpl_TaiKhoan(em);
            registry.rebind("TaiKhoanService", taiKhoanService);

            DAO_ThongKe thongKeService = new DAOImpl_ThongKe(em);
            registry.rebind("ThongKeService", thongKeService);

            // Đăng ký các dịch vụ khác nếu cần...

            System.out.println("RMI Server đang chạy...");

            // Đảm bảo đóng EntityManagerFactory khi server tắt
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                AppUtil.closeEntityManagerFactory();
            }));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}