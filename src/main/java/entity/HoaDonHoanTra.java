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
@NoArgsConstructor
@ToString
@Entity
@Table(name = "HoaDonHoanTra")
@NamedQueries({ 
		@NamedQuery(name = "HoaDonHoanTra.getAllHoaDonHoanTra", query = "SELECT h FROM HoaDonHoanTra h"),
		@NamedQuery(name = "HoaDonHoanTra.getAllHoaDonHoanTra_20", query = "SELECT h FROM HoaDonHoanTra h") 
		
})





public class HoaDonHoanTra implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2956097488696422597L;
	@Id
    private String maHoaDonHoanTra;
    private LocalDateTime ngayHoanTra;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhanVien")
    private NhanVien nhanVien;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoaDon")
    private HoaDon hoaDon;
    @Column(name = "ghiChu", columnDefinition = "nvarchar(255)")
    private String ghiChu;
    private int tinhTrangHoaDon;

    private double tienHoanTra;
    private String lyDoHoanTra;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hoaDonHoanTra")
    private List<ChiTietHoanTra> chiTietHoanTra;

    public HoaDonHoanTra (String ma) {
        super();
        this.maHoaDonHoanTra = ma;
    }


    public HoaDonHoanTra(String maHoaDonHoanTra, LocalDateTime ngayHoan, NhanVien nhanVien, HoaDon hoaDon,
            String ghiChu, int tinhTrangHoaDon, double tienHoanTra) {
        super();
        this.maHoaDonHoanTra = maHoaDonHoanTra;
        this.ngayHoanTra = ngayHoan;
        this.nhanVien = nhanVien;
        this.hoaDon = hoaDon;
        this.ghiChu = ghiChu;
        this.tinhTrangHoaDon = tinhTrangHoaDon;
        this.tienHoanTra = tienHoanTra;
    }

   

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.maHoaDonHoanTra);
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
        final HoaDonHoanTra other = (HoaDonHoanTra) obj;
        return Objects.equals(this.maHoaDonHoanTra, other.maHoaDonHoanTra);
    }

}
