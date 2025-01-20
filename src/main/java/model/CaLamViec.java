package model;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "ca_lam_viec")
@Data
public class CaLamViec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_ca")
    private int maCa;

    @Column(name = "ten_ca")
    private String tenCa;

    @Column(name = "thoi_gian_bat_dau")
    private LocalTime thoiGianBatDau;

    @Column(name = "thoi_gian_ket_thuc")
    private LocalTime thoiGianKetThuc;

    @OneToMany(mappedBy = "caLamViec")
    private List<NhanVien> nhanVienList;
}
