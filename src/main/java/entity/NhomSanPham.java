package entity;

import java.io.Serializable;

import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "NhomSanPham")
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "NhomSanPham.getAllNhomSanPham", query = "SELECT n FROM NhomSanPham n"),
    @NamedQuery(name = "NhomSanPham.getNsptheoTen", query = "SELECT n FROM NhomSanPham n WHERE n.tenNhomSanPham = :ten"),
    @NamedQuery(name = "NhomSanPham.getNspTheoMa", query = "SELECT n FROM NhomSanPham n WHERE n.maNhomSanPham = :ma") 
    })



public class NhomSanPham implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5697854377525315292L;
	@Id
	private String maNhomSanPham;
	@Column(name = "tenNhomSanPham", columnDefinition = "nvarchar(255)")
	private String tenNhomSanPham;
	@OneToMany(mappedBy = "nhomSanPham", fetch = FetchType.LAZY)
	private Set<SanPham> sanPham;
	
	
	public NhomSanPham(String maNhomSanPham) {
		super();
		this.maNhomSanPham = maNhomSanPham;
	}

	public NhomSanPham(String maNhomSanPham, String tenNhomSanPham) {
		this.maNhomSanPham = maNhomSanPham;
		this.tenNhomSanPham = tenNhomSanPham;
	}

	@Override
	public String toString() {
		return maNhomSanPham;
	}
	
	
	
	
}
