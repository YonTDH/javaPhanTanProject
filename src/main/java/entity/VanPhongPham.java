package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "VanPhongPham")
@NamedQueries({ 
		@NamedQuery(name = "VanPhongPham.getAllVanPhongPham", query = "SELECT v FROM VanPhongPham v"),
		@NamedQuery(name = "VanPhongPham.getAllVanPhongPham_20", query = "SELECT v FROM VanPhongPham v"),
		@NamedQuery(name = "VanPhongPham.getVpptheoTen", query = "SELECT v FROM VanPhongPham v WHERE v.tenSanPham = :ten"),
		@NamedQuery(name = "VanPhongPham.getVPPtheoMa", query = "SELECT v FROM VanPhongPham v WHERE v.maSanPham = :ma")
	})



public class VanPhongPham extends SanPham implements Serializable {

	private static final long serialVersionUID = -2934180625419100087L;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mauSac")
    private MauSac mauSac;

	private String chatLieu;

	private String kichThuoc;

	@Column(name = "noiSanXuat", columnDefinition = "nvarchar(255)")
    private String noiSanXuat;


    public VanPhongPham(String ma) {
        super(ma);
    }

    public VanPhongPham(String maSanPham, String tenSanPham, NhomSanPham nhomSanPham, NhaCungCap nhaCungCap,
            int soLuongTon, double donGiaNhap, String moTa, String tinhTrang, double donGiaBan, double vAT,
            LocalDateTime ngayTao, double giamGia) {
        super(maSanPham, tenSanPham, nhomSanPham, nhaCungCap, soLuongTon, donGiaNhap, moTa, tinhTrang, donGiaBan, vAT, ngayTao,
                giamGia);
        // TODO Auto-generated constructor stub
    }

    public VanPhongPham(String maSanPham, String tenSanPham, NhomSanPham nhomSanPham, NhaCungCap nhaCungCap,
            int soLuongTon, double donGiaNhap, String moTa, String tinhTrang, double donGiaBan, double vAT,
            LocalDateTime ngayTao, double giamGia, MauSac mauSac, String noiSanXuat) {
        super(maSanPham, tenSanPham, nhomSanPham, nhaCungCap, soLuongTon, donGiaNhap, moTa, tinhTrang, donGiaBan, vAT,
                ngayTao, giamGia);
        this.mauSac = mauSac;
        this.noiSanXuat = noiSanXuat;
    }

	public VanPhongPham(String ma, MauSac mauSac, String noiSanXuat) {
		super(ma);
		this.mauSac = mauSac;
		this.noiSanXuat = noiSanXuat;
	}


    
    


}
