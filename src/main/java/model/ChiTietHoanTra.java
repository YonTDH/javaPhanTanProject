package model;


import jakarta.persistence.*;

@Entity
@Table(name = "chi_tiet_hoan_tra")
public class ChiTietHoanTra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "hoa_don_hoan_tra")
    private HoaDonHoanTra hoaDonHoanTra;

    @OneToOne
    @JoinColumn(name = "san_pham")
    private SanPham sanPham;
}
