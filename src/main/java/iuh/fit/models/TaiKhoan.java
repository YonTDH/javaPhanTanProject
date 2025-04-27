package iuh.fit.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TaiKhoan")
@NamedQueries({
        @NamedQuery(name = "TaiKhoan.phanQuyen", query = "SELECT tk.tenDangNhap FROM TaiKhoan tk WHERE tk.email = :email"),
        @NamedQuery(name = "TaiKhoan.getTenNguoiDung", query = "SELECT nv.hoTenNV FROM NhanVien nv WHERE nv.email = :email"),
        @NamedQuery(name = "TaiKhoan.doiMatKhau", query = "SELECT tk FROM TaiKhoan tk WHERE tk.email = :email"),
        @NamedQuery(name = "TaiKhoan.getAllTaiKhoan", query = "SELECT tk FROM TaiKhoan tk"),
        @NamedQuery(name = "TaiKhoan.getTaiKhoanByEmail", query = "SELECT t FROM TaiKhoan t WHERE t.email = :email"),
})
public class TaiKhoan implements Serializable {

    private static final long serialVersionUID = 2374252324184671506L;

    @Id
    @Column(name = "tenDangNhap", columnDefinition = "nvarchar(50)")
    private String tenDangNhap;

    @Column(name = "matKhau", columnDefinition = "nvarchar(255)", nullable = false)
    private String matKhau;

    @Column(name = "email", columnDefinition = "nvarchar(255)", nullable = false, unique = true)
    private String email;

     @OneToOne(mappedBy = "taiKhoan")
     private NhanVien nhanVien;

    public static TaiKhoan ChuyenString(String text) {
        return new TaiKhoan(text);
    }

    public TaiKhoan(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }
}

