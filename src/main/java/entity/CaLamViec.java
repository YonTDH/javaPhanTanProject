package entity;

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
	private String maCa;
	@Column(name = "tenCa", columnDefinition = "nvarchar(255)")
	private String tenCa;
	private String thoiGianBatDau;
	private String thoiGianKetThuc;
	@OneToMany(mappedBy = "caLamViec", fetch = FetchType.LAZY)
	private List<NhanVien> dsNhanVien;

	public CaLamViec(String maCa) {
		super();
		this.maCa = maCa;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maCa);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaLamViec other = (CaLamViec) obj;
		return Objects.equals(maCa, other.maCa);
	}

        
	
	
}
