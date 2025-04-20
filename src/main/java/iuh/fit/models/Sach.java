package iuh.fit.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("SACH")
@Table(name = "SanPham")
@ToString(callSuper = true)
@NamedQueries({
        @NamedQuery(name = "Sach.getAlltbSach_20", query = "SELECT s FROM Sach s")
})
public class Sach extends SanPham implements Serializable {

    private static final long serialVersionUID = -227605300086694239L;

    @Column(name = "tacGia", columnDefinition = "nvarchar(255)")
    private String tacGia;

    private int namXuatBan;

    @Column(name = "nhaXuatBan", columnDefinition = "nvarchar(255)")
    private String nhaXuatBan;

    private int soTrang;

    public Sach(String ma) {
        super(ma);
    }

    public Sach(String maSanPham, String tenSanPham, NhomSanPham nhomSanPham, NhaCungCap nhaCungCap, int soLuongTon,
                double donGiaNhap, String moTa, String tinhTrang, double donGiaBan, double vAT, LocalDateTime ngayTao,
                double giamGia, String tacGia, int namXuatBan, String nhaXuatBan, int soTrang) {
        super(maSanPham, tenSanPham, nhomSanPham, nhaCungCap, soLuongTon, donGiaNhap, moTa, tinhTrang, donGiaBan, vAT,
                ngayTao, giamGia);
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.nhaXuatBan = nhaXuatBan;
        this.soTrang = soTrang;
    }

    public Sach(String ma, String tacGia, int namXuatBan, String nhaXuatBan, int soTrang) {
        super(ma);
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.nhaXuatBan = nhaXuatBan;
        this.soTrang = soTrang;
    }
}
