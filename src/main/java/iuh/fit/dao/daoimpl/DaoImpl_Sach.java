package iuh.fit.dao.daoimpl;

import iuh.fit.dao.DAO_Sach;
import iuh.fit.models.NhaCungCap;
import iuh.fit.models.NhomSanPham;
import iuh.fit.models.Sach;
import iuh.fit.util.AppUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.List;

public class DaoImpl_Sach implements DAO_Sach {
    private EntityManager em;

    public DaoImpl_Sach() {
        em = AppUtil.getEntityManager();
    }

    public DaoImpl_Sach(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Sach> getAlltbSach_20() throws RemoteException {
        try {
            return em.createQuery("SELECT s FROM Sach s ORDER BY s.maSanPham DESC", Sach.class)
                    .setMaxResults(20)
                    .getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy 20 sách mới nhất.", e);
        }
    }

    @Override
    public List<Sach> getAlltbSach() throws RemoteException {
        try {
            return em.createQuery("FROM Sach", Sach.class).getResultList();
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy tất cả sách.", e);
        }
    }

    @Override
    public boolean createSach(Sach s) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(s);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi tạo sách.", e);
        }
    }

    @Override
    public void importExcel(String fileName, String sheetName) throws RemoteException {
        DaoImpl_NhomSanPham daoImplNhomSanPham = new DaoImpl_NhomSanPham();
        DaoImpl_NhaCungCap daoImplNhaCungCap = new DaoImpl_NhaCungCap();

        try (FileInputStream fis = new FileInputStream(fileName);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RemoteException("Không tìm thấy sheet: " + sheetName);
            }

            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next(); // Bỏ qua dòng tiêu đề
            }
//Mã sản phẩm|Tên sản phẩm|Mã nhóm sản phẩm|Mã nhà cung cấp|Số lượng tồn|Đơn giá nhập|Mô tả|Tình trạng|Đơn giá bán|VAT|Ngày tạo|Giảm giá|Tác giả|Năm xuất bản|Nhà xuất bản|Số trang
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                String maSanPham = getCellString(row.getCell(0));
                String tenSanPham = getCellString(row.getCell(1));
                String maNhom = getCellString(row.getCell(2));
                String maNhaCC = getCellString(row.getCell(3));
                int soLuongTon = (int) row.getCell(4).getNumericCellValue();
                double donGiaNhap = row.getCell(5).getNumericCellValue();
                String moTa = getCellString(row.getCell(6));
                String tinhTrang = getCellString(row.getCell(7));
                double donGiaBan = row.getCell(8).getNumericCellValue();
                double vat = row.getCell(9).getNumericCellValue();
                LocalDateTime ngayTao = row.getCell(10).getLocalDateTimeCellValue();;
                double giamGia = row.getCell(11).getNumericCellValue();
                String tacGia = getCellString(row.getCell(12));
                int namXuatBan = (int) row.getCell(13).getNumericCellValue();
                String nhaXuatBan = getCellString(row.getCell(14));
                int soTrang = (int) row.getCell(15).getNumericCellValue();

                // Lấy đối tượng NhomSanPham và NhaCungCap từ CSDL
                NhomSanPham nhomSanPham = daoImplNhomSanPham.getNspTheoMa(maNhom);
                NhaCungCap nhaCungCap = daoImplNhaCungCap.getNCCTheoMa(maNhaCC);

                Sach sach = new Sach(maSanPham, tenSanPham, nhomSanPham, nhaCungCap,
                        soLuongTon, donGiaNhap, moTa, tinhTrang, donGiaBan, vat,
                        ngayTao, giamGia, tacGia, namXuatBan, nhaXuatBan, soTrang);
                // Lưu vào database
                createSach(sach);
            }

        } catch (FileNotFoundException e) {
            throw new RemoteException("Không tìm thấy file: " + fileName, e);
        } catch (IOException e) {
            throw new RemoteException("Lỗi khi đọc file Excel: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RemoteException("Lỗi không xác định: " + e.getMessage(), e);
        }
    }


    // Helper method để đọc ô kiểu String
    private String getCellString(Cell cell) {
        if (cell == null) return "";
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }

    @Override
    public Sach getSachtheoMa(String ma) throws RemoteException {
        try {
            return em.find(Sach.class, ma);
        } catch (Exception e) {
            throw new RemoteException("Lỗi khi lấy sách theo mã.", e);
        }
    }

    @Override
    public boolean updateSach(Sach s) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(s);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            throw new RemoteException("Lỗi khi cập nhật sách.", e);
        }
    }
}
