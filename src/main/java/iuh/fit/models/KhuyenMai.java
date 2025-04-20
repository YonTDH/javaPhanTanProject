package iuh.fit.models;


import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "KhuyenMai")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KhuyenMai {

    @Id
    @Column(name = "maKhuyenMai", nullable = false, unique = true, length = 50)
    private String maKhuyenMai;

    @Column(name = "tenKhuyenMai", nullable = false, length = 255)
    private String tenKhuyenMai;

    @Column(name = "moTa", columnDefinition = "TEXT")
    private String moTa;

    @Column(name = "phanTramGiam", precision = 5, scale = 2)
    private BigDecimal phanTramGiam;

    @Column(name = "soTienGiam", precision = 18, scale = 2)
    private BigDecimal soTienGiam;

    @Column(name = "ngayBatDau", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayBatDau;

    @Column(name = "ngayKetThuc", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayKetThuc;

    @Column(name = "trangThai", nullable = false)
    private Boolean trangThai; // true - đang áp dụng, false - đã kết thúc

    // Quan hệ với nhóm khuyến mãi
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNhomKhuyenMai", referencedColumnName = "maNhom")
    private NhomKhuyenMai nhomKhuyenMai;

    // Quan hệ một-nhiều với SanPham
    @OneToMany(mappedBy = "khuyenMai", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SanPham> danhSachSanPham;

    @OneToMany(mappedBy = "khuyenMai", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HoaDon> danhSachHoaDon;
}