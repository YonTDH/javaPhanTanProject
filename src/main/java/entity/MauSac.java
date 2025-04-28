package entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MauSac")
@NamedQueries({ 
		@NamedQuery(name = "MauSac.getAlltbMauSac", query = "SELECT m FROM MauSac m"),
		@NamedQuery(name = "MauSac.getMauSactheoTen", query = "SELECT m FROM MauSac m WHERE m.tenMau = :ten"),
		
})


public class MauSac implements Serializable {

	private static final long serialVersionUID = 8915500877956954406L;
	@Id
	private String maMau;
	@Column(name = "tenMau", columnDefinition = "nvarchar(255)")
	private String tenMau;
	
	public MauSac(String maMau, String tenMau) {
		super();
		this.maMau = maMau;
		this.tenMau = tenMau;
	}
	public MauSac(String maMau) {
		super();
		this.maMau = maMau;
	}

	@Override
	public String toString() {
		return maMau;
	}

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.maMau);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MauSac other = (MauSac) obj;
        return Objects.equals(this.maMau, other.maMau);
    }
	
	
}
