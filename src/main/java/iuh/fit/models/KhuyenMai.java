package iuh.fit.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "KhuyenMai")
@Builder
@NamedQueries({
        @NamedQuery(name = "KhuyenMai.getAlltbKM", query = "SELECT k FROM KhuyenMai k"),
        @NamedQuery(name = "KhuyenMai.getAlltbKMTheoDangChay", query = "SELECT k FROM KhuyenMai k WHERE k.trangThai = 'Đang hoạt động'"),
        @NamedQuery(name = "KhuyenMai.getKhuyenMaiByMaKhuyenMai", query = "SELECT k FROM KhuyenMai k WHERE k.maKhuyenMai = :maKhuyenMai")
})
public class KhuyenMai implements Serializable {

    private static final long serialVersionUID = 9148093557088718028L;

    @Id
    @Column(name = "maKhuyenMai", columnDefinition = "nvarchar(50)")
    private String maKhuyenMai;

    @Column(name = "tenKhuyenMai", columnDefinition = "nvarchar(255)", nullable = false)
    private String tenKhuyenMai;

    @Column(name = "ghiChu", columnDefinition = "nvarchar(255)")
    private String ghiChu;

    @Column(name = "trangThai", columnDefinition = "nvarchar(255)")
    private String trangThai;

    @Column(name = "tyLeKhuyenMai")
    private double tyLeKhuyenMai;

    @Column(name = "tienToiThieu")
    private double tienToiThieu;

    @Column(name = "giaTriKhuyenMaiToiDa")
    private double giaTriKhuyenMaiToiDa;

    @Column(name = "ngayBatDau")
    private LocalDateTime ngayBatDau;

    @Column(name = "ngayKetThuc")
    private LocalDateTime ngayKetThuc;


    public KhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }
}

