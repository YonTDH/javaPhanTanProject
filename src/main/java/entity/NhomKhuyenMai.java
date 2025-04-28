package entity;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Getter
@Setter
@NamedQueries({ 
		@NamedQuery(name = "NhomKhuyenMai.getAllNhomKM", query = "SELECT n FROM NhomKhuyenMai n"),
		@NamedQuery(name = "deleteNhomKM", query = "DELETE FROM NhomKhuyenMai n WHERE n.khuyenMai = :s")
		})
public class NhomKhuyenMai  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "makhuyenmai")
	private KhuyenMai khuyenMai;

	@ManyToOne
	@JoinColumn(name = "manhomsanpham")
	private NhomSanPham nhomSanPham;


}
