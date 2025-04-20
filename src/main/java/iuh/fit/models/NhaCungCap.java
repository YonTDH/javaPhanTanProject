package iuh.fit.models;

@Entity
@Table(name = "NhaCungCap")
@NamedQueries({
        @NamedQuery(name = "NhaCungCap.getAll", query = "SELECT n FROM NhaCungCap n"),
        @NamedQuery(name = "NhaCungCap.findByTen", query = "SELECT n FROM NhaCungCap n WHERE n.tenNhaCungCap = :tenNhaCungCap")
})
public class NhaCungCap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "maNhaCungCap")
    private String maNhaCungCap;

    @Column(name = "tenNhaCungCap", columnDefinition = "nvarchar(255)")
    private String tenNhaCungCap;

    @Column(name = "diaChi", columnDefinition = "nvarchar(255)")
    private String diaChi;

    @Column(name = "soDienThoai", columnDefinition = "nvarchar(50)")
    private String soDienThoai;

   @OneToMany(mappedBy = "nhaCungCap", fetch = FetchType.LAZY)
   private Set<SanPham> danhSachSanPham;

    public NhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NhaCungCap)) return false;
        NhaCungCap that = (NhaCungCap) o;
        return Objects.equals(maNhaCungCap, that.maNhaCungCap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maNhaCungCap);
    }

    @Override
    public String toString() {
        return "NhaCungCap{" +
                "maNhaCungCap='" + maNhaCungCap + '\'' +
                ", tenNhaCungCap='" + tenNhaCungCap + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                '}';
    }
}
