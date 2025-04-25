// ChiTietHoaDonDoi.java
package iuh.fit.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ChiTietHoaDonDoi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChiTietHoaDonDoi {

    @EmbeddedId
    private ChiTietHoaDonDoiId maChiTietHoaDonDoi;

    @MapsId("maHoaDonDoiHang")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maHoaDonDoiHang", insertable = false, updatable = false)
    private HoaDonDoiHang hoaDonDoiHang;

    @MapsId("maSanPham")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maSanPham", insertable = false, updatable = false)
    private SanPham sanPham;

    @Column(name = "soLuong")
    private int soLuong;

    @Column(name = "giaTien")
    private double giaTien;

    @Column(name = "thanhTien")
    private double thanhTien;

    public void tinhThanhTien() {
        this.thanhTien = this.soLuong * this.giaTien;
    }
}
