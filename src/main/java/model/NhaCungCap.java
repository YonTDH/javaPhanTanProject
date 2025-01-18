package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "nha_cung_cap")
@Data
public class NhaCungCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_ncc")
    private int maNCC;

    @Column(name = "ten_ncc")
    private String tenNCC;

    @Column(name = "dia_chi")
    private String diaChiNCC;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "ghi_chu")
    private String ghiChu;
}
