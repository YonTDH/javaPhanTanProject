package iuh.fit.models;

import java.io.Serializable;
import java.util.Objects;

public class ChiTietHoaDonDoiId implements Serializable {
    private static final long serialVersionUID = 1L;

    private HoaDonDoiHang hoaDonDoiHang;
    private SanPham sanPham;

    public ChiTietHoaDonDoiId() {}

    public ChiTietHoaDonDoiId(HoaDonDoiHang hoaDonDoiHang, SanPham sanPham) {
        this.hoaDonDoiHang = hoaDonDoiHang;
        this.sanPham = sanPham;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietHoaDonDoiId)) return false;
        ChiTietHoaDonDoiId that = (ChiTietHoaDonDoiId) o;
        return Objects.equals(hoaDonDoiHang, that.hoaDonDoiHang) &&
                Objects.equals(sanPham, that.sanPham);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hoaDonDoiHang, sanPham);
    }
}
