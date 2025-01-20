package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mau_sac")
@Data
public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_mau")
    private int maMau;

    @Column(name = "ten_mau")
    private String tenMau;

    @OneToOne(mappedBy = "mauSac", cascade = CascadeType.ALL)
    private VanPhongPham vanPhongPham;

}
