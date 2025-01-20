package model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "nhom_san_pham")
@Data
public class NhomSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nhom_san_pham")
    private int maNhomSanPham;

    @Column(name = "ten_nhom_san_pham")
    private String tenNhomSanPham;

    @OneToMany(mappedBy = "nhomSanPham", cascade = CascadeType.ALL)
    private List<SanPham> sanPhamList;

    public NhomSanPham(String tenNhomSanPham) {
        this.tenNhomSanPham = tenNhomSanPham;
    }
}
