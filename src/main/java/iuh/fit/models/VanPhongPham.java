package iuh.fit.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import iuh.fit.models.MauSac;
import iuh.fit.models.NhaCungCap;
import iuh.fit.models.NhomSanPham;
import iuh.fit.models.SanPham;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("VPP")
@Table(name = "SanPham")
@ToString(callSuper = true)
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

    @Column(name = "noiSanXuat", columnDefinition = "nvarchar(255)")
    private String noiSanXuat;

    public VanPhongPham(String ma) {
        super(ma);
    }

    public VanPhongPham(String maSanPham, String tenSanPham, NhomSanPham nhomSanPham, NhaCungCap nhaCungCap,
                        int soLuongTon, double donGiaNhap, String moTa, String tinhTrang, double donGiaBan, double vAT,
                        LocalDateTime ngayTao, double giamGia) {
        super(maSanPham, tenSanPham, nhomSanPham, nhaCungCap, soLuongTon, donGiaNhap, moTa, tinhTrang, donGiaBan, vAT,
                ngayTao, giamGia);
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
