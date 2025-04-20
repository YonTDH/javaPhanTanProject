package iuh.fit.models;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class ChiTietHoaDonPK implements Serializable {
    private String maHoaDon;
    private String maSanPham;
}
