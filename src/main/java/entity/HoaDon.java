package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "HoaDon")
@NamedQueries({ 
		@NamedQuery(name = "HoaDon.getAllHoaDon", query = "SELECT h FROM HoaDon h"),
		@NamedQuery(name = "HoaDon.getAllHoaDon_InDay", query = "SELECT h FROM HoaDon h WHERE h.ngayLap = CURRENT_DATE") 
})


public class HoaDon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3812769171560799958L;
	@Id
    private String maHoaDon;
	@Column(name = "ngayLap", columnDefinition = "datetime")
    private LocalDateTime ngayLap;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhanVien")
    private NhanVien nhanVien;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khachHang")
    private KhachHang khachHang;
    @Column(name = "ghiChu", columnDefinition = "nvarchar(255)")
    private String ghiChu;
    private int tinhTrangHoaDon;
    private double tongTien;
    private double chietKhau;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hoaDon", cascade = CascadeType.ALL)
    private List<ChiTietHoaDon> chiTietHoaDon;
    
    
    private String khuyenMai;

    public HoaDon(String ma) {
        super();
        this.maHoaDon = ma;
    }

    public HoaDon(String maHoaDon, LocalDateTime ngayLap, NhanVien nhanVien, KhachHang khachHang, String ghiChu,
            int tinhTrangHoaDon, double tongTien, double chietKhau, String khuyenMai) {
        super();
        this.maHoaDon = maHoaDon;
        this.ngayLap = ngayLap;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.ghiChu = ghiChu;
        this.tinhTrangHoaDon = tinhTrangHoaDon;
        this.tongTien = tongTien;
        this.chietKhau = chietKhau;
        this.khuyenMai = khuyenMai;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.maHoaDon);
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
        final HoaDon other = (HoaDon) obj;
        return Objects.equals(this.maHoaDon, other.maHoaDon);
    }

}
