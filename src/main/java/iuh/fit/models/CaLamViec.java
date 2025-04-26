package iuh.fit.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Entity
@Table(name = "CaLamViec")
public class CaLamViec implements Serializable {

    private static final long serialVersionUID = -7220260694252736743L;

    @Id
    @Column(name = "maCa", columnDefinition = "nvarchar(50)")
    private String maCa;

    @Column(name = "tenCa", columnDefinition = "nvarchar(255)")
    private String tenCa;

    @Column(name = "thoiGianBatDau", columnDefinition = "nvarchar(50)")
    private String thoiGianBatDau;

    @Column(name = "thoiGianKetThuc", columnDefinition = "nvarchar(50)")
    private String thoiGianKetThuc;

    @OneToMany(mappedBy = "caLamViec", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<NhanVien> dsNhanVien;

    public CaLamViec(String maCa) {
        this.maCa = maCa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maCa);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CaLamViec other = (CaLamViec) obj;
        return Objects.equals(maCa, other.maCa);
    }
}
