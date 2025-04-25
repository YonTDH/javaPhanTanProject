package iuh.fit.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table (name = "taiKhoan")
public class TaiKhoan implements Serializable {
    @Id
    private String taiKhoan;
    private String matKhau;
    private String quyen;
}

