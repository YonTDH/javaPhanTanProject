package entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Entity
@ToString
@Table(name = "HoaDonDoiHang")
@NamedQueries({ 
		@NamedQuery(name = "HoaDonDoiHang.getAllHoaDonDoiHang_20", query = "SELECT h FROM HoaDonDoiHang h"),
		@NamedQuery(name = "HoaDonDoiHang.getHoaDonDoiHangtheoMaHT", query = "SELECT h FROM HoaDonDoiHang h WHERE h.hoaDonHoanTra.id = :maHT") 
})


public class HoaDonDoiHang implements Serializable {

	private static final long serialVersionUID = 7374626181841798328L;
	@Id
    private String maHoaDonDoi;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hoaDonHoanTra")
    private HoaDonHoanTra hoaDonHoanTra;
	@Column(name = "ghiChu", columnDefinition = "nvarchar(255)")
    private String ghiChu;
    private double tienHoanTra;
    private double chietKhau;
    @Column(name = "khuyenMai", columnDefinition = "nvarchar(255)")
    private String khuyenMai;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hoaDonDoiHang")
    private List<ChiTietHoaDonDoi> chiTietHoaDonDoi;

    public HoaDonDoiHang(String maHoaDonDoi, HoaDonHoanTra hoaDonHoanTra, String ghiChu, double tienHoanTra, double chietKhau, String khuyenMai) {
        this.maHoaDonDoi = maHoaDonDoi;
        this.hoaDonHoanTra = hoaDonHoanTra;
        this.ghiChu = ghiChu;
        this.tienHoanTra = tienHoanTra;
        this.chietKhau = chietKhau;
        this.khuyenMai = khuyenMai;
    }

    public HoaDonDoiHang(String maHoaDonDoi) {
        this.maHoaDonDoi = maHoaDonDoi;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HoaDonDoiHang other = (HoaDonDoiHang) obj;
        return Objects.equals(this.maHoaDonDoi, other.maHoaDonDoi);
    }
    
}
