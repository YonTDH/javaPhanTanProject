package iuh.fit.models;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "NhomKhuyenMai")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhomKhuyenMai {

    @Id
    @Column(name = "maNhom", nullable = false, unique = true, length = 50)
    private String maNhom;

    @Column(name = "tenNhom", nullable = false, length = 255)
    private String tenNhom;

    @Column(name = "moTa", columnDefinition = "TEXT")
    private String moTa;

    @Column(name = "trangThai", nullable = false)
    private Boolean trangThai; // true - đang hoạt động, false - tạm ngừng

    // Quan hệ 1-N với KhuyenMai (1 nhóm có nhiều khuyến mãi)
    @OneToMany(mappedBy = "nhomKhuyenMai", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<KhuyenMai> danhSachKhuyenMai;

    // Quan hệ N-1 với NhomSanPham (nếu khuyến mãi áp dụng theo nhóm sản phẩm)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNhomSanPham", referencedColumnName = "maNhom")
    private NhomSanPham nhomSanPham;

    // Quan hệ N-1 với NhomKhachHang (nếu khuyến mãi áp dụng theo nhóm khách hàng)
    @Enumerated(EnumType.STRING)
    @Column(name = "nhomKhachHang", length = 20)
    private NhomKhachHang nhomKhachHang;

    // Các phương thức tiện ích
    @PrePersist
    public void prePersist() {
        if (trangThai == null) {
            trangThai = true; // Mặc định là hoạt động khi tạo mới
        }
    }
}