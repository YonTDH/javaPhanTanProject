package iuh.fit.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "HoaDon")
@NamedQueries({
        @NamedQuery(name = "HoaDon.getAll", query = "SELECT hd FROM HoaDon hd"),
        @NamedQuery(name = "HoaDon.findByMa", query = "SELECT hd FROM HoaDon hd WHERE hd.maHoaDon = :maHoaDon"),
        @NamedQuery(name = "HoaDon.findByKhachHang", query = "SELECT hd FROM HoaDon hd WHERE hd.khachHang.maKhachHang = :maKhachHang")
})
public class HoaDon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String maHoaDon;

    @Column(name = "ngayLap")
    private LocalDateTime ngayLap;

    private double tongTien;

    @Column(name = "hinhThucThanhToan", columnDefinition = "nvarchar(255)")
    private String hinhThucThanhToan;

    @Column(name = "ghiChu", columnDefinition = "nvarchar(255)")
    private String ghiChu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maKhachHang")
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNhanVien")
    private NhanVien nhanVien;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ChiTietHoaDon> chiTietHoaDon;

    public HoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
}
