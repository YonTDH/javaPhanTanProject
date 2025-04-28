/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

/**
 *
 * @author DNCO
 */

@NamedQueries({ 
		@NamedQuery(name = "NhomKhuyenMai.getAllNhomKM", query = "SELECT n FROM NhomKhuyenMai n"),
		@NamedQuery(name = "deleteNhomKM", query = "DELETE FROM NhomKhuyenMai n WHERE n.khuyenMai = :s") 
		})

public class NhomKhuyenMai  {
    private KhuyenMai khuyenMai;
    private NhomSanPham nhomSanPham;
	/**
	 * @return the khuyenMai
	 */
	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}
	/**
	 * @param khuyenMai the khuyenMai to set
	 */
	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
	/**
	 * @return the nhomSanPham
	 */
	public NhomSanPham getNhomSanPham() {
		return nhomSanPham;
	}
	/**
	 * @param nhomSanPham the nhomSanPham to set
	 */
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
