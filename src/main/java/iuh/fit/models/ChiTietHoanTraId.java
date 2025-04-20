package iuh.fit.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class ChiTietHoanTraId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String hoaDonHoanTra;
    private String sanPham;

    public ChiTietHoanTraId() {}

    public ChiTietHoanTraId(String hoaDonHoanTra, String sanPham) {
        this.hoaDonHoanTra = hoaDonHoanTra;
        this.sanPham = sanPham;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietHoanTraId)) return false;
        ChiTietHoanTraId that = (ChiTietHoanTraId) o;
        return Objects.equals(hoaDonHoanTra, that.hoaDonHoanTra) &&
                Objects.equals(sanPham, that.sanPham);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hoaDonHoanTra, sanPham);
    }
}
