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

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "ChiTietHoaDon")
@NamedQueries({
		 @NamedQuery(name = "ChiTietHoaDon.getAllChiTietHoaDon", query = "SELECT c FROM ChiTietHoaDon c"),
		 @NamedQuery(name = "ChiTietHoaDon.deleteChiTietHoaDonVaSanPham", query = "DELETE FROM ChiTietHoaDon c WHERE c.hoaDon.id = :maHD AND c.sanPham.id = :maSP"),
		 @NamedQuery(name = "ChiTietHoaDon.getCTHoaDontheoMa", query = "SELECT c FROM ChiTietHoaDon c WHERE c.hoaDon.id = :maHoaDon AND c.sanPham.id = :maSanPham")
		 
})

public class ChiTietHoaDon implements Serializable {
	private static final long serialVersionUID = 4826746826060222945L;
	@Id
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "hoaDon")
	private HoaDon hoaDon;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sanPham")
    private SanPham sanPham;
	@EqualsAndHashCode.Exclude
    private int soLuong;
	@EqualsAndHashCode.Exclude
    private double thanhTien;

    public ChiTietHoaDon(HoaDon hoaDon, SanPham sanPham, int soLuong, double thanhTien) {
        super();
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    
}
