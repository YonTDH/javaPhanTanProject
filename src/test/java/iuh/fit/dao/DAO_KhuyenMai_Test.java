package iuh.fit.dao;

import iuh.fit.dao.daoimpl.DaoImpl_KhuyenMai;
import iuh.fit.models.KhachHang;
import iuh.fit.models.KhuyenMai;
import iuh.fit.models.NhomKhachHang;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class DAO_KhuyenMai_Test {
    private static DAO_KhuyenMai khuyenMaiDAO;
    private static String maKhuyenMaiTest = "KMTEST-00001";

    @BeforeAll
    static void init() {
        khuyenMaiDAO = new DaoImpl_KhuyenMai();
    }

    @Test
    @Order(1)
    void testGetAlltbKhuyenMai() throws RemoteException {
        List<KhuyenMai> danhSach = khuyenMaiDAO.getAlltbKM();
        Assertions.assertNotNull(danhSach);
        Assertions.assertTrue(danhSach.size() >= 0);
        danhSach.forEach((kh) ->{
            System.out.println(kh.getTenKhuyenMai()+ "\n");
        });
    }


    @Test
    @Order(2)
    void testCreateKhuyenMai() throws RemoteException{
        KhuyenMai khuyenMai = KhuyenMai.builder()
                .maKhuyenMai(maKhuyenMaiTest)
                .tenKhuyenMai("Test khuyến mãi")
                .tyLeKhuyenMai(0.35)
                .ghiChu("Chỉ test")
                .trangThai("Đang hoạt động")
                .giaTriKhuyenMaiToiDa(500000)
                .ngayBatDau(LocalDateTime.now())
                .ngayKetThuc(LocalDateTime.of(2025,12,30,12, 0))
                .tienToiThieu(500000)
                .build();
        boolean result = khuyenMaiDAO.createKhuyenMai(khuyenMai);
        Assertions.assertTrue(result);
    }

    @Test
    @Order(3)
    void testGetKMTheoMa() throws RemoteException {
        KhuyenMai kh = khuyenMaiDAO.getKMtheoMa(maKhuyenMaiTest);
        Assertions.assertNotNull(kh);
        Assertions.assertEquals(maKhuyenMaiTest, kh.getMaKhuyenMai());
        System.out.println(kh.getTenKhuyenMai());
    }

    @Test
    @Order(5)
    void testUpdateKhuyenMai() throws RemoteException {
        KhuyenMai khuyenMai = khuyenMaiDAO.getKMtheoMa(maKhuyenMaiTest);
        khuyenMai.setTenKhuyenMai("Update Khuyê Mai");
        boolean updated = khuyenMaiDAO.updateKhuyenMai(khuyenMai);
        Assertions.assertTrue(updated);

        KhuyenMai updatedKH = khuyenMaiDAO.getKMtheoMa(maKhuyenMaiTest);
        Assertions.assertEquals("Update Khuyê Mai", updatedKH.getTenKhuyenMai());
    }
}
