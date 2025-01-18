package model;

import jakarta.persistence.*;

@Entity
@Table(name = "mau_sac")
public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_mau")
    private int maMau;

    @Column(name = "ten_mau")
    private String tenMau;

}
