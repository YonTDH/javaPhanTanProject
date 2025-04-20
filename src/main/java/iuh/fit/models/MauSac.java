package iuh.fit.models;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "MauSac")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MauSac {

    @Id
    @Column(name = "maMau", nullable = false, unique = true, length = 50)
    private String maMau;

    @Column(name = "tenMau", nullable = false, length = 100)
    private String tenMau;

    @Column(name = "trangThai", nullable = false)
    private Boolean trangThai; // true - hiển thị, false - ẩn

}
