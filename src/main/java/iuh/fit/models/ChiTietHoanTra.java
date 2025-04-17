// src/main/java/iuh/fit/models/ChiTietHoanTra.java
package iuh.fit.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ChiTietHoanTra")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ChiTietHoanTra {

    /** nhúng composite key */
    @EmbeddedId
    private ChiTietHoanTraId id;

    /** ánh xạ many-to-one tới HoaDonHoanTra, dùng MapsId để link vào id.maHoaDonHoanTra */
    @MapsId("maHoaDonHoanTra")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maHoaDonHoanTra", insertable = false, updatable = false)
    private HoaDonHoanTra hoaDonHoanTra;

    /** ánh xạ many-to-one tới SanPham, dùng MapsId để link vào id.maSanPham */
    @MapsId("maSanPham")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maSanPham", insertable = false, updatable = false)
    private SanPham sanPham;

    @Column(name = "soLuong", nullable = false)
    private Integer soLuong;

    @Column(name = "lyDo", length = 255)
    private String lyDo;
}
