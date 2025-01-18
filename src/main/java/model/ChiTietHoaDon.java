package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chi_tiet_hoa_don")
@Data
public class ChiTietHoaDon {

    @Id
    @ManyToOne
    @JoinColumn(name = "ma_hoa_don")
    private HoaDon hoaDon;

    @OneToOne
    @JoinColumn(name = "san_pham")
    private SanPham sanPham;

    @Column(name = "so_luong")
    private int soLuong;

    @Column(name = "thanh_tien")
    private double thanhTien;







}
