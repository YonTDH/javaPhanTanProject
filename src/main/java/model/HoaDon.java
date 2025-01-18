package model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hoa_don")
@Data
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_hoa_don")
    private int maHoaDon;

    @Column(name = "ngay_lap")
    private LocalDateTime ngayLap;

    @ManyToOne
    @JoinColumn(name = "nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "khach_hang")
    private KhachHang khachHang;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "tinh_trang")
    private int tinhTrangHoaDon;

    @Column(name = "tong_tien")
    private Double tongTien;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL)
    private List<ChiTietHoaDon> chiTietHoaDonList;


    @ManyToMany
    @JoinTable(
            name = "hoa_don_km",
            joinColumns = @JoinColumn(name = "ma_hoa_don"),
            inverseJoinColumns = @JoinColumn(name = "ma_khuyen_mai")
    )
    private List<KhuyenMai> khuyenMaiList;


    @OneToMany
    @JoinColumn(name = "hoa_don_hoan_tra")
    private List<HoaDonHoanTra> hoaDonHoanTraList;


}
