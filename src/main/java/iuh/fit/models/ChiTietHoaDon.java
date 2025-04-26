package iuh.fit.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ChiTietHoaDon")
@NamedQueries({
        @NamedQuery(name = "ChiTietHoaDon.getAll", query = "SELECT cthd FROM ChiTietHoaDon cthd"),
        @NamedQuery(name = "ChiTietHoaDon.findByHoaDon", query = "SELECT cthd FROM ChiTietHoaDon cthd WHERE cthd.hoaDon.maHoaDon = :maHoaDon")
})
public class ChiTietHoaDon implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ChiTietHoaDonPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("maHoaDon")
    @JoinColumn(name = "maHoaDon")
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("maSanPham")
    @JoinColumn(name = "maSanPham")
    private SanPham sanPham;

    private int soLuong;
    private double donGia;
    private double giamGia;

    public ChiTietHoaDon(HoaDon hoaDon, SanPham sanPham, int soLuong, double donGia, double giamGia) {
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.giamGia = giamGia;
        this.id = new ChiTietHoaDonPK(hoaDon.getMaHoaDon(), sanPham.getMaSanPham());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietHoaDon)) return false;
        ChiTietHoaDon that = (ChiTietHoaDon) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
