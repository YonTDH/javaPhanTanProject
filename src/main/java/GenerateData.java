import model.*;
import net.datafaker.Faker;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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


    private NhaCungCap generateNhaCungCap(){
        NhaCungCap nhaCungCap = new NhaCungCap();
        nhaCungCap.setTenNCC(faker.company().name());
        nhaCungCap.setEmail(faker.internet().emailAddress());
        nhaCungCap.setDiaChiNCC(faker.location().building());
        nhaCungCap.setSoDienThoai(faker.phoneNumber().phoneNumber());

        return  nhaCungCap;
    }

    public static void main(String[] args) {

    }
}
