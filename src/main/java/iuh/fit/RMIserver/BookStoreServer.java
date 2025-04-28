package iuh.fit.RMIserver;

import iuh.fit.dao.*;
import iuh.fit.dao.daoimpl.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BookStoreServer {
    public static final int PORT = 1099;
    public static void main(String[] args) {
        try {
            DAO_ChiTietHoaDon daoChiTietHoaDon= new DAOImpl_ChiTietHoaDon();
            DAO_ChiTietHoaDonDoi daoChiTietHoaDonDoi = new DAOImpl_ChiTietHoaDonDoi();
            DAO_ChiTietHoaDonHoanTra daoChiTietHoaDonHoanTra= new DAOImpl_ChiTietHoaDonHoanTra();
            DAO_HoaDon daoHoaDon = new DAOImpl_HoaDon();
            DAO_HoaDonDoiHang daoHoaDonDoiHang= new DAOImpl_HoaDonDoiHang();
            DAO_HoaDonHoanTra daoHoaDonHoanTra= new DAOImpl_HoaDonHoanTra();
            DAO_KhachHang daoKhachHang = new DAOImpl_KhachHang();
            DAO_KhuyenMai daoKhuyenMai = new DAOImpl_KhuyenMai();
            DAO_MauSac daoMauSac = new DAOImpl_MauSac();
            DAO_NhaCungCap daoNhaCungCap = new DAOImpl_NhaCungCap();
            DAO_NhanVien daoNhanVien = new DAOImpl_NhanVien();
            DAO_NhomKhuyenMai daoNhomKhuyenMai = new DAOImpl_NhomKhuyenMai();
            DAO_NhomSanPham daoNhomSanPham = new DAOImpl_NhomSanPham();
            DAO_Sach daoSach = new DAOImpl_Sach();
            DAO_SanPham daoSanPham = new DAOImpl_SanPham();
            DAO_TaiKhoan daoTaiKhoan = new DAOImpl_TaiKhoan();
            DAO_ThongKe daoThongKe = new DAOImpl_ThongKe();
            DAO_VanPhongPham daoVanPhongPham = new DAOImpl_VanPhongPham();

            // Tạo RMI Registry trên port 1099 (port mặc định của RMI)
            Registry registry = LocateRegistry.createRegistry(PORT);

            // Đăng ký tất cả các dịch vụ DAO
            registry.rebind("ChiTietHoaDonService", daoChiTietHoaDon);
            registry.rebind("ChiTietHoaDonDoiService", daoChiTietHoaDonDoi);
            registry.rebind("ChiTietHoaDonHoanTraService", daoChiTietHoaDonHoanTra);
            registry.rebind("HoaDonService", daoHoaDon);
            registry.rebind("HoaDonDoiHangService", daoHoaDonDoiHang);
            registry.rebind("HoaDonHoanTraService", daoHoaDonHoanTra);
            registry.rebind("KhachHangService", daoKhachHang);
            registry.rebind("KhuyenMaiService", daoKhuyenMai);
            registry.rebind("MauSacService", daoMauSac);
            registry.rebind("NhaCungCapService", daoNhaCungCap);
            registry.rebind("NhanVienService", daoNhanVien);
            registry.rebind("NhomKhuyenMaiService", daoNhomKhuyenMai);
            registry.rebind("NhomSanPhamService", daoNhomSanPham);
            registry.rebind("SachService", daoSach);
            registry.rebind("SanPhamService", daoSanPham);
            registry.rebind("TaiKhoanService", daoTaiKhoan);
            registry.rebind("ThongKeService", daoThongKe);
            registry.rebind("VanPhongPhamService", daoVanPhongPham);

            System.out.println("RMI Server is running on port " + PORT);
            System.out.println("All services have been registered successfully.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}