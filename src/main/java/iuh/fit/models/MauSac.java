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
@Table(name = "MauSac")
@NamedQueries({
        @NamedQuery(name = "MauSac.getAll", query = "SELECT m FROM MauSac m"),
        @NamedQuery(name = "MauSac.findByTen", query = "SELECT m FROM MauSac m WHERE m.tenMauSac = :tenMauSac")
})
public class MauSac implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "maMauSac")
    private String maMauSac;

    @Column(name = "tenMauSac", columnDefinition = "nvarchar(255)")
    private String tenMauSac;

    @OneToMany(mappedBy = "mauSac", fetch = FetchType.LAZY)
    private Set<VanPhongPham > danhSachVanPhongPham;

    public MauSac(String maMauSac) {
        this.maMauSac = maMauSac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MauSac)) return false;
        MauSac that = (MauSac) o;
        return Objects.equals(maMauSac, that.maMauSac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maMauSac);
    }

    @Override
    public String toString() {
        return "MauSac{" +
                "maMauSac='" + maMauSac + '\'' +
                ", tenMauSac='" + tenMauSac + '\'' +
                '}';
    }
}

