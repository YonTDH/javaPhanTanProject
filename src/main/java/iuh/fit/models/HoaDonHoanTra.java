// src/main/java/iuh/fit/models/HoaDonHoanTra.java
package iuh.fit.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "HoaDonHoanTra")
@NamedQueries({
        @NamedQuery(name = "HoaDonHoanTra.getAllHoaDonHoanTra", query = "SELECT h FROM HoaDonHoanTra h")
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HoaDonHoanTra implements Serializable {

    private static final long serialVersionUID = 2956097488696422597L;

    @Id
    @EqualsAndHashCode.Include
    private String maHoaDonHoanTra;

    private LocalDateTime ngayHoan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhanVien")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoaDon")
    private HoaDon hoaDon;

    @Column(name = "ghiChu", columnDefinition = "nvarchar(255)")
    private String ghiChu;

    private int tinhTrangHoaDon;
    private double tienHoanTra;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hoaDonHoanTra")
    private List<ChiTietHoanTra> chiTietHoanTra;

    public HoaDonHoanTra(String maHoaDonHoanTra) {
        this.maHoaDonHoanTra = maHoaDonHoanTra;
    }

    public HoaDonHoanTra(String maHoaDonHoanTra, LocalDateTime ngayHoan, NhanVien nhanVien, HoaDon hoaDon,
                         String ghiChu, int tinhTrangHoaDon, double tienHoanTra) {
        this.maHoaDonHoanTra = maHoaDonHoanTra;
        this.ngayHoan = ngayHoan;
        this.nhanVien = nhanVien;
        this.hoaDon = hoaDon;
        this.ghiChu = ghiChu;
        this.tinhTrangHoaDon = tinhTrangHoaDon;
        this.tienHoanTra = tienHoanTra;
    }

    // Hàm tiện ích
//    public void addChiTietHoanTra(ChiTietHoanTra chiTiet) {
//        this.chiTietHoanTra.add(chiTiet);
//        chiTiet.setHoaDonHoanTra(this);
//    }
}
