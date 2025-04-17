package iuh.fit.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "HoaDonDoiHang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDonDoiHang {

    @Id
    @Column(name = "maHoaDonDoiHang", length = 50)
    private String maHoaDonDoiHang;

    @Column(name = "ngayDoi")
    private LocalDateTime ngayDoi;

    @Column(name = "lyDo", length = 255)
    private String lyDo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maKhachHang")
    private KhachHang khachHang;

    @OneToMany(mappedBy = "hoaDonDoiHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChiTietHoaDonDoi> chiTietDoiHang;
}
