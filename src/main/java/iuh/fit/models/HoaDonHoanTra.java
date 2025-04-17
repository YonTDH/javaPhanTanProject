// src/main/java/iuh/fit/models/HoaDonHoanTra.java
package iuh.fit.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HoaDonHoanTra")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class HoaDonHoanTra {

    @Id
    @Column(name = "maHoaDon", length = 50)
    private String maHoaDon;

    @Column(name = "ngayHoanTra")
    private LocalDateTime ngayHoanTra;

    @Column(name = "lyDo", length = 255)
    private String lyDo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maKhachHang")
    private KhachHang khachHang;

    @OneToMany(
            mappedBy = "hoaDonHoanTra",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<ChiTietHoanTra> chiTietHoanTraList = new ArrayList<>();
}
