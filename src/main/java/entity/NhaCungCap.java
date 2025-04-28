package entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "NhaCungCap")
@NamedNativeQuery(name = "NhaCungCap.getMaNhaCungCapDB", query = "SELECT top 1 maNCC \n" +
        "FROM NhaCungCap\n" +
        "ORDER BY \n" +
        "CONVERT(DATE, SUBSTRING(maNCC, 4, 2) + '/' + SUBSTRING(maNCC, 6, 2) + '/' + SUBSTRING(maNCC, 8, 4), 103) DESC,\n" +
        "CAST(SUBSTRING(maNCC, 13, LEN(maNCC)) AS INT) DESC;")


@NamedQueries({ 
		@NamedQuery(name = "NhaCungCap.getALLNhaCungCap_20", query = "SELECT n FROM NhaCungCap n"),
		@NamedQuery(name = "NhaCungCap.getNCCTheoTen", query = "SELECT n FROM NhaCungCap n WHERE n.tenNCC = :tenNCC"),
		@NamedQuery(name = "NhaCungCap.locNhaCungCap", query = "SELECT c FROM NhaCungCap c WHERE c.maNCC LIKE :duLieuTim OR c.tenNCC LIKE :duLieuTim OR c.diaChiNCC LIKE :duLieuTim OR c.soDienThoai LIKE :duLieuTim OR c.email LIKE :duLieuTim OR c.ghiChu LIKE :duLieuTim")

})

public class NhaCungCap implements Serializable{

	private static final long serialVersionUID = -8830915878779031702L;
	@Id
	private String maNCC;
	@Column(name = "tenNCC", columnDefinition = "nvarchar(255)")
	private String tenNCC;
	@Column(name = "diachiNCC", columnDefinition = "nvarchar(255)")
	private String diaChiNCC;
	@Column(name = "soDienThoai", columnDefinition = "nvarchar(255)")
	private String soDienThoai;
	@Column(name = "email", columnDefinition = "nvarchar(255)")
	private String email;
	@Column(name = "ghiChu", columnDefinition = "nvarchar(255)")
	private String ghiChu;
	
	@OneToMany(mappedBy = "nhaCungCap", fetch = FetchType.LAZY)
	private Set<SanPham> sanPham;

	public NhaCungCap(String maNCC, String tenNCC, String diaChiNCC, String soDienThoai, String email, String ghiChu) {
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.diaChiNCC = diaChiNCC;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.ghiChu = ghiChu;
	}
	
	
	
	
	

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.maNCC);
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
        final NhaCungCap other = (NhaCungCap) obj;
        return Objects.equals(this.maNCC, other.maNCC);
    }




	public NhaCungCap(String maNCC) {
		super();
		this.maNCC = maNCC;
	}






	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChiNCC=" + diaChiNCC + ", soDienThoai="
				+ soDienThoai + ", email=" + email + ", ghiChu=" + ghiChu + ", sanPham=" + sanPham + "]";
	}
	
	
}
