package iuh.fit.models;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class TaiKhoan implements Serializable {

    private static final long serialVersionUID = 2374252324184671506L;

    @Id
    @Column(name = "tenDangNhap", columnDefinition = "nvarchar(50)")
    private String tenDangNhap;

    @Column(name = "matKhau", columnDefinition = "nvarchar(255)", nullable = false)
    private String matKhau;

    @Column(name = "email", columnDefinition = "nvarchar(255)", nullable = false, unique = true)
    private String email;

    @Column(name = "chucVu")
    private ChucVu chucVu;

    @OneToOne(mappedBy = "taiKhoan")
    private NhanVien nhanVien;
}

