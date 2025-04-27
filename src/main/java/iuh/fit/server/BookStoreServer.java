package iuh.fit.server;

import iuh.fit.dao.*;
import iuh.fit.dao.daoimpl.*;
import iuh.fit.models.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.List;

public class BookStoreServer {
    private static final int PORT = 12345;

    private static EntityManagerFactory emf;
    private static DAO_ChiTietHoaDon chiTietHoaDonDAO;
    private static DAO_ChiTietHoaDonDoi chiTietHoaDonDoiDAO;
    private static DAO_ChiTietHoaDonHoanTra chiTietHoaDonHoanTraDAO;
    private static DAO_HoaDon hoaDonDAO;
    private static DAO_HoaDonDoiHang hoaDonDoiHangDAO;
    private static DAO_HoaDonHoanTra hoaDonHoanTraDAO;
    private static DAO_KhachHang khachHangDAO;
    private static DAO_KhuyenMai khuyenMaiDAO;
    private static DAO_MauSac mauSacDAO;
    private static DAO_NhaCungCap nhaCungCapDAO;
    private static DAO_NhanVien nhanVienDAO;
    private static DAO_NhomKhuyenMai nhomKhuyenMaiDAO;
    private static DAO_NhomSanPham nhomSanPhamDAO;
    private static DAO_Sach sachDAO;
    private static DAO_SanPham sanPhamDAO;
    private static DAO_TaiKhoan taiKhoanDAO;
    private static DAO_ThongKe thongKeDAO;
    private static DAO_VanPhongPham vanPhongPhamDAO;

    public static void main(String[] args) {
        // Khởi tạo EntityManagerFactory
        emf = Persistence.createEntityManagerFactory("mariadb-pu");
        EntityManager em = emf.createEntityManager();

        // Khởi tạo tất cả các DAO
        try {
            chiTietHoaDonDAO = new DAOImpl_ChiTietHoaDon(em);
            chiTietHoaDonDoiDAO = new DAOImpl_ChiTietHoaDonDoi(em);
            chiTietHoaDonHoanTraDAO = new DAOImpl_ChiTietHoaDonHoanTra(em);
            hoaDonDAO = new DAOImpl_HoaDon(em);
            hoaDonDoiHangDAO = new DAOImpl_HoaDonDoiHang(em);
            hoaDonHoanTraDAO = new DAOImpl_HoaDonHoanTra(em);
            khachHangDAO = new DaoImpl_KhachHang(em);
            khuyenMaiDAO = new DaoImpl_KhuyenMai(em);
            mauSacDAO = new DAOImpl_MauSac(em);
            nhaCungCapDAO = new DaoImpl_NhaCungCap(em);
            nhanVienDAO = new DaoImpl_NhanVien(em);
            nhomKhuyenMaiDAO = new DaoImpl_NhomKhuyenMai(em);
            nhomSanPhamDAO = new DaoImpl_NhomSanPham(em);
            sachDAO = new DaoImpl_Sach(em);
            sanPhamDAO = new DaoImpl_SanPham(em);
            taiKhoanDAO = new DaoImpl_TaiKhoan(em);
            thongKeDAO = new DAOImpl_ThongKe();
            vanPhongPhamDAO = new DaoImpl_VanPhongPham(em);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Khởi tạo ServerSocket
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server đang chạy trên port " + PORT + "...");

            while (true) {
                // Chấp nhận kết nối từ client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client đã kết nối: " + clientSocket.getInetAddress());

                // Tạo một thread mới để xử lý client
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            // Đọc yêu cầu từ client
            String request = in.readLine();
            if (request == null) {
                return;
            }

            // Xử lý yêu cầu
            String[] parts = request.split(";");
            String command = parts[0];

            switch (command) {
                // TaiKhoan
                case "SEND_OTP":
                    String email = parts[1];
                    int otp = taiKhoanDAO.sendEmail(email);
                    out.println("OTP;" + otp);
                    break;

                case "GET_ALL_TAIKHOAN":
                    List<TaiKhoan> taiKhoans = taiKhoanDAO.getAllTaiKhoan();
                    out.println("ALL_TAIKHOAN;" + taiKhoans.toString());
                    break;

                // ThongKe
                case "GET_TOP_PRODUCTS":
                    List<ProductInfo> topProducts = thongKeDAO.getTopSellingProducts();
                    out.println("TOP_PRODUCTS;" + topProducts.toString());
                    break;

                case "GET_TOTAL_REVENUE":
                    int year = Integer.parseInt(parts[1]);
                    double revenue = thongKeDAO.tongDoanhThu(year);
                    out.println("TOTAL_REVENUE;" + revenue);
                    break;

                // HoaDon
                case "GET_HOADON_BY_ID":
                    String maHoaDon = parts[1];
                    HoaDon hoaDon = hoaDonDAO.getHoaDontheoMa(maHoaDon);
                    out.println("HOADON;" + (hoaDon != null ? hoaDon.toString() : "null"));
                    break;

                // ChiTietHoaDonDoi
                case "GET_CHITIET_HOADONDOI":
                    String maHDD = parts[1];
                    String maSP = parts[2];
                    ChiTietHoaDonDoi chiTietHoaDonDoi = chiTietHoaDonDoiDAO.getHoaDontheoMa(maHDD, maSP);
                    out.println("CHITIET_HOADONDOI;" + (chiTietHoaDonDoi != null ? chiTietHoaDonDoi.toString() : "null"));
                    break;

                // KhachHang
                case "GET_KHACHHANG_BY_ID":
                    String maKhachHang = parts[1];
                    KhachHang khachHang = khachHangDAO.getKHTheoMa(maKhachHang);
                    out.println("KHACHHANG;" + (khachHang != null ? khachHang.toString() : "null"));
                    break;

                // SanPham
                case "GET_SANPHAM_BY_ID":
                    String maSanPham = parts[1];
                    SanPham sanPham = sanPhamDAO.getSanPhamtheoMa(maSanPham);
                    out.println("SANPHAM;" + (sanPham != null ? sanPham.toString() : "null"));
                    break;

                default:
                    out.println("ERROR;Yêu cầu không hợp lệ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}