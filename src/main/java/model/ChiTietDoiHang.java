package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chi_tiet_doi_hang")
@Data
public class ChiTietDoiHang {

    @ManyToOne
    @JoinColumn(name = "hoa_don_doi_hang")
    @Id
    private HoaDonDoiHang hoaDonDoiHang;

    @OneToOne
    @JoinColumn(name = "san_pham")
    private SanPham sanPham;

    @Column(name = "so_luong")
    private int soLuong;

    @Column(name = "thanh_tien")
    private double thanhTien;

    @ManyToOne
    @JoinColumn(name = "nhan_vien")
    private NhanVien nhanVien;


}
