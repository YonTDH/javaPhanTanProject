package iuh.fit.models;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.Id;
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
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ChiTietDoiHang")
@IdClass(ChiTietHoaDonDoiId.class)
@NamedQueries({
        @NamedQuery(name = "ChiTietHoaDonDoi.getAllChiTietDonDoi", query = "SELECT c FROM ChiTietHoaDonDoi c"),
        @NamedQuery(name = "ChiTietHoaDonDoi.getHoaDontheoMa", query = "SELECT c FROM ChiTietHoaDonDoi c WHERE c.hoaDonDoiHang = :maHDD AND c.sanPham = :maSP")
})
public class ChiTietHoaDonDoi implements Serializable {
    private static final long serialVersionUID = 267692227133307790L;

    @Id
    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "hoaDonDoiHang")
    private HoaDonDoiHang hoaDonDoiHang;

    @Id
    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sanPham")
    private SanPham sanPham;

    private int soLuong;
    private double thanhTien;

    public ChiTietHoaDonDoi(HoaDonDoiHang hoaDonDoiHang, SanPham sanPham, int soLuong, double thanhTien) {
        this.hoaDonDoiHang = hoaDonDoiHang;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }
}

