package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "khach_hang")
@Data
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_khach_hang")
    private int maKhachHang;

    @Column(name = "ten_khach_hang")
    private String tenKhachHang;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "nhom_khach_hang")
    private NhomKhachHang nhomKhachHang;

    @Column(name = "tong_tien_mua")
    private Double tongTienMua;

    @Column(name = "so_luong_hoa_don")
    private int soLuongHoaDon;

}
