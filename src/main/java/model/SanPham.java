package model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "san_pham")
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "loai_san_pham", discriminatorType = DiscriminatorType.STRING)
public abstract class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_san_pham")
    private int maSanPham;

    @Column(name = "ten_san_pham")
    private String tenSanPham;


    @ManyToOne
    @JoinColumn(name = "ma_nhom_san_pham")
    private NhomSanPham nhomSanPham;


    @ManyToOne
    @JoinColumn(name = "ma_nha_cung_cap")
    private  NhaCungCap nhaCungCap;

    @Column(name = "so_luong_ton")
    private int soLuongTon;

    @Column(name = "don_gia_nhap")
    private Double donGiaNhap;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "tinh_trang")
    private String tinhTrang;

    @Column(name = "don_gia_ban")
    private Double donGiaBan;

    @Column(name = "vat")
    private Double vAT;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "giam_gia")
    private Double giamGia;

    @OneToOne(mappedBy = "sanPham")
    private ChiTietDoiHang chiTietDoiHang;


    @OneToOne(mappedBy = "sanPham")
    private ChiTietHoaDon chiTietHoaDon;


    @OneToOne(mappedBy = "sanPham")
    private ChiTietHoanTra chiTietHoanTra;

}
