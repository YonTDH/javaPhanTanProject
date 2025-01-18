package model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Sach")
public class Sach extends SanPham{
    @Column(name = "tac_gia")
    private String tacGia;

    @Column(name = "nam_xuat_ban")
    private int namXuatBan;

    @Column(name = "nha_xuat_ban")
    private String nhaXuatBan;

    @Column(name = "so_trang")
    private int soTrang;
}
