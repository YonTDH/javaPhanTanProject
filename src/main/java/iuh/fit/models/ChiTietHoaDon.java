package iuh.fit.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ChiTietHoaDon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChiTietHoaDon {

    // Mã chi tiết hóa đơn (nếu cần, bạn có thể thêm mã riêng)
    @EmbeddedId
    private ChiTietHoaDonPK maChiTietHoaDon;

    @MapsId("maHoaDon")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maHoaDon", insertable = false, updatable = false)
    private HoaDon hoaDon;

    @MapsId("maSanPham")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maSanPham", insertable = false, updatable = false)
    private SanPham sanPham;

    // Số lượng sản phẩm trong chi tiết hóa đơn
    @Column(name = "soLuong")
    private int soLuong;

    // Giá tiền của sản phẩm tại thời điểm bán
    @Column(name = "giaTien")
    private double giaTien;

    // Thành tiền (Số lượng * Giá tiền)
    @Column(name = "thanhTien")
    private double thanhTien;

    // Hàm tính thành tiền cho chi tiết hóa đơn
    public void tinhThanhTien() {
        this.thanhTien = this.soLuong * this.giaTien;
    }
}
