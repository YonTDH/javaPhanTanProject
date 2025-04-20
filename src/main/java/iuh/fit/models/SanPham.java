package iuh.fit.models;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Discriminator")
@Table(name = "SanPham")
@NamedQuery(name = "SanPham.getAllSanPham", query = "SELECT sp FROM SanPham sp")
public abstract class SanPham implements Serializable {

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

    public SanPham(String maSanPham, String tenSanPham, NhomSanPham nhomSanPham, NhaCungCap nhaCungCap,
                   int soLuongTon, double donGiaNhap, String moTa, String tinhTrang, double donGiaBan, double vAT,
                   LocalDateTime ngayTao, double giamGia) {
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
        return Objects.hash(maSanPham);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof SanPham))
            return false;
        SanPham other = (SanPham) obj;
        return Objects.equals(maSanPham, other.maSanPham);
    }
}
