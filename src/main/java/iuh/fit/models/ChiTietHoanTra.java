// src/main/java/iuh/fit/models/ChiTietHoanTra.java
package iuh.fit.models;

import java.io.Serializable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "ChiTietHoanTra")
@IdClass(ChiTietHoanTraId.class)
@NamedQueries({
        @NamedQuery(name = "ChiTietHoanTra.getAllChiTietHoanTra", query = "SELECT c FROM ChiTietHoanTra c"),
        @NamedQuery(name = "ChiTietHoanTra.getHoaDontheoMa", query = "SELECT c FROM ChiTietHoanTra c WHERE c.hoaDonHoanTra = :maHD AND c.sanPham = :maSP")
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ChiTietHoanTra implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "hoaDonHoanTra")
    private HoaDonHoanTra hoaDonHoanTra;

    @Id
    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sanPham")
    private SanPham sanPham;

    private int soLuong;
    private double thanhTien;

    public ChiTietHoanTra(HoaDonHoanTra hoaDonHoanTra, SanPham sanPham, int soLuong, double thanhTien) {
        this.hoaDonHoanTra = hoaDonHoanTra;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

}
