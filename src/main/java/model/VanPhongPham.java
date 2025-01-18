package model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@DiscriminatorValue("Van Phong Pham")
public class VanPhongPham extends SanPham{
    @OneToOne
    @JoinColumn(name = "ma_mau")
    @Cascade(CascadeType.ALL)
    private MauSac mauSac;

    @Column(name = "noi_san_xuat")
    private String noiSanXuat;
}
