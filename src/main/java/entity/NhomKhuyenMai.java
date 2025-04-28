package entity;

import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;


@NamedQueries({ 
		@NamedQuery(name = "NhomKhuyenMai.getAllNhomKM", query = "SELECT n FROM NhomKhuyenMai n"),
		@NamedQuery(name = "deleteNhomKM", query = "DELETE FROM NhomKhuyenMai n WHERE n.khuyenMai = :s") 
		})
public class NhomKhuyenMai  {
    private KhuyenMai khuyenMai;
    private NhomSanPham nhomSanPham;

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public NhomSanPham getNhomSanPham() {
		return nhomSanPham;
	}

	public void setNhomSanPham(NhomSanPham nhomSanPham) {
		this.nhomSanPham = nhomSanPham;
	}
	public NhomKhuyenMai(KhuyenMai khuyenMai, NhomSanPham nhomSanPham) {
		super();
		this.khuyenMai = khuyenMai;
		this.nhomSanPham = nhomSanPham;
	}
	public NhomKhuyenMai() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "NhomKhuyenMai [khuyenMai=" + khuyenMai + ", nhomSanPham=" + nhomSanPham + "]";
	}
    
}
