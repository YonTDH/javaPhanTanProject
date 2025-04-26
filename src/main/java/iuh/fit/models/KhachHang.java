package iuh.fit.models;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "KhachHang")
@NamedQueries({
        @NamedQuery(name = "KhachHang.getAll", query = "SELECT kh FROM KhachHang kh"),
        @NamedQuery(name = "KhachHang.getByEmail", query = "SELECT kh FROM KhachHang kh WHERE kh.email = :email")
})
public class KhachHang implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String maKhachHang;

    @Column(name = "hoTenKH", columnDefinition = "nvarchar(255)")
    private String hoTenKH;

    private LocalDate ngaySinh;

    @Column(name = "soDienThoai", columnDefinition = "nvarchar(20)")
    private String soDienThoai;

    @Column(name = "diaChi", columnDefinition = "nvarchar(255)")
    private String diaChi;

    @Column(name = "email", columnDefinition = "nvarchar(255)", unique = true)
    private String email;

    @Column(name = "gioiTinh", columnDefinition = "nvarchar(10)")
    private String gioiTinh;

    public KhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "nhomKhachHang", columnDefinition = "nvarchar(50)")
    private NhomKhachHang nhomKhachHang;

}
