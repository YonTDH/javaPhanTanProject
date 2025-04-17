// ChiTietHoaDonDoiId.java
package iuh.fit.models;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ChiTietHoaDonDoiId implements Serializable {

    private String maHoaDonDoiHang;
    private String maSanPham;
}
