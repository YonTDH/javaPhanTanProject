package iuh.fit.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "KhachHang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KhachHang {

    @Id
    @Column(name = "maKhachHang", length = 50, nullable = false, unique = true)
    private String maKhachHang;

    @Column(name = "tenKhachHang", nullable = false, length = 100)
    private String tenKhachHang;

    @Column(name = "soDienThoai", length = 15)
    private String soDienThoai;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "diaChi", length = 255)
    private String diaChi;

    @Temporal(TemporalType.DATE)
    @Column(
            name = "ngayDangKy",
            columnDefinition = "DATE DEFAULT CURRENT_DATE",
            insertable = false,
            updatable = false
    )
    private Date ngayDangKy;

    private Boolean trangThai;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private NhomKhachHang nhomKhachHang;

    // Sửa 'mappedBy' thành 'khachHang' để ánh xạ chính xác
    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HoaDon> danhSachHoaDon;
}
