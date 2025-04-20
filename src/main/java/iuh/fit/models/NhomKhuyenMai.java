package iuh.fit.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "NhomKhuyenMai")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@NamedQueries({
        @NamedQuery(name = "NhomKhuyenMai.getAllNhomKM", query = "SELECT n FROM NhomKhuyenMai n"),
        @NamedQuery(name = "deleteNhomKM", query = "DELETE FROM NhomKhuyenMai n WHERE n.khuyenMai = :s")
})
public class NhomKhuyenMai implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khuyenMai")
    private KhuyenMai khuyenMai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhomSanPham")
    private NhomSanPham nhomSanPham;
}

