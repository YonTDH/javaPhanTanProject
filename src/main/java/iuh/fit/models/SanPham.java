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
    protected double donGiaBan;

    @Column(name = "moTa", columnDefinition = "nvarchar(255)")
    protected String moTa;

    @Column(name = "tinhTrang", columnDefinition = "nvarchar(255)")
    protected String tinhTrang;


    @OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY)
    protected Set<ChiTietHoaDon> chiTietHoaDon;

    @OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY)
    protected Set<ChiTietHoanTra> chiTietHoanTra;

    @OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY)
    protected Set<ChiTietHoaDonDoi> chiTietHoaDonDoi;

    @ManyToOne
    @JoinColumn(name = "danhSachSanPham",  insertable = false, updatable = false)
    private KhuyenMai khuyenMai;

}
