import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.*;
import net.datafaker.Faker;
import org.hibernate.mapping.Collection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class GenerateData {

    Faker faker = new Faker();
    private final Random random = new Random();

    private TaiKhoan generateTaiKhoan(){
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenDangNhap(faker.internet().username());
        taiKhoan.setMatKhau(faker.internet().password());
        taiKhoan.setEmail(faker.internet().emailAddress());

        return taiKhoan;
    }

    private NhanVien generateNhanVien(){
        NhanVien nhanVien = new NhanVien();
        nhanVien.setHoTenNV(faker.name().name());
        nhanVien.setNgaySinh(randomDateBetween(1985, 2006));
        nhanVien.setSoDienThoai(faker.phoneNumber().phoneNumber());
        nhanVien.setGioiTinh(getRandomEnumValue(GioiTinh.class));
        nhanVien.setEmail(faker.internet().emailAddress());
        nhanVien.setChucVu(getRandomEnumValue(ChucVu.class));

        return nhanVien;
    }

    private CaLamViec generateCaLamViec(){
        CaLamViec caLamViec = new CaLamViec();
        LocalTime randomTime = randomTimeBetween(8, 18);

        caLamViec.setTenCa(faker.company().catchPhrase());
        caLamViec.setThoiGianBatDau(randomTime);
        caLamViec.setThoiGianKetThuc(randomTime.plusHours(4));
        return caLamViec;
    }


    //Nhóm sản phẩm cho sách
    private NhomSanPham generateNhomSach(){
        List<NhomSanPham> productGroups = new ArrayList<>();

        productGroups.add(new NhomSanPham("Sách giáo khoa"));
        productGroups.add(new NhomSanPham("Sách tham khảo"));
        productGroups.add(new NhomSanPham("Sách ngoại ngữ"));
        productGroups.add(new NhomSanPham("Sách thiếu nhi"));
        productGroups.add(new NhomSanPham("Sách văn học"));
        productGroups.add(new NhomSanPham("Sách kỹ năng sống"));
        productGroups.add(new NhomSanPham("Sách kinh tế"));


        return productGroups.get(faker.number().numberBetween(0, productGroups.size()));
    }

    //Nhóm sp cho văn phòng  phẩm
    private NhomSanPham generateNhomVPP(){
        List<NhomSanPham> productGroups = new ArrayList<>();

        productGroups.add(new NhomSanPham("Dụng cụ viết"));
        productGroups.add(new NhomSanPham("Giấy & Sổ tay"));
        productGroups.add(new NhomSanPham("Dụng cụ hỗ trợ"));
        productGroups.add(new NhomSanPham("Dụng cụ đo lường"));
        productGroups.add(new NhomSanPham("Sáng tạo"));
        productGroups.add(new NhomSanPham("Công nghệ"));


        return productGroups.get(faker.number().numberBetween(0, productGroups.size()));
    }

    private Sach generateSach(){
        Sach sach = new Sach();

        Double donGiaNhap = faker.number().randomDouble(2, 30000, 150000);

        sach.setTenSanPham(faker.book().title());
        sach.setSoLuongTon(faker.number().numberBetween(5, 20));
        sach.setDonGiaNhap(donGiaNhap);
        sach.setMoTa(faker.text().text());
        sach.setTinhTrang(faker.text().text());
        sach.setDonGiaBan(donGiaNhap+ 15000.0);
        sach.setVAT(faker.number().randomDouble(2, 0, 1));
        sach.setNgayTao(LocalDateTime.now());
        sach.setGiamGia(faker.number().randomDouble(2, 0, 1));
        sach.setTacGia(faker.book().author());
        sach.setNamXuatBan(faker.number().numberBetween(1968, 2025));
        sach.setNhaXuatBan(faker.book().publisher());
        sach.setSoTrang(faker.number().numberBetween(50, 500));

        return sach;
    }

    private VanPhongPham generateVanPhongPham(){

        Double donGiaNhap = faker.number().randomDouble(2, 30000, 100000);

        VanPhongPham vanPhongPham = new VanPhongPham();
        vanPhongPham.setTenSanPham(faker.commerce().productName());
        vanPhongPham.setSoLuongTon(faker.number().numberBetween(5, 20));
        vanPhongPham.setDonGiaNhap(donGiaNhap);
        vanPhongPham.setMoTa(faker.text().text());
        vanPhongPham.setTinhTrang(faker.text().text());
        vanPhongPham.setDonGiaBan(donGiaNhap+ 15000.0);
        vanPhongPham.setVAT(faker.number().randomDouble(2, 0, 1));
        vanPhongPham.setNgayTao(LocalDateTime.now());
        vanPhongPham.setGiamGia(faker.number().randomDouble(2, 0, 1));

        return vanPhongPham;
    }

    private MauSac generateMauSac(){
        MauSac mauSac = new MauSac();
        mauSac.setTenMau(faker.color().name());

        return mauSac;

    }


    private NhaCungCap generateNhaCungCap(){
        NhaCungCap nhaCungCap = new NhaCungCap();
        nhaCungCap.setTenNCC(faker.company().name());
        nhaCungCap.setEmail(faker.internet().emailAddress());
        nhaCungCap.setDiaChiNCC(faker.location().building());
        nhaCungCap.setSoDienThoai(faker.phoneNumber().phoneNumber());

        return  nhaCungCap;
    }



    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadb-up");
        EntityManager em = emf.createEntityManager();
        for (int i = 0; i < 10 ; i++) {
            NhanVien nhanVien = new GenerateData().generateNhanVien();
            TaiKhoan taiKhoan = new GenerateData().generateTaiKhoan();
            CaLamViec caLamViec = new GenerateData().generateCaLamViec();

            nhanVien.setTaiKhoan(taiKhoan);

            //Email có trong 2 table TaiKhoan & NhanVien nên setlại
            nhanVien.setEmail(taiKhoan.getEmail());


            nhanVien.setChucVu(new GenerateData().getRandomEnumValue(ChucVu.class));
            nhanVien.setCaLamViec(caLamViec);

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            em.persist(caLamViec);
            em.persist(nhanVien);

            transaction.commit();


        }
    }


    private LocalDate randomDateBetween(int startYear, int endYear){
        LocalDate startDate = LocalDate.of(startYear, 1, 1);
        LocalDate endDate = LocalDate.of(endYear, 12, 31);

        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();

        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);
        return LocalDate.ofEpochDay(randomDay);

    }

    private <T extends Enum<?>> T getRandomEnumValue(Class<T> enumClass) {
        T[] enumValues = enumClass.getEnumConstants();
        int randomIndex = ThreadLocalRandom.current().nextInt(enumValues.length);
        return enumValues[randomIndex];
    }

    private LocalTime randomTimeBetween(int startHour, int endHour){
        LocalTime startTime = LocalTime.of(startHour, 0, 0);
        LocalTime endTime = LocalTime.of(endHour, 0, 0);

        int startSecond = startTime.toSecondOfDay();
        int endSecond = endTime.toSecondOfDay();

        int randomSecond = ThreadLocalRandom.current().nextInt(startSecond, endSecond + 1);

        return LocalTime.ofSecondOfDay(randomSecond);
    }
}
