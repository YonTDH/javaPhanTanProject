package server;

import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;

import dao.DAO_ChiTietHoaDon;
import dao.DAO_ChiTietHoaDonDoi;
import dao.DAO_ChiTietHoaDonHoanTra;
import dao.DAO_HoaDon;
import dao.DAO_HoaDonDoiHang;
import dao.DAO_HoaDonHoanTra;
import dao.DAO_KhachHang;
import dao.DAO_KhuyenMai;
import dao.DAO_MauSac;
import dao.DAO_NhaCungCap;
import dao.DAO_NhanVien;
import dao.DAO_NhomKhuyenMai;
import dao.DAO_NhomSanPham;
import dao.DAO_Sach;
import dao.DAO_SanPham;
import dao.DAO_TaiKhoan;
import dao.DAO_ThongKe;
import dao.DAO_VanPhongPham;
import daoImpl.DAO_ChiTietHoaDonDoi_Impl;
import daoImpl.DAO_ChiTietHoaDonHoanTra_Impl;
import daoImpl.DAO_ChiTietHoaDon_Impl;
import daoImpl.DAO_HoaDonDoiHang_Impl;
import daoImpl.DAO_HoaDonHoanTra_Impl;
import daoImpl.DAO_HoaDon_Impl;
import daoImpl.DAO_KhachHang_Impl;
import daoImpl.DAO_KhuyenMai_Impl;
import daoImpl.DAO_MauSac_Impl;
import daoImpl.DAO_NhaCungCap_Impl;
import daoImpl.DAO_NhanVien_Impl;
import daoImpl.DAO_NhomKhuyenMai_Impl;
import daoImpl.DAO_NhomSanPham_Impl;
import daoImpl.DAO_Sach_Impl;
import daoImpl.DAO_SanPham_Impl;
import daoImpl.DAO_TaiKhoan_Impl;
import daoImpl.DAO_ThongKe_Impl;
import daoImpl.DAO_VanPhongPham_Impl;


public class Server {
	private static final String URL = "rmi://localhost:9090/";
	public static void main(String[] args) {
		try {
		
			DAO_HoaDon hoaDon = new DAO_HoaDon_Impl();
			DAO_ChiTietHoaDon chiTietHoaDon = new DAO_ChiTietHoaDon_Impl();
			DAO_HoaDonHoanTra hoaDonHoanTra = new DAO_HoaDonHoanTra_Impl();
			DAO_ChiTietHoaDonHoanTra chiTietHoanTra = new DAO_ChiTietHoaDonHoanTra_Impl();
			DAO_HoaDonDoiHang hoaDonDoiHang = new DAO_HoaDonDoiHang_Impl();
			DAO_ChiTietHoaDonDoi chiTietHoaDonDoi = new DAO_ChiTietHoaDonDoi_Impl();
			DAO_KhachHang khachHang = new DAO_KhachHang_Impl();
			DAO_NhanVien nhanVien = new DAO_NhanVien_Impl();
			DAO_NhaCungCap nhaCungCap = new DAO_NhaCungCap_Impl();
			DAO_Sach sach = new DAO_Sach_Impl();
			DAO_VanPhongPham vanPhongPham = new DAO_VanPhongPham_Impl();
			DAO_KhuyenMai khuyenMai = new DAO_KhuyenMai_Impl();
			DAO_NhomKhuyenMai nhomKhuyenMai = new DAO_NhomKhuyenMai_Impl();
			DAO_MauSac mauSac = new DAO_MauSac_Impl();
			DAO_NhomSanPham nhomSanPham = new DAO_NhomSanPham_Impl();
			DAO_TaiKhoan taiKhoan = new DAO_TaiKhoan_Impl();
			DAO_ThongKe thongKe = new DAO_ThongKe_Impl();
			DAO_SanPham sanPham = new DAO_SanPham_Impl();
				
			Context context = new InitialContext();
			
			LocateRegistry.createRegistry(9090);
			
			context.bind(URL + "hoaDon", hoaDon);
            context.bind(URL + "chiTietHoaDon", chiTietHoaDon);
            context.bind(URL + "hoaDonHoanTra", hoaDonHoanTra);
            context.bind(URL + "chiTietHoanTra", chiTietHoanTra);
            context.bind(URL + "hoaDonDoiHang", hoaDonDoiHang);
            context.bind(URL + "chiTietHoaDonDoi", chiTietHoaDonDoi);
            context.bind(URL + "khachHang", khachHang);
            context.bind(URL + "nhanVien", nhanVien);
            context.bind(URL + "nhaCungCap", nhaCungCap);
            context.bind(URL + "sach", sach);	
            context.bind(URL + "vanPhongPham", vanPhongPham);
            context.bind(URL + "khuyenMai", khuyenMai);
            context.bind(URL + "nhomKhuyenMai", nhomKhuyenMai);
            context.bind(URL + "mauSac", mauSac);
            context.bind(URL + "nhomSanPham", nhomSanPham);
            context.bind(URL + "taiKhoan", taiKhoan);
            context.bind(URL + "thongKe", thongKe);
            context.bind(URL + "sanPham", sanPham);
            
			
			System.out.println("Server is running...");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
