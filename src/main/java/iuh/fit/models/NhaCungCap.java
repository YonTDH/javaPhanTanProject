package iuh.fit.models;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "NhaCungCap")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhaCungCap {

    @Id
    @Column(name = "maNhaCungCap", nullable = false, unique = true, length = 50)
    private String maNhaCungCap;

    @Column(name = "tenNhaCungCap", nullable = false, length = 255)
    private String tenNhaCungCap;

    @Column(name = "diaChi", length = 500)
    private String diaChi;

    @Column(name = "soDienThoai", length = 20)
    private String soDienThoai;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "trangThai", nullable = false)
    private Boolean trangThai; // true - đang hợp tác, false - ngừng hợp tác

    // Quan hệ một-nhiều với SanPham
    @OneToMany(mappedBy = "nhaCungCap", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SanPham> danhSachSanPham;
}