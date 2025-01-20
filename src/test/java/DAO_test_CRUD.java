import dao.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.*;

public class DAO_test_CRUD {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadb-up"); // Cập nhật tên persistence unit của bạn
        EntityManager em = emf.createEntityManager();

        //Create
        TaiKhoan taiKhoan = new GenerateData().generateTaiKhoan();
        CaLamViec caLamViec = new GenerateData().generateCaLamViec();
        NhanVien nhanVien = new GenerateData().generateNhanVien();
        KhachHang khachHang = new GenerateData().generateKhachHang();
        NhaCungCap nhaCungCap = new GenerateData().generateNhaCungCap();
        NhomSanPham nhomSanPhamSach = new GenerateData().generateNhomSach();
        NhomSanPham nhomVPP = new GenerateData().generateNhomVPP();
        Sach sach = new GenerateData().generateSach();
        MauSac mauSac = new GenerateData().generateMauSac();
        VanPhongPham vanPhongPham = new GenerateData().generateVanPhongPham();
        KhuyenMai khuyenMai = new GenerateData().generateKhuyenMai();


        sach.setNhomSanPham(nhomSanPhamSach);
        sach.setNhaCungCap(nhaCungCap);
        vanPhongPham.setNhaCungCap(nhaCungCap);
        vanPhongPham.setNhomSanPham(nhomVPP);
        vanPhongPham.setMauSac(mauSac);
        nhanVien.setTaiKhoan(taiKhoan);
        //Email có trong 2 table TaiKhoan & NhanVien nên setlại
        nhanVien.setEmail(taiKhoan.getEmail());
        nhanVien.setChucVu(new GenerateData().getRandomEnumValue(ChucVu.class));
        nhanVien.setCaLamViec(caLamViec);


        //thêm ok
        TaiKhoan_DAO taiKhoanDao = new TaiKhoan_DAO(em);
        if (taiKhoanDao.add(taiKhoan)) {
            System.out.println("Thêm thành công tài khoản: " + taiKhoan.toString());
        } else {
            System.out.println("Thêm tài khoản thất bại");
        }

        //thêm ok
        CaLamViec_DAO caLamViecDao = new CaLamViec_DAO(em);
        if (caLamViecDao.addNewCaLamViec(caLamViec)) {
            System.out.println("Thêm thành công ca làm việc: " + caLamViec.toString());
        } else {
            System.out.println("Thêm ca làm việc thất bại");
        }

        //thêm ok
        NhanVien_DAO nhanVienDao = new NhanVien_DAO(em);
        if (nhanVienDao.addNewNhanVien(nhanVien)) {
            System.out.println("Thêm thành công nhân viên: " + nhanVien.toString());
        } else {
            System.out.println("Thêm nhân viên thất bại");
        }

        //thêm ok
        KhachHang_DAO khachHangDao = new KhachHang_DAO(em);
        if (khachHangDao.addNewKhachHang(khachHang)) {
            System.out.println("Thêm thành công khách hàng: " + khachHang.toString());
        } else {
            System.out.println("Thêm khách hàng thất bại");
        }

        //thêm ok
        NhaCungCap_DAO nhaCungCapDao = new NhaCungCap_DAO(em);
        if (nhaCungCapDao.addNewNhaCungCap(nhaCungCap)) {
            System.out.println("Thêm thành công nhà cung cấp: " + nhaCungCap.toString());
        } else {
            System.out.println("Thêm nhà cung cấp thất bại");
        }
        //Thêm thành công
        KhuyenMai_DAO khuyenMaiDao = new KhuyenMai_DAO(em);
        if (khuyenMaiDao.addNewKhuyenMai(khuyenMai)) {
            System.out.println("Thêm thành công khuyến mãi: " + khuyenMai.toString());
        } else {
            System.out.println("Thêm khuyến mãi thất bại");
        }



        NhomSanPham_DAO nhomSanPhamDao = new NhomSanPham_DAO(em);
        SanPham_DAO sanPhamDao = new SanPham_DAO(em);
        if (nhomSanPhamDao.addNewNhomSanPham(nhomSanPhamSach)) {
            if(sanPhamDao.addNewSanPham(sach)){
                System.out.print("Thêm thành công sản phẩm sách: " + sach.toString());
            } else {
                System.out.println("Thêm sách thất bại");
            }
            System.out.println("Thêm thành công khuyến mãi: " + khuyenMai.toString());
        } else {
            System.out.println("Thêm khuyến mãi thất bại");
        }

        if(nhomSanPhamDao.addNewNhomSanPham(nhomVPP)){
            System.out.print("Thêm thành công nhóm sản phẩm VPP: " + nhomVPP.toString());
        } else {
            System.out.println("Thêm nhóm VPP thất bại");
        }
        MauSac_DAO mauSacDao = new MauSac_DAO(em);

        if(mauSacDao.addNewMauSac(mauSac)){
            System.out.print("Thêm thành công màu sắc: " + mauSac.toString());
            if(sanPhamDao.addNewSanPham(vanPhongPham)){
                System.out.print("Thêm thành công sản phẩm VPP: " + vanPhongPham.toString());
            } else {
                System.out.println("Thêm VPP thất bại");
            }
        } else {
            System.out.println("Thêm VPP thất bại");
        }








        em.close();
        emf.close();


    }
}
