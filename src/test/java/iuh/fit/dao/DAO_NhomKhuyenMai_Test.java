package iuh.fit.dao;

import iuh.fit.dao.daoimpl.DaoImpl_KhachHang;
import iuh.fit.dao.daoimpl.DaoImpl_NhomKhuyenMai;
import iuh.fit.models.*;
import org.junit.jupiter.api.*;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DAO_NhomKhuyenMai_Test {

    private static DAO_NhomKhuyenMai daoNhomKhuyenMai;
    private static String maKhuyenMaiTest = "KMTEST-00001";

    @BeforeAll
    static void init() {
        daoNhomKhuyenMai = new DaoImpl_NhomKhuyenMai();
    }

    @Test
    @Order(1)
    void testGetAllNhomKM() throws RemoteException {
        List<NhomKhuyenMai> danhSach = daoNhomKhuyenMai.getAllNhomKM();
        Assertions.assertNotNull(danhSach);
        Assertions.assertTrue(danhSach.size() >= 0);
        danhSach.forEach((kh) ->{
            System.out.println(kh.getKhuyenMai().getTenKhuyenMai()+ "\n");
        });
    }

    @Test
    @Order(2)
    //Nhóm sp thiếu
    void testCreateNhomKM() throws RemoteException {
//        KhuyenMai khuyenMai = KhuyenMai.builder()
//                .maKhuyenMai(maKhuyenMaiTest)
//                .tenKhuyenMai("Test khuyến mãi")
//                .tyLeKhuyenMai(0.35)
//                .ghiChu("Chỉ test")
//                .trangThai("Đang hoạt động")
//                .giaTriKhuyenMaiToiDa(500000)
//                .ngayBatDau(LocalDateTime.now())
//                .ngayKetThuc(LocalDateTime.of(2025,12,30,12, 0))
//                .tienToiThieu(500000)
//                .build();
//        NhomSanPham nhomSanPham = NhomSanPham.builder()
//                .maNhom()
//                .tenNhom()
//                .danhSachSanPham()
//
//
//        boolean result = daoNhomKhuyenMai.createNhomKM(khuyenMai);
//        Assertions.assertTrue(result);
    }
}
