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
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "NhomSanPham")
public class NhomSanPham implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "maNhom")
    private String maNhom;

    @Column(name = "tenNhom", columnDefinition = "nvarchar(255)")
    private String tenNhom;

    @OneToMany(mappedBy = "nhomSanPham", fetch = FetchType.LAZY)
    private Set<SanPham> danhSachSanPham;
}
