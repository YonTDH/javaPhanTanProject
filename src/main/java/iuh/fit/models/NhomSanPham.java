package iuh.fit.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NhomSanPham")
@Builder
@NamedQueries({
        @NamedQuery(name = "NhomSanPham.getAll", query = "SELECT n FROM NhomSanPham n"),
        @NamedQuery(name = "NhomSanPham.findByTen", query = "SELECT n FROM NhomSanPham n WHERE n.tenNhom = :tenNhom")
})
public class NhomSanPham implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "maNhom")
    private String maNhom;

    @Column(name = "tenNhom", columnDefinition = "nvarchar(255)")
    private String tenNhom;

    @OneToMany(mappedBy = "nhomSanPham", fetch = FetchType.LAZY)
    private Set<SanPham> danhSachSanPham;

    public NhomSanPham(String maNhom) {
        this.maNhom = maNhom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NhomSanPham)) return false;
        NhomSanPham that = (NhomSanPham) o;
        return Objects.equals(maNhom, that.maNhom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maNhom);
    }

    @Override
    public String toString() {
        return "NhomSanPham{" +
                "maNhom='" + maNhom + '\'' +
                ", tenNhom='" + tenNhom + '\'' +
                '}';
    }
}
