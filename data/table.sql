
CREATE DATABASE IF NOT EXISTS `nhasachdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `nhasachdb`;

-- Dumping structure for table nhasachdb.calamviec
CREATE TABLE IF NOT EXISTS `calamviec` (
  `maCa` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci NOT NULL,
  `tenCa` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  `thoiGianBatDau` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  `thoiGianKetThuc` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  PRIMARY KEY (`maCa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table nhasachdb.chitiethoadon
CREATE TABLE IF NOT EXISTS `chitiethoadon` (
  `giaTien` double DEFAULT NULL,
  `soLuong` int(11) DEFAULT NULL,
  `thanhTien` double DEFAULT NULL,
  `maHoaDon` varchar(255) NOT NULL,
  `maSanPham` varchar(255) NOT NULL,
  PRIMARY KEY (`maHoaDon`,`maSanPham`),
  KEY `FKjrhk50rw9gnc8yk1epve0uc7s` (`maSanPham`),
  CONSTRAINT `FK48lko1mwj5apm7o0w68jp3ugw` FOREIGN KEY (`maHoaDon`) REFERENCES `hoadon` (`maHoaDon`),
  CONSTRAINT `FKjrhk50rw9gnc8yk1epve0uc7s` FOREIGN KEY (`maSanPham`) REFERENCES `sanpham` (`maSanPham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table nhasachdb.chitiethoadondoi
CREATE TABLE IF NOT EXISTS `chitiethoadondoi` (
  `giaTien` double DEFAULT NULL,
  `soLuong` int(11) DEFAULT NULL,
  `thanhTien` double DEFAULT NULL,
  `HoaDonDoiHang` varchar(255) NOT NULL,
  `maSanPham` varchar(255) NOT NULL,
  PRIMARY KEY (`HoaDonDoiHang`,`maSanPham`),
  KEY `FKtc79cdyrr2rlv0f6dtpbqllsx` (`maSanPham`),
  CONSTRAINT `FKqr8hmuk8kcxge6rsxl93mscmn` FOREIGN KEY (`HoaDonDoiHang`) REFERENCES `hoadondoihang` (`maHoaDonDoiHang`),
  CONSTRAINT `FKtc79cdyrr2rlv0f6dtpbqllsx` FOREIGN KEY (`maSanPham`) REFERENCES `sanpham` (`maSanPham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table nhasachdb.chitiethoantra
CREATE TABLE IF NOT EXISTS `chitiethoantra` (
  `hoaDonHoanTra` varchar(255) NOT NULL,
  `sanPham` varchar(255) NOT NULL,
  `soLuong` int(11) NOT NULL,
  `thanhTien` double NOT NULL,
  PRIMARY KEY (`hoaDonHoanTra`,`sanPham`),
  KEY `FKg78uwxpvyw6jh3ag696pywbq2` (`sanPham`),
  CONSTRAINT `FKg78uwxpvyw6jh3ag696pywbq2` FOREIGN KEY (`sanPham`) REFERENCES `sanpham` (`maSanPham`),
  CONSTRAINT `FKq038ftcyajb3gujranmuqhayb` FOREIGN KEY (`hoaDonHoanTra`) REFERENCES `hoadonhoantra` (`maHoaDonHoanTra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table nhasachdb.hoadon
CREATE TABLE IF NOT EXISTS `hoadon` (
  `maHoaDon` varchar(50) NOT NULL,
  `ngayTao` datetime(6) DEFAULT NULL,
  `soTienGiam` double NOT NULL,
  `thanhTien` double NOT NULL,
  `tongTien` double NOT NULL,
  `maKhachHang` bigint(20) DEFAULT NULL,
  `maKhuyenMai` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  `maNhanVien` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`maHoaDon`),
  KEY `FK9d827nxw1jb7c2n7k6lma13fo` (`maKhachHang`),
  KEY `FK1ingtkkt0wcuy2wooff07a1m` (`maKhuyenMai`),
  KEY `FKlr1g5d8b2338kpln7dlergfjg` (`maNhanVien`),
  CONSTRAINT `FK1ingtkkt0wcuy2wooff07a1m` FOREIGN KEY (`maKhuyenMai`) REFERENCES `khuyenmai` (`maKhuyenMai`),
  CONSTRAINT `FK9d827nxw1jb7c2n7k6lma13fo` FOREIGN KEY (`maKhachHang`) REFERENCES `khachhang` (`id`),
  CONSTRAINT `FKlr1g5d8b2338kpln7dlergfjg` FOREIGN KEY (`maNhanVien`) REFERENCES `nhanvien` (`maNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table nhasachdb.hoadondoihang
CREATE TABLE IF NOT EXISTS `hoadondoihang` (
  `maHoaDonDoiHang` varchar(50) NOT NULL,
  `lyDo` varchar(255) DEFAULT NULL,
  `ngayDoi` datetime(6) DEFAULT NULL,
  `maKhachHang` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`maHoaDonDoiHang`),
  KEY `FK2597efd32jhyrs6xqahcsl40i` (`maKhachHang`),
  CONSTRAINT `FK2597efd32jhyrs6xqahcsl40i` FOREIGN KEY (`maKhachHang`) REFERENCES `khachhang` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table nhasachdb.hoadonhoantra
CREATE TABLE IF NOT EXISTS `hoadonhoantra` (
  `maHoaDonHoanTra` varchar(255) NOT NULL,
  `ghiChu` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  `ngayHoan` datetime(6) DEFAULT NULL,
  `tienHoanTra` double NOT NULL,
  `tinhTrangHoaDon` int(11) NOT NULL,
  `hoaDon` varchar(50) DEFAULT NULL,
  `nhanVien` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`maHoaDonHoanTra`),
  KEY `FKg1n354fk6aorqai73jbvxdmf2` (`hoaDon`),
  KEY `FK8tclti5ovu362k44b1mky4r4t` (`nhanVien`),
  CONSTRAINT `FK8tclti5ovu362k44b1mky4r4t` FOREIGN KEY (`nhanVien`) REFERENCES `nhanvien` (`maNhanVien`),
  CONSTRAINT `FKg1n354fk6aorqai73jbvxdmf2` FOREIGN KEY (`hoaDon`) REFERENCES `hoadon` (`maHoaDon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table nhasachdb.khachhang
CREATE TABLE IF NOT EXISTS `khachhang` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `diaChi` varchar(255) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `maKhachHang` varchar(50) NOT NULL,
  `ngayDangKy` date DEFAULT NULL,
  `nhomKhachHang` enum('VIP','THUONG') DEFAULT NULL,
  `soDienThoai` varchar(15) DEFAULT NULL,
  `tenKhachHang` varchar(100) NOT NULL,
  `trangThai` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ly5ybr9h3ea2yw44cntxn2kpd` (`maKhachHang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table nhasachdb.khuyenmai
CREATE TABLE IF NOT EXISTS `khuyenmai` (
  `maKhuyenMai` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci NOT NULL,
  `ghiChu` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  `giaTriKhuyenMaiToiDa` double DEFAULT NULL,
  `ngayBatDau` datetime(6) DEFAULT NULL,
  `ngayKetThuc` datetime(6) DEFAULT NULL,
  `tenKhuyenMai` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci NOT NULL,
  `tienToiThieu` double DEFAULT NULL,
  `trangThai` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  `tyLeKhuyenMai` double DEFAULT NULL,
  PRIMARY KEY (`maKhuyenMai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table nhasachdb.mausac
CREATE TABLE IF NOT EXISTS `mausac` (
  `maMau` varchar(50) NOT NULL,
  `tenMau` varchar(100) NOT NULL,
  `trangThai` bit(1) NOT NULL,
  PRIMARY KEY (`maMau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table nhasachdb.nhacungcap
CREATE TABLE IF NOT EXISTS `nhacungcap` (
  `maNhaCungCap` varchar(255) NOT NULL,
  `diaChi` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  `soDienThoai` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  `tenNhaCungCap` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  PRIMARY KEY (`maNhaCungCap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table nhasachdb.nhanvien
CREATE TABLE IF NOT EXISTS `nhanvien` (
  `maNhanVien` varchar(50) NOT NULL,
  `chucVu` enum('THUNGAN','QUANLY') DEFAULT NULL,
  `diaChi` varchar(255) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `ngayVaoLam` date DEFAULT NULL,
  `soDienThoai` varchar(15) DEFAULT NULL,
  `tenNhanVien` varchar(100) NOT NULL,
  `trangThai` bit(1) DEFAULT NULL,
  `caLamViec` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  PRIMARY KEY (`maNhanVien`),
  KEY `FK76wyxwkn1pc9mhyt2f8e5iyow` (`caLamViec`),
  CONSTRAINT `FK76wyxwkn1pc9mhyt2f8e5iyow` FOREIGN KEY (`caLamViec`) REFERENCES `calamviec` (`maCa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table nhasachdb.nhomkhuyenmai
CREATE TABLE IF NOT EXISTS `nhomkhuyenmai` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `khuyenMai` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  `nhomSanPham` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKie8yl7ksgqhmgp6metb6vf61q` (`khuyenMai`),
  KEY `FKdxts6k65g3pumm5w4e2by08dj` (`nhomSanPham`),
  CONSTRAINT `FKdxts6k65g3pumm5w4e2by08dj` FOREIGN KEY (`nhomSanPham`) REFERENCES `nhomsanpham` (`maNhom`),
  CONSTRAINT `FKie8yl7ksgqhmgp6metb6vf61q` FOREIGN KEY (`khuyenMai`) REFERENCES `khuyenmai` (`maKhuyenMai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table nhasachdb.nhomsanpham
CREATE TABLE IF NOT EXISTS `nhomsanpham` (
  `maNhom` varchar(255) NOT NULL,
  `tenNhom` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  PRIMARY KEY (`maNhom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table nhasachdb.sanpham
CREATE TABLE IF NOT EXISTS `sanpham` (
  `Discriminator` varchar(31) NOT NULL,
  `maSanPham` varchar(255) NOT NULL,
  `VAT` double NOT NULL,
  `donGiaBan` double NOT NULL,
  `donGiaNhap` double NOT NULL,
  `giamGia` double NOT NULL,
  `moTa` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  `ngayTao` datetime(6) DEFAULT NULL,
  `soLuongTon` int(11) NOT NULL,
  `tenSanPham` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  `tinhTrang` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  `noiSanXuat` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  `namXuatBan` int(11) DEFAULT NULL,
  `nhaXuatBan` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  `soTrang` int(11) DEFAULT NULL,
  `tacGia` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  `nhaCungCap` varchar(255) DEFAULT NULL,
  `nhomSanPham` varchar(255) DEFAULT NULL,
  `mauSac` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`maSanPham`),
  KEY `FKoff0u1ogdrix5kst4xdbmke5j` (`nhaCungCap`),
  KEY `FK6ae7ag4f14mrc9ddmm3a1div6` (`nhomSanPham`),
  KEY `FKmhuc3j9fquhuvvev4vorv9w7f` (`mauSac`),
  CONSTRAINT `FK6ae7ag4f14mrc9ddmm3a1div6` FOREIGN KEY (`nhomSanPham`) REFERENCES `nhomsanpham` (`maNhom`),
  CONSTRAINT `FKmhuc3j9fquhuvvev4vorv9w7f` FOREIGN KEY (`mauSac`) REFERENCES `mausac` (`maMau`),
  CONSTRAINT `FKoff0u1ogdrix5kst4xdbmke5j` FOREIGN KEY (`nhaCungCap`) REFERENCES `nhacungcap` (`maNhaCungCap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
