package iuh.fit.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "HoaDon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDon {

    @Id
    @Column(name = "maHoaDon", length = 50)
    private String maHoaDon;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "ngayTao",
            columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP",
            insertable = false,
            updatable = false
    )
    private Date ngayTao;

    private double tongTien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maKhuyenMai")
    private KhuyenMai khuyenMai;

    private double soTienGiam;

    // --- thanhTien mặc định = tongTien - soTienGiam (MySQL GENERATED COLUMN) ---
    @Column(
            name = "thanhTien",
            columnDefinition = "DOUBLE GENERATED ALWAYS AS (tongTien - soTienGiam) STORED",
            insertable = false,
            updatable = false
    )
    private double thanhTien;

    // Đảm bảo ánh xạ chính xác đến mối quan hệ trong KhachHang
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "maKhachHang")
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNhanVien")
    private NhanVien nhanVien;
}

