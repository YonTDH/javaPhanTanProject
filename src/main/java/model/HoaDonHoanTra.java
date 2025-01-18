package model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hoa_don_hoan_tra")
@Data
public class HoaDonHoanTra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_hoa_don_hoan_tra")
    private int maHoaDonHoanTra;

    @Column(name = "ngay_hoan")
    private LocalDateTime ngayHoan;

    @ManyToOne
    @JoinColumn(name = "nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "hoa_don")
    private HoaDon hoaDon;


    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "tinh_trang")
    private int tinhTrangHoaDon;

    @Column(name = "tien_hoan_tra")
    private double tienHoanTra;

    @OneToOne
    @JoinColumn(name = "hoa_don_doi_hang")
    private HoaDonDoiHang hoaDonDoiHang;

    @OneToMany(mappedBy = "hoaDonHoanTra", cascade = CascadeType.ALL)
    private List<ChiTietHoanTra> chiTietHoanTraList;
}
