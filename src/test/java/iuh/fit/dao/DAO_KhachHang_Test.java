package iuh.fit.dao;

import iuh.fit.dao.daoimpl.DaoImpl_KhachHang;
import iuh.fit.models.KhachHang;
import iuh.fit.models.NhomKhachHang;
import org.junit.jupiter.api.*;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DAO_KhachHang_Test {

    private static DAO_KhachHang khachHangDAO;

    @BeforeAll
    static void init() {
        khachHangDAO = new DaoImpl_KhachHang();
    }

    @Test
    @Order(1)
    void testGetAllKhachHang() throws RemoteException {
        List<KhachHang> danhSach = khachHangDAO.getAllKhachHang();
        Assertions.assertNotNull(danhSach);
        Assertions.assertTrue(danhSach.size() >= 0);
//        danhSach.forEach((kh) ->{
//            System.out.println(kh.getTenKhachHang()+ "\n");
//        });
    }

    @Test
    @Order(2)
    void testThemKhachHang() throws RemoteException {
        KhachHang kh = KhachHang.builder()
                .maKhachHang("KH002")
                .tenKhachHang("Nguyen Van A")
                .soDienThoai("0909123458")
                .email("abc@example.com")
                .diaChi("123 Lê Lợi")
                .ngayDangKy(new Date())
                .nhomKhachHang(NhomKhachHang.THUONG)
                .trangThai(true)
                .build();

        boolean result = khachHangDAO.themKhachHang(kh);
        Assertions.assertTrue(result);
    }
    private static String maTest = "KH002"; // Mã dùng chung cho các test

    @Test
    @Order(3)
    void testGetKHTheoMa() throws RemoteException {
        KhachHang kh = khachHangDAO.getKHTheoMa(maTest);
        Assertions.assertNotNull(kh);
        Assertions.assertEquals(maTest, kh.getMaKhachHang());
        System.out.println(kh.getTenKhachHang());
    }

    @Test
    @Order(4)
    void testGetKHTheoSDT() throws RemoteException {
        KhachHang kh = khachHangDAO.getKHTheoSDT("0909123458");
        Assertions.assertNotNull(kh);
        Assertions.assertEquals("0909123458", kh.getSoDienThoai());
        System.out.println(kh.getTenKhachHang());
    }

    @Test
    @Order(5)
    void testUpdateKhachHang() throws RemoteException {
        KhachHang kh = khachHangDAO.getKHTheoMa(maTest);
        kh.setTenKhachHang("Nguyen Van A to C");
        boolean updated = khachHangDAO.updateKhachHang(kh);
        Assertions.assertTrue(updated);

        KhachHang updatedKH = khachHangDAO.getKHTheoMa(maTest);
        Assertions.assertEquals("Nguyen Van A to C", updatedKH.getTenKhachHang());
    }

    @Test
    @Order(6)
    void testTimKhachHangTheoThongTin() throws RemoteException {
        List<KhachHang> found = khachHangDAO.timKhachHangTheoThongTin("Nguyen");
        Assertions.assertNotNull(found);
        Assertions.assertTrue(found.size() > 0);
    }

    @Test
    @Order(7)
    void testGetKhachHangTrong1Thang() throws RemoteException {
        List<KhachHang> recent = khachHangDAO.getKhachHangTrong1Thang();
        Assertions.assertNotNull(recent);

        System.out.println(recent);
    }

    @Test
    @Order(8)
    void testLocDuLieuKhachHang() throws RemoteException {
        List<KhachHang> list = khachHangDAO.locDuLieuKhachHang("THUONG");
        Assertions.assertNotNull(list);
        for (KhachHang kh : list) {
            Assertions.assertEquals(NhomKhachHang.THUONG, kh.getNhomKhachHang());
            System.out.println(kh.getTenKhachHang());
        }
    }


    @Test
    @Order(9)
    void testCapNhatNhomKhachHangCho1Nguoi() throws RemoteException {
        khachHangDAO.capNhatNhomKhachHang("KH001", NhomKhachHang.VIP);
        KhachHang khupdated = khachHangDAO.getKHTheoMa("KH001");
        Assertions.assertEquals(NhomKhachHang.VIP, khupdated.getNhomKhachHang());
    }

    @Test
    @Order(10)
    void testCapNhatNhomKhachHangChoTatCa() throws RemoteException {
        khachHangDAO.capNhatNhomKhachHang(null, NhomKhachHang.VIP);

        List<KhachHang> danhSachKhachHang = khachHangDAO.getAllKhachHang();
        for (KhachHang khachHang : danhSachKhachHang) {
            Assertions.assertEquals(NhomKhachHang.VIP, khachHang.getNhomKhachHang());
        }
    }
}
