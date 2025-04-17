package iuh.fit.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "NhanVien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhanVien {

    @Id
    @Column(name = "maNhanVien", length = 50)
    private String maNhanVien;

    @Column(name = "tenNhanVien", length = 100, nullable = false)
    private String tenNhanVien;

    @Column(name = "soDienThoai", length = 15)
    private String soDienThoai;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "diaChi", length = 255)
    private String diaChi;

    @Temporal(TemporalType.DATE)
    @Column(name = "ngayVaoLam")
    private Date ngayVaoLam;

    @Column(name = "trangThai")
    private Boolean trangThai;

    @Enumerated(EnumType.STRING)
    private ChucVu chucVu;

    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HoaDon> danhSachHoaDon;
}

