package model;

import jakarta.persistence.*;
import lombok.Data;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "nhan_vien")
@Data
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nhan_vien")
    private int maNhanVien;

    @Column(name = "ho_ten")
    private String hoTenNV;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Enumerated(EnumType.STRING)
    @Column(name = "gioi_tinh")
    private GioiTinh gioiTinh;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tai_khoan")
    private TaiKhoan taiKhoan;

    @Enumerated(EnumType.STRING)
    @Column(name = "chuc_vu")
    private ChucVu chucVu;

    @ManyToOne
    @JoinColumn(name = "ca_lam_viec")
    private CaLamViec caLamViec;


    @OneToMany(mappedBy = "nhanVien")
    private List<HoaDon> hoaDonList;

    @OneToMany(mappedBy = "nhanVien")
    private List<HoaDonHoanTra> hoaDonHoanTraList;

    @OneToMany(mappedBy = "nhanVien")
    private List<ChiTietDoiHang> chiTietDoiHangList;




}
