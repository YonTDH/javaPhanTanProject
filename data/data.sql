-- 1. Chèn dữ liệu cho các bảng không có khóa ngoại trước
-- Chèn dữ liệu vào bảng calamviec
INSERT INTO `calamviec` (`maCa`, `tenCa`, `thoiGianBatDau`, `thoiGianKetThuc`) VALUES ('CA03', 'Tối', '16:00', '21:00');

-- Chèn dữ liệu vào bảng mausac
INSERT INTO `mausac` (`maMau`, `tenMau`, `trangThai`) VALUES ('YELLOW', 'Vàng', 1);
INSERT INTO `mausac` (`maMau`, `tenMau`, `trangThai`) VALUES ('BLACK', 'Đen', 1);
INSERT INTO `mausac` (`maMau`, `tenMau`, `trangThai`) VALUES ('COLORS', 'Nhiều màu', 1);

-- Chèn dữ liệu vào bảng nhacungcap
INSERT INTO `nhacungcap` (`maNhaCungCap`, `diaChi`, `soDienThoai`, `tenNhaCungCap`) VALUES ('NCC24102023-000002', 'Tầng 3, Dream Home Center, Thanh Xuân, Hà Nội', '0932329959', 'Alpha Books');
INSERT INTO `nhacungcap` (`maNhaCungCap`, `diaChi`, `soDienThoai`, `tenNhaCungCap`) VALUES ('NCC24102023-000003', 'Số 10 Mai Chí Thọ, Thủ Thiêm, Thủ Đức, TP.HCM', '02837505555', 'Thiên Long Hoàn Cầu');
INSERT INTO `nhacungcap` (`maNhaCungCap`, `diaChi`, `soDienThoai`, `tenNhaCungCap`) VALUES ('NCC24102023-000004', '29 DD11, P. TÂN HƯNG THUẬN, Q.12, TPHCM.', '0394334199', 'Deli');
INSERT INTO `nhacungcap` (`maNhaCungCap`, `diaChi`, `soDienThoai`, `tenNhaCungCap`) VALUES ('NCC23102023-000005', '120 Hai Bà Trưng, Quận 1, TP.HCM', '0903123456', 'Văn Phòng Phẩm Việt');

-- Chèn dữ liệu vào bảng nhomsanpham
INSERT INTO `nhomsanpham` (`maNhom`, `tenNhom`) VALUES ('NSP001', 'Sách');
INSERT INTO `nhomsanpham` (`maNhom`, `tenNhom`) VALUES ('NSP002', 'Dụng Cụ Học Sinh');
INSERT INTO `nhomsanpham` (`maNhom`, `tenNhom`) VALUES ('NSP003', 'Văn Phòng Phẩm');
INSERT INTO `nhomsanpham` (`maNhom`, `tenNhom`) VALUES ('NSP004', 'SGK');
INSERT INTO `nhomsanpham` (`maNhom`, `tenNhom`) VALUES ('NSP005', 'Truyện');

-- Chèn dữ liệu vào bảng khuyenmai
INSERT INTO `khuyenmai` (`maKhuyenMai`, `ghiChu`, `giaTriKhuyenMaiToiDa`, `ngayBatDau`, `ngayKetThuc`, `tenKhuyenMai`, `tienToiThieu`, `trangThai`, `tyLeKhuyenMai`) VALUES ('KM06122023-000001', 'Giam gia tet', 100000, '2023-12-06 22:04:22.880', '2025-12-26 22:04:24.330', 'Sales', 0, 'Đang hoạt động', 0.5);
INSERT INTO `khuyenmai` (`maKhuyenMai`, `ghiChu`, `giaTriKhuyenMaiToiDa`, `ngayBatDau`, `ngayKetThuc`, `tenKhuyenMai`, `tienToiThieu`, `trangThai`, `tyLeKhuyenMai`) VALUES ('RFNKOAWBNF', 'Noel', 20000, '2023-12-13 19:37:39.156', '2025-12-27 19:37:40.523', 'Sale Noel', 0, 'Đang hoạt động', 30);

-- 2. Chèn dữ liệu vào bảng có quan hệ phụ thuộc đơn
-- Chèn dữ liệu vào bảng nhanvien (phụ thuộc calamviec)
INSERT INTO `nhanvien` (`maNhanVien`, `chucVu`, `diaChi`, `email`, `ngayVaoLam`, `soDienThoai`, `tenNhanVien`, `trangThai`, `caLamViec`) VALUES ('TN23102023-000011', 'THUNGAN', NULL, 'phuongvo@gmail.com', '2023-10-23', '0965251364', 'Võ Thị Phương', 1, 'CA03');
INSERT INTO `nhanvien` (`maNhanVien`, `chucVu`, `diaChi`, `email`, `ngayVaoLam`, `soDienThoai`, `tenNhanVien`, `trangThai`, `caLamViec`) VALUES ('QL23102023-000007', 'QUANLY', NULL, 'quanly@gmail.com', '2023-10-23', '0908123456', 'Nguyễn Văn Quản Lý', 1, 'CA03');

-- Chèn dữ liệu vào bảng khachhang
INSERT INTO `khachhang` (`id`, `diaChi`, `email`, `maKhachHang`, `ngayDangKy`, `nhomKhachHang`, `soDienThoai`, `tenKhachHang`, `trangThai`) VALUES (1, NULL, NULL, 'KH05092021-000040', '2021-09-05', 'THUONG', '0354654677', 'Nguyễn Huyền Trang', 1);
INSERT INTO `khachhang` (`id`, `diaChi`, `email`, `maKhachHang`, `ngayDangKy`, `nhomKhachHang`, `soDienThoai`, `tenKhachHang`, `trangThai`) VALUES (2, NULL, NULL, 'KH25102023-000002', '2023-10-25', 'THUONG', '0804076819', 'Huberto MacGillacolm', 1);
INSERT INTO `khachhang` (`id`, `diaChi`, `email`, `maKhachHang`, `ngayDangKy`, `nhomKhachHang`, `soDienThoai`, `tenKhachHang`, `trangThai`) VALUES (3, NULL, NULL, 'KH30102021-000034', '2021-10-30', 'THUONG', '0364894351', 'Võ Tấn Lộc', 1);
INSERT INTO `khachhang` (`id`, `diaChi`, `email`, `maKhachHang`, `ngayDangKy`, `nhomKhachHang`, `soDienThoai`, `tenKhachHang`, `trangThai`) VALUES (4, NULL, NULL, 'KH30122021-000031', '2021-12-30', 'THUONG', '0568438718', 'Chu Ðoan Trang', 1);

-- 3. Chèn dữ liệu vào bảng sanpham (phụ thuộc nhacungcap, nhomsanpham, mausac)
-- Chèn dữ liệu vào bảng sanpham (sách)
INSERT INTO `sanpham` (`Discriminator`, `maSanPham`, `VAT`, `donGiaBan`, `donGiaNhap`, `giamGia`, `moTa`, `ngayTao`, `soLuongTon`, `tenSanPham`, `tinhTrang`, `tacGia`, `namXuatBan`, `nhaXuatBan`, `soTrang`, `nhaCungCap`, `nhomSanPham`, `mauSac`)
VALUES ('Sach', 'S22042024-000001', 0.08, 7.5, 5, 0, 'Sách truyện thiếu nhi', '2024-04-22 15:08:23.123', 90, 'Truyện Doreamon', 'Còn hàng', 'Fujiko F. Fujio', 2020, 'Kim Đồng', 100, 'NCC24102023-000002', 'NSP005', NULL);

INSERT INTO `sanpham` (`Discriminator`, `maSanPham`, `VAT`, `donGiaBan`, `donGiaNhap`, `giamGia`, `moTa`, `ngayTao`, `soLuongTon`, `tenSanPham`, `tinhTrang`, `tacGia`, `namXuatBan`, `nhaXuatBan`, `soTrang`, `nhaCungCap`, `nhomSanPham`, `mauSac`)
VALUES ('Sach', 'S22042024-000002', 0.08, 1.5, 1, 0, 'Sách tham khảo', '2024-04-22 15:08:23.123', 96, 'Sách tham khảo Toán', 'Còn hàng', 'Nhiều tác giả', 2021, 'Giáo Dục', 200, 'NCC24102023-000002', 'NSP001', NULL);

INSERT INTO `sanpham` (`Discriminator`, `maSanPham`, `VAT`, `donGiaBan`, `donGiaNhap`, `giamGia`, `moTa`, `ngayTao`, `soLuongTon`, `tenSanPham`, `tinhTrang`, `tacGia`, `namXuatBan`, `nhaXuatBan`, `soTrang`, `nhaCungCap`, `nhomSanPham`, `mauSac`)
VALUES ('Sach', 'S23042024-000002', 0.08, 25000, 15000, 0, 'Sách truyện tranh', '2024-04-23 10:00:00.000', 100, 'Thám tử lừng danh Conan', 'Còn hàng', 'BLACK', 2000, 'Kim đồng', 40, 'NCC24102023-000002', 'NSP005', NULL);

INSERT INTO `sanpham` (`Discriminator`, `maSanPham`, `VAT`, `donGiaBan`, `donGiaNhap`, `giamGia`, `moTa`, `ngayTao`, `soLuongTon`, `tenSanPham`, `tinhTrang`, `tacGia`, `namXuatBan`, `nhaXuatBan`, `soTrang`, `nhaCungCap`, `nhomSanPham`, `mauSac`)
VALUES ('Sach', 'S23042024-000003', 0.08, 30000, 20000, 0, 'Sách truyện tranh', '2024-04-23 10:05:00.000', 80, 'Dragon Ball', 'Còn hàng', 'BLACK', 2002, 'Kim đồng', 60, 'NCC24102023-000002', 'NSP005', NULL);

-- Chèn dữ liệu vào bảng sanpham (văn phòng phẩm)
INSERT INTO `sanpham` (`Discriminator`, `maSanPham`, `VAT`, `donGiaBan`, `donGiaNhap`, `giamGia`, `moTa`, `ngayTao`, `soLuongTon`, `tenSanPham`, `tinhTrang`, `noiSanXuat`, `nhaCungCap`, `nhomSanPham`, `mauSac`)
VALUES ('VanPhongPham', 'V23042024-0000001', 0.08, 25000, 8000, 0, 'Bút bi cao cấp', '2023-11-10 19:41:57.000', 120, 'Bút Bi Thiên Long', 'Còn hàng', 'ThaiLan', 'NCC24102023-000003', 'NSP003', 'BLACK');

INSERT INTO `sanpham` (`Discriminator`, `maSanPham`, `VAT`, `donGiaBan`, `donGiaNhap`, `giamGia`, `moTa`, `ngayTao`, `soLuongTon`, `tenSanPham`, `tinhTrang`, `noiSanXuat`, `nhaCungCap`, `nhomSanPham`, `mauSac`)
VALUES ('VanPhongPham', 'V23042024-0000002', 0.08, 30000, 10000, 0, 'co', '2023-11-10 19:41:57.000', 100, 'But Muc', 'Còn hàng', 'ThaiLan', 'NCC23102023-000005', 'NSP002', 'BLACK');

INSERT INTO `sanpham` (`Discriminator`, `maSanPham`, `VAT`, `donGiaBan`, `donGiaNhap`, `giamGia`, `moTa`, `ngayTao`, `soLuongTon`, `tenSanPham`, `tinhTrang`, `noiSanXuat`, `nhaCungCap`, `nhomSanPham`, `mauSac`)
VALUES ('VanPhongPham', 'VPP23102023-000005', 0.08, 11570, 8900, 0, 'Loại bút lông bảng thân cỡ vừa, được thiết kế phù hợp với giáo viên, NVVP', '2023-11-29 23:48:06.930', 72, 'BÚT LÔNG BẢNG WB-03', 'Còn hàng', 'Việt Nam', 'NCC24102023-000003', 'NSP001', 'COLORS');

-- 4. Chèn dữ liệu vào bảng hoadon (phụ thuộc khachhang, nhanvien, khuyenmai)
INSERT INTO `hoadon` (`maHoaDon`, `ngayTao`, `soTienGiam`, `thanhTien`, `tongTien`, `maKhachHang`, `maKhuyenMai`, `maNhanVien`) VALUES ('HD22042024-000001', '2024-04-22 15:08:23.123', 0, 7.5, 7.5, 1, NULL, 'QL23102023-000007');
INSERT INTO `hoadon` (`maHoaDon`, `ngayTao`, `soTienGiam`, `thanhTien`, `tongTien`, `maKhachHang`, `maKhuyenMai`, `maNhanVien`) VALUES ('HD22042024-000003', '2024-04-22 15:08:23.123', 0, 6, 6, 1, NULL, 'QL23102023-000007');

-- 5. Chèn dữ liệu vào bảng hoadonhoantra (phụ thuộc hoadon, nhanvien)
INSERT INTO `hoadonhoantra` (`maHoaDonHoanTra`, `ghiChu`, `ngayHoan`, `tienHoanTra`, `tinhTrangHoaDon`, `hoaDon`, `nhanVien`) VALUES ('HT22042024-000001', '', '2024-04-22 15:08:23.123', 7.5, 1, 'HD22042024-000001', 'QL23102023-000007');
INSERT INTO `hoadonhoantra` (`maHoaDonHoanTra`, `ghiChu`, `ngayHoan`, `tienHoanTra`, `tinhTrangHoaDon`, `hoaDon`, `nhanVien`) VALUES ('HT22042024-000002', '', '2024-04-22 15:08:23.123', 3, 1, 'HD22042024-000001', 'QL23102023-000007');
INSERT INTO `hoadonhoantra` (`maHoaDonHoanTra`, `ghiChu`, `ngayHoan`, `tienHoanTra`, `tinhTrangHoaDon`, `hoaDon`, `nhanVien`) VALUES ('HT23042024-000002', '', '2024-04-23 01:21:25.324525', 6, 1, 'HD22042024-000003', 'QL23102023-000007');

-- 6. Chèn dữ liệu vào bảng hoadondoihang (phụ thuộc khachhang)
INSERT INTO `hoadondoihang` (`maHoaDonDoiHang`, `lyDo`, `ngayDoi`, `maKhachHang`) VALUES ('HDD22042024-000001', '', '2024-04-22 15:08:23.123', 1);
INSERT INTO `hoadondoihang` (`maHoaDonDoiHang`, `lyDo`, `ngayDoi`, `maKhachHang`) VALUES ('HDD22042024-000002', '', '2024-04-22 15:08:23.123', 1);
INSERT INTO `hoadondoihang` (`maHoaDonDoiHang`, `lyDo`, `ngayDoi`, `maKhachHang`) VALUES ('HDD23042024-000001', '', '2024-04-23 01:21:25.324525', 1);

-- 7. Chèn dữ liệu vào các bảng chi tiết (quan hệ nhiều-nhiều)
-- Chèn dữ liệu vào bảng chitiethoadon
INSERT INTO `chitiethoadon` (`soLuong`, `thanhTien`, `maHoaDon`, `maSanPham`) VALUES (5, 7.5, 'HD22042024-000001', 'S22042024-000001');

-- Chèn dữ liệu vào bảng chitiethoantra
INSERT INTO `chitiethoantra` (`soLuong`, `thanhTien`, `sanPham`, `hoaDonHoanTra`) VALUES (5, 7.5, 'S22042024-000001', 'HT22042024-000001');
INSERT INTO `chitiethoantra` (`soLuong`, `thanhTien`, `sanPham`, `hoaDonHoanTra`) VALUES (2, 3, 'S22042024-000001', 'HT22042024-000002');

-- Chèn dữ liệu vào bảng chitiethoadondoi
INSERT INTO `chitiethoadondoi` (`soLuong`, `thanhTien`, `maSanPham`, `HoaDonDoiHang`) VALUES (4, 6, 'S22042024-000002', 'HDD23042024-000001');