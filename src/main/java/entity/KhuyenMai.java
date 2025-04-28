package entity;

import java.io.Serializable;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
@Table(name = "KhuyenMai")
@NamedQueries({ 
		@NamedQuery(name = "KhuyenMai.getAlltbKM", query = "SELECT k FROM KhuyenMai k"),
		@NamedQuery(name = "KhuyenMai.getAlltbKMTheoDangChay", query = "SELECT k FROM KhuyenMai k WHERE k.trangThai = 'Đang hoạt động'"),
		@NamedQuery(name = "KhuyenMai.getKhuyenMaiByMaKhuyenMai", query = "SELECT k FROM KhuyenMai k WHERE k.maKhuyenMai = :maKhuyenMai") 
		})


public class KhuyenMai implements Serializable {

	private static final long serialVersionUID = 9148093557088718028L;
	@Id
    private String maKhuyenMai;
	@Column(name = "tenKhuyenMai", columnDefinition = "nvarchar(255)")
    private String tenKhuyenMai;
	@Column(name = "ghiChu", columnDefinition = "nvarchar(255)")
    private String ghiChu;
	@Column(name = "trangThai", columnDefinition = "nvarchar(255)")
    private String trangThai;
    private double tyLeKhuyenMai;
    private double tienToiThieu;
    private double giaTriKhuyenMaiToiDa;   
    private LocalDateTime ngayBatDau;
    private LocalDateTime ngayKetThuc;
    

	
	public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, String ghiChu, String trangThai, double tyLeKhuyenMai,
			double tienToiThieu, double giaTriKhuyenMaiToiDa, LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc) throws Exception{
		super();
		this.maKhuyenMai = maKhuyenMai;
		this.tenKhuyenMai = tenKhuyenMai;
		this.ghiChu = ghiChu;
		this.trangThai = trangThai;
		this.tyLeKhuyenMai = tyLeKhuyenMai;
		this.tienToiThieu = tienToiThieu;
		this.giaTriKhuyenMaiToiDa = giaTriKhuyenMaiToiDa;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
	}
	
    public KhuyenMai(String maKhuyeMai) {
		super();
		this.maKhuyenMai = maKhuyeMai;
	}
	
 
    
}
