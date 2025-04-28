package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "NhanVien")
@NamedQueries({
    @NamedQuery(name = "NhanVien.getAllNhanVien", query = "SELECT n FROM NhanVien n"),
    @NamedQuery(name = "NhanVien.getAllNhanVien_20", query = "SELECT n FROM NhanVien n"),
    @NamedQuery(name = "NhanVien.locNhanVien", query = "SELECT kh FROM NhanVien kh " +
			"WHERE " +
			"(kh.maNhanVien LIKE :data OR " +
			"kh.hoTenNV LIKE :data OR " +
			"kh.soDienThoai LIKE :data)"),
    @NamedQuery(name = "NhanVien.locNhanVienTheoChucVu", query = "SELECT n FROM NhanVien n WHERE n.chucVu = :value"),
    @NamedQuery(name = "NhanVien.getNhanVienTheoCa", query = "SELECT n FROM NhanVien n WHERE n.caLamViec = :value"),
    })

@NamedNativeQueries({
    @NamedNativeQuery(name = "NhanVien.getAllNhanVienTheoCa", query = "SELECT * FROM NhanVien WHERE caLamViec = ?")
    })

public class NhanVien implements Serializable {
	private static final long serialVersionUID = 5549263630083775424L;
	@Id
	@Column(name = "maNhanVien", columnDefinition = "nvarchar(255)")
	private String maNhanVien;
	@Column(name = "hoTenNV", columnDefinition = "nvarchar(255)")
	private String hoTenNV;
	@Column(name = "ngaySinh")
	private LocalDate ngaySinh;
	@Column(name = "soDienThoaiNV", columnDefinition = "nvarchar(255)")
	private String soDienThoai;
	@Column(name = "gioiTinh", columnDefinition = "nvarchar(255)")
	private String gioiTinh;
	@Column(name = "email", columnDefinition = "nvarchar(255)")
	private String email;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "taiKhoan")
	private TaiKhoan taiKhoan;
	@Column(name = "tinhTrangLamViec")
	private int tinhTrangLamViec;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caLamViec")
	private CaLamViec caLamViec;
	@Enumerated(EnumType.STRING)
	@Column(name = "chucVu")
	private ChucVu chucVu;

	
	public NhanVien(String ma) {
            super();
            this.maNhanVien = ma;
	}
	@Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.maNhanVien);
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
        final NhanVien other = (NhanVien) obj;
        return Objects.equals(this.maNhanVien, other.maNhanVien);
    }
    
    public NhanVien(String maNhanVien, String hoTenNV, LocalDate ngaySinh, String soDienThoai, String gioiTinh,
			String email, TaiKhoan taiKhoanStr, int tinhTrangLamViec, String caLamViecStr, ChucVu chucVu) {
		this.maNhanVien = maNhanVien;
		this.hoTenNV = hoTenNV;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
		this.gioiTinh = gioiTinh;
		this.email = email;
        this.taiKhoan = taiKhoanStr;
		this.tinhTrangLamViec = tinhTrangLamViec;
		this.caLamViec =  new CaLamViec(caLamViecStr);
		this.chucVu = chucVu;
	}
        public NhanVien(String maNhanVien, String hoTenNV, LocalDate ngaySinh, String soDienThoai, String gioiTinh,
			String email, int tinhTrangLamViec, String caLamViecStr, ChucVu chucVu) {
		this.maNhanVien = maNhanVien;
		this.hoTenNV = hoTenNV;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.tinhTrangLamViec = tinhTrangLamViec;
		this.caLamViec =  new CaLamViec(caLamViecStr);
		this.chucVu = chucVu;
	}
	
	
}
