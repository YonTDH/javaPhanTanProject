package iuh.fit.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ChiTietHoaDonPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "maHoaDon")
    private String maHoaDon;

    @Column(name = "maSanPham")
    private String maSanPham;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietHoaDonPK)) return false;
        ChiTietHoaDonPK that = (ChiTietHoaDonPK) o;
        return Objects.equals(maHoaDon, that.maHoaDon) &&
                Objects.equals(maSanPham, that.maSanPham);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maHoaDon, maSanPham);
    }
}
