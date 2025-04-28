package entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "KhachHang")
@NamedQueries({ @NamedQuery(name = "KhachHang.getAllKhachHang", query = "SELECT k FROM KhachHang k"),
		@NamedQuery(name = "KhachHang.getKHTheoMa", query = "SELECT k FROM KhachHang k WHERE k.maKhachHang = :ma"),
		@NamedQuery(name = "KhachHang.getKHTheoSDT", query = "SELECT k FROM KhachHang k WHERE k.soDienThoai = :sdt"),
		@NamedQuery(name = "KhachHang.getAllKhachHang_20", query = "SELECT k FROM KhachHang k"),
		@NamedQuery(name = "KhachHang.timKhachHangTheoThongTin", query = "SELECT kh FROM KhachHang kh WHERE (kh.maKhachHang LIKE :data OR kh.tenKhachHang LIKE :data OR kh.soDienThoai LIKE :data)"),
		@NamedQuery(name = "KhachHang.capNhatNhomKhachHang", query = "SELECT c.khachHang.maKhachHang, SUM(c.tongTien) FROM HoaDon c GROUP BY c.khachHang.maKhachHang"),
		@NamedQuery(name = "KhachHang.locDuLieuKhachHang", query = "SELECT kh FROM KhachHang kh " +
				"WHERE kh.nhomKhachHang != 'KHACHLE' AND " +
				"(kh.maKhachHang LIKE :data OR " +
				"kh.tenKhachHang LIKE :data OR " +
				"kh.soDienThoai LIKE :data)"),
		

})

public class KhachHang implements Serializable {
	private static final long serialVersionUID = 4775110070764260836L;
	@Id
	private String maKhachHang;
	@Column(name = "tenKhachHang", columnDefinition = "nvarchar(255)")
	private String tenKhachHang;
	@Column(name = "soDienThoai", columnDefinition = "nvarchar(255)")
	private String soDienThoai;
	@Enumerated(EnumType.STRING)
	private NhomKhachHang nhomKhachHang;
	private double tongTienMua;
	private int soLuongHoaDon;

	public KhachHang(String maKhachHang, String tenKhachHang, String soDienThoai, NhomKhachHang nhomKhachHang,
			double tongTienMua, int soLuongHoaDon) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.soDienThoai = soDienThoai;
		this.nhomKhachHang = nhomKhachHang;
		this.tongTienMua = tongTienMua;
		this.soLuongHoaDon = soLuongHoaDon;
	}

	public KhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.maKhachHang);
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
		final KhachHang other = (KhachHang) obj;
		return Objects.equals(this.maKhachHang, other.maKhachHang);
	}

}
