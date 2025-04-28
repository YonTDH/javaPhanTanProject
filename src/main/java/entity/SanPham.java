package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "SanPham")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "SanPham.getAllSanPham", query = "SELECT sp FROM SanPham sp")


public abstract class SanPham implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8124213137126012314L;
	@Id
    protected String maSanPham;
	@Column(name = "tenSanPham", columnDefinition = "nvarchar(255)")
	protected String tenSanPham;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhomSanPham")
    protected NhomSanPham nhomSanPham;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhaCungCap")
    protected NhaCungCap nhaCungCap;
    protected int soLuongTon;
    protected double donGiaNhap;
    @Column(name = "moTa", columnDefinition = "nvarchar(255)")
    protected String moTa;
    @Column(name = "tinhTrang", columnDefinition = "nvarchar(255)")
    protected String tinhTrang;
    protected double donGiaBan;
    protected double VAT;
    protected LocalDateTime ngayTao;
    protected double giamGia;
    
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY)
    protected Set<ChiTietHoaDon> chiTietHoaDon;
    
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY)
    protected Set<ChiTietHoanTra> chiTietHoanTra;
    
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY)	
    protected Set<ChiTietHoaDonDoi> chiTietHoaDonDoi;
    


    public SanPham(String ma) {
        super();
        this.maSanPham = ma;
    }

    public SanPham(String maSanPham, String tenSanPham, NhomSanPham nhomSanPham, NhaCungCap nhaCungCap, int soLuongTon,
            double donGiaNhap, String moTa, String tinhTrang, double donGiaBan, double vAT, LocalDateTime ngayTao,
            double giamGia) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.nhomSanPham = nhomSanPham;
        this.nhaCungCap = nhaCungCap;
        this.soLuongTon = soLuongTon;
        this.donGiaNhap = donGiaNhap;
        this.moTa = moTa;
        this.tinhTrang = tinhTrang;
        this.donGiaBan = donGiaBan;
        VAT = vAT;
        this.ngayTao = ngayTao;
        this.giamGia = giamGia;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.maSanPham);
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
        final SanPham other = (SanPham) obj;
        return Objects.equals(this.maSanPham, other.maSanPham);
    }

}
