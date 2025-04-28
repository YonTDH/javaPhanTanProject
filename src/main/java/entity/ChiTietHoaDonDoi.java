/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author nguyen chau tai
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ChiTietDoiHang")
@EqualsAndHashCode
@NamedQueries({
	@NamedQuery(name = "ChiTietHoaDonDoi.getAllChiTietDonDoi", query = "SELECT c FROM ChiTietHoaDonDoi c"),
	@NamedQuery(name = "ChiTietHoaDonDoi.getHoaDontheoMa", query = "SELECT c FROM ChiTietHoaDonDoi c WHERE c.hoaDonDoiHang = :maHDD AND c.sanPham = :maSP")
})
public class ChiTietHoaDonDoi implements Serializable{
	private static final long serialVersionUID = 267692227133307790L;
	@Id
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "hoaDonDoiHang")
    private HoaDonDoiHang hoaDonDoiHang;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sanPham")
    private SanPham sanPham;
	@EqualsAndHashCode.Exclude
    private int soLuong;
    @EqualsAndHashCode.Exclude
    private double thanhTien;

    public ChiTietHoaDonDoi(HoaDonDoiHang hoaDonDoiHang, SanPham sanPham, int soLuong, double thanhTien) {
        super();
        this.hoaDonDoiHang = hoaDonDoiHang;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

 
}
