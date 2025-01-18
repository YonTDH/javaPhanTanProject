package model;


import jakarta.persistence.*;

@Entity
@Table(name = "hoa_don_doi_hang")
public class HoaDonDoiHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_hoa_don_doi")
    private int maHoaDonDoi;

    @OneToOne(mappedBy = "hoaDonDoiHang")
    private HoaDonHoanTra hoaDonHoanTra;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "tien_hoan_tra")
    private double tienHoanTra;
}
