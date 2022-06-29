-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th9 18, 2021 lúc 10:19 AM
-- Phiên bản máy phục vụ: 10.4.17-MariaDB
-- Phiên bản PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `congtyconghe`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `maDonHang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `soLuong` int(11) NOT NULL,
  `tien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`maDonHang`, `masanpham`, `soLuong`, `tien`) VALUES
(1, 0, 0, 0),
(2, 24, 2, 130000),
(7, 22, 2, 120000),
(7, 2, 2, 80000),
(8, 22, 2, 120000),
(8, 2, 2, 80000),
(9, 1, 3, 45000),
(10, 9, 2, 30000),
(10, 5, 2, 600000),
(11, 1, 2, 30000),
(12, 5, 2, 600000),
(13, 1, 2, 30000),
(14, 2, 2, 80000),
(15, 5, 2, 600000),
(16, 1, 3, 45000),
(17, 1, 1, 15000),
(18, 22, 3, 180000),
(19, 2, 2, 80000),
(20, 1, 3, 45000),
(21, 21, 2, 180000),
(22, 23, 2, 240000),
(23, 6, 2, 90000),
(24, 69, 2, 180000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `diachigiaohang`
--

CREATE TABLE `diachigiaohang` (
  `khuvuc` varchar(200) NOT NULL,
  `tienship` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `diachigiaohang`
--

INSERT INTO `diachigiaohang` (`khuvuc`, `tienship`) VALUES
('da nang', 60000),
('ha noi', 20000),
('hai duong', 20000),
('hai phong', 30000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `maDonHang` int(11) NOT NULL,
  `maKhachHang` int(11) NOT NULL,
  `tenKhachHang` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL,
  `diaChi` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL,
  `sdt` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL,
  `tongTien` bigint(110) NOT NULL,
  `ngayDatHang` date NOT NULL,
  `ngayNhanHang` date DEFAULT NULL,
  `trangThai` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`maDonHang`, `maKhachHang`, `tenKhachHang`, `diaChi`, `sdt`, `tongTien`, `ngayDatHang`, `ngayNhanHang`, `trangThai`) VALUES
(1, 0, '', '', '', 0, '0000-00-00', NULL, ''),
(2, 2, '213', '123', '123', 150000, '2021-09-01', NULL, ''),
(3, 2, 'Hoàng Văn Lương', '123', '456', 200000, '2021-09-01', NULL, ''),
(4, 2, 'Hoàng Văn Lương', '123', '456', 200000, '2021-09-01', NULL, ''),
(5, 2, 'Hoàng Văn Lương', '123', '456', 200000, '2021-09-01', NULL, ''),
(6, 2, '123', '123', '123', 140000, '2021-09-01', NULL, ''),
(7, 2, 'luong', 'tien tien', '0123', 220000, '2021-09-01', NULL, ''),
(8, 2, '852', '852', '52', 220000, '2021-09-01', NULL, ''),
(9, 1, '12344', '44', '44', 65000, '2021-09-01', NULL, ''),
(10, 1, 'Lương111', '444', '444', 650000, '2021-09-04', NULL, ''),
(11, 1, 'Hoàng Văn Lương', 'Hải Dương', '0396112459', 50000, '2021-09-11', NULL, ''),
(12, 1, 'Hoàng Lương', 'Hairi Dương', '0396112459', 620000, '2021-09-12', NULL, ''),
(13, 1, 'Đào Đình Tiến', 'Cẩm Giàng', '0123456789', 50000, '2021-09-12', NULL, ''),
(14, 1, 'Hữu Hà', 'Gia Lộc', '012345999', 100000, '2021-09-12', NULL, ''),
(15, 1, 'Tuong', 'Kinh Mon', '123456789', 620000, '2021-09-12', NULL, ''),
(16, 2, 'trần Linh', 'Ninh Giang', '0963258741', 65000, '2021-09-12', NULL, ''),
(17, 2, 'Lương99', 'Hải Dương', '0396112459', 35000, '2021-09-12', NULL, ''),
(18, 1, 'Tăng Xuân Tiền', 'Tp Hải Dương - tỉnh Hải Dương', '0982909999', 200000, '2021-09-12', NULL, ''),
(19, 2, 'Đào Tiến', 'Cẩm Giàng - Hải Dương', '0396112459', 100000, '2021-09-13', NULL, ''),
(20, 15, 'Vũ Ngọc Tưởng', 'Kinh Môn', '055588555', 65000, '2021-09-13', NULL, ''),
(21, 15, 'Vũ Ngọc Tưởng', 'Kinh Môn - Hai Dươn', '055588555', 200000, '2021-09-13', NULL, ''),
(22, 1, 'Tăng Xuân Thành', 'Tp Hải Dương - tỉnh Hải Dương', '0982909228', 260000, '2021-09-13', NULL, ''),
(23, 1, 'Tăng Xuân Tiền', 'Tp Hải Dương - tỉnh Hải Dương', '09829092289', 110000, '2021-09-13', NULL, ''),
(24, 1, 'Tăng Xuân Tiền', 'Tp Hải Dương - tỉnh Hải Dương', '01258977', 200000, '2021-09-13', NULL, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `giamgia`
--

CREATE TABLE `giamgia` (
  `magiamgia` varchar(11) NOT NULL,
  `phantram` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `giamgia`
--

INSERT INTO `giamgia` (`magiamgia`, `phantram`) VALUES
('giamgia1', '20%-30%'),
('giamgia2', '10%-40%'),
('giamgia3', 'trên 50%'),
('giamgia4', '15%-20%'),
('giamgia5', '10%-50%');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `giohang`
--

CREATE TABLE `giohang` (
  `maKhachHang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `soLuong` int(11) NOT NULL,
  `tien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `giohang`
--

INSERT INTO `giohang` (`maKhachHang`, `masanpham`, `soLuong`, `tien`) VALUES
(-1, 2, 5, 200000),
(2, 1, 2, 30000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `id` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `maKhachHang` int(11) NOT NULL,
  `tensanpham` varchar(2000) NOT NULL,
  `diachi` varchar(110) NOT NULL,
  `sdt` int(11) NOT NULL,
  `tien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`id`, `masanpham`, `maKhachHang`, `tensanpham`, `diachi`, `sdt`, `tien`) VALUES
(25, 4, 2, 'nullSữa Nan Nga ', 'Xã Tiền Tiến - TP Hải Dương - tỉnh Hải Dương', 396112459, 370000),
(26, 2, 1, 'nullSữa Vinamilk', 'Xã Quyết Thắng - TP Hải Dương - tỉnh Hải Dương', 256489126, 60000),
(27, 4, 1, 'Bánh Oreo', 'Hà Nội', 396225577, 100000),
(28, 3, 2, 'Bia 333', 'HD', 456789325, 200000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `maLoaiSP` int(11) NOT NULL,
  `tenLoaiSP` varchar(220) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`maLoaiSP`, `tenLoaiSP`) VALUES
(1, 'Sữa'),
(2, 'Bánh'),
(3, 'Nước Ngọt'),
(4, 'Bia'),
(5, 'Sữa tươi');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `masanpham` int(11) NOT NULL,
  `tensanpham` varchar(100) NOT NULL,
  `gia` int(12) NOT NULL,
  `loai` varchar(255) DEFAULT NULL,
  `hinhanh` varchar(2000) NOT NULL,
  `mota` varchar(2000) NOT NULL,
  `maLoaiSP` int(11) NOT NULL,
  `maShop` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`masanpham`, `tensanpham`, `gia`, `loai`, `hinhanh`, `mota`, `maLoaiSP`, `maShop`) VALUES
(1, 'CoCa Cola', 15000, '', 'https://i.imgur.com/iHdi0Gp.jpg', 'đồ uống mát lạnh cho ngày dài mệt mỏi\r', 3, 1),
(2, 'Sữa Vinamilk', 40000, 'Sữa tươi', 'https://i.imgur.com/9OvTZkQ.png', 'Sữa tốt', 5, 1),
(3, 'sữa chua dâu', 22000, 'suachua', 'https://i.imgur.com/NuPEKxT.jpg', 'sữa chua dâu món tráng miệng tuyệt vời sau mỗi bữa ăn.', 0, 2),
(4, 'Sữa Nan Nga', 350000, 'Sữa', 'https://i.imgur.com/UKyv2ok.jpg', 'Sữa Nan Nga số 1 400g từ trước tới nay vẫn nổi tiếng là dòng sữa mát, nhạt với đạm chất lượng Optipro và các vi sinh vật có lợi Probiotics, giàu chất xơ giúp tạo ra một môi trường sống thuận lợi cho các vi sinh vật đường ruột.', 1, 1),
(5, 'Sữa Aptamil', 300000, 'Sữa', 'https://i.imgur.com/VLgXws0.jpg', 'Trà sữa chất lượng royal tea chất lượng cao', 1, 2),
(6, 'Pepsi', 45000, 'Nước ngọt', 'https://i.imgur.com/8eeVYCf.jpg', 'Nước giải khát', 3, 1),
(8, 'Bia Heineken', 20000, 'Bia', 'https://i.imgur.com/6RP9GvN.jpg', 'Bia Ngon', 4, 1),
(9, 'Bia Sài Gòn', 15000, 'Bia', 'https://i.imgur.com/B0k2KIY.jpg', 'Bia ngon', 4, 1),
(10, 'Bia Tiger', 46000, 'Bia', 'https://i.imgur.com/C8Y1985.jpg', 'Bia tốt', 4, 2),
(21, 'Bánh OREO', 90000, 'Bánh', 'https://i.imgur.com/2R6GKSA.jpg', 'Bánh ngon', 2, 1),
(22, 'Bánh COSY', 60000, 'Bánh', 'https://i.imgur.com/2KqgscE.jpg', 'Bánh ngon', 2, 2),
(23, 'Bánh GarudaFood Blue', 120000, 'Bánh', 'https://i.imgur.com/njuX1gE.jpg', 'Bánh ngon', 2, 1),
(24, 'Bánh Choco-Pie', 65000, 'Bánh', 'https://i.imgur.com/KfgAtDL.jpg', 'Bánh ngon', 2, 2),
(41, 'Sữa Calo Sure', 450000, 'Sữa', 'https://i.imgur.com/J2MZjwL.jpg', 'Sữa tốt', 1, 2),
(42, 'Sữa Similac', 460000, 'Sữa', 'https://i.imgur.com/6tshVpH.jpg', 'Sữa tốt', 1, 1),
(43, 'Sữa Similac Alimentum1', 550000, 'Sữa', 'https://i.imgur.com/5d9JraM.jpg', 'Sữa tốt', 1, 1),
(44, 'Bánh Oreo', 150000, 'Bánh', 'http://192.168.0.102/CuaHang/image/2b6878f19ff22c040f328bdc704a17041629450649099.jpg', 'bánh ngon', 2, 1),
(55, 'Sữa', 35000, '1', 'http://192.168.0.102/CuaHang/image/suatuoi1631433144822.png', 'Sữa tươi 100%', 5, 1),
(57, 'Bia Tiger', 20000, '2', 'http://192.168.0.102/CuaHang/image/tiger1631433438054.jpg', 'Bia ngon', 4, 1),
(58, 'Bia Tiger', 25000, '1', 'http://192.168.0.102/CuaHang/image/tiger1631517581677.jpg', 'Bia ngon', 4, 1),
(59, 'Sữa Similac', 550000, '1', 'http://192.168.0.102/CuaHang/image/sua-similac-newborn-iq-plus-hmo-so-1-400g-moi-11631518225999.jpg', 'Sữa cho bé 1 tuổi', 1, 2),
(60, 'Sữa Nan', 350000, '1', 'http://192.168.0.102/CuaHang/image/sua-nan-nga-cho-tre-so-sinh1631520340948.jpg', 'Sữa dành bé 6 tháng tuổi', 1, 1),
(61, 'Bia Heineken', 20000, '1', 'http://192.168.0.102/CuaHang/image/heineken1631520410302.jpg', '123', 4, 2),
(62, 'coca cola', 12000, '1', 'http://192.168.0.102/CuaHang/image/6-lon-nuoc-ngot-coca-cola-zero-320ml-202103202205541074_300x3001631522820668.jpg', '123', 3, 0),
(63, 'Coca Cola bản đặc biệt', 20000, '1', 'http://192.168.0.102/CuaHang/image/6-lon-nuoc-ngot-coca-cola-zero-320ml-202103202205541074_300x3001631523149178.jpg', 'Giải khát mùa hè. Bản đặc biệt', 3, 1),
(64, 'Bia 333', 12000, '1', 'http://172.20.10.12/CuaHang/image/3331631542606015.jpg', 'Bia chính hãng', 4, 2),
(68, 'Pepsi', 15000, '2', 'http://192.168.0.102/CuaHang/image/pepsi1631544350501.jpg', 'Giải khát mùa hè', 3, 2),
(69, 'Bánh Cosy Dừa', 90000, '1', 'http://192.168.0.102/CuaHang/image/893_4680_112673_21631544688351.jpg', 'Bánh ngon', 2, 1),
(70, 'Bia Sài Gòn', 19000, '1', 'http://192.168.36.174/CuaHang/image/Saigon1631578038912.jpg', 'Thuong hiệu VIet', 4, 1),
(71, 'Coca co', 6575, '1', 'http://192.168.36.174/CuaHang/image/6-lon-nuoc-ngot-coca-cola-zero-320ml-202103202205541074_300x3001631583645651.jpg', 'Nuoc ngọt', 3, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanphamcuatoi`
--

CREATE TABLE `sanphamcuatoi` (
  `masanpham` int(20) NOT NULL,
  `tensanpham` varchar(100) NOT NULL,
  `gia` varchar(20) NOT NULL,
  `hinhanh` varchar(2000) NOT NULL,
  `mota` varchar(2000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `sanphamcuatoi`
--

INSERT INTO `sanphamcuatoi` (`masanpham`, `tensanpham`, `gia`, `hinhanh`, `mota`) VALUES
(1, 'CoCa Cola1', '15000', 'https://i.imgur.com/iHdi0Gp.jpg', 'đồ uống mát lạnh cho ngày dài mệt mỏi\r\n'),
(4, 'Bia Tiger', '18000', 'http://192.168.0.102/CuaHang/image/tiger1628302716969.jpg', 'Bia ngon'),
(5, 'Sữa', '150000', 'http://192.168.0.102/CuaHang/image/sua-similac-newborn-iq-plus-hmo-so-1-400g-moi-11628315043272.jpg', 'sadasd'),
(6, 'Bia', '15000', 'http://192.168.0.102/CuaHang/image/heineken1628315201838.jpg', '777'),
(7, 'Bánh oreo', '99000', 'http://192.168.0.102/CuaHang/image/2b6878f19ff22c040f328bdc704a17041628315428044.jpg', 'Bánh ngon'),
(8, 'Sữa tươi Vinamilk', '30000', 'http://192.168.0.102/CuaHang/image/suatuoi1628316021522.png', 'Sữa tươi ngon'),
(9, 'Bia', '15000', 'http://192.168.0.102/CuaHang/image/heineken1628416474274.jpg', 'Bia ngon'),
(10, 'Bánh', '5000', 'http://192.168.0.102/CuaHang/image/2b6878f19ff22c040f328bdc704a17041629388192091.jpg', '´dasdasd'),
(11, '', '', '', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `shop`
--

CREATE TABLE `shop` (
  `maShop` int(11) NOT NULL,
  `maKhachHang` int(11) NOT NULL,
  `tenShop` varchar(220) COLLATE utf8_vietnamese_ci NOT NULL,
  `sdt` varchar(220) COLLATE utf8_vietnamese_ci NOT NULL,
  `email` varchar(220) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `shop`
--

INSERT INTO `shop` (`maShop`, `maKhachHang`, `tenShop`, `sdt`, `email`) VALUES
(1, 1, 'Công Hè123', '0233525', 'hoang@gmail.com'),
(2, 2, 'Công Hè', '0233', 'hoang@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tblkhachhang`
--

CREATE TABLE `tblkhachhang` (
  `maKhachHang` int(11) NOT NULL,
  `tenKhachHang` varchar(45) COLLATE utf8_vietnamese_ci NOT NULL,
  `anhKhachHang` varchar(600) COLLATE utf8_vietnamese_ci NOT NULL,
  `sdt` varchar(45) COLLATE utf8_vietnamese_ci NOT NULL,
  `diaChi` varchar(45) COLLATE utf8_vietnamese_ci NOT NULL,
  `tenTaiKhoan` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL,
  `matKhau` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `tblkhachhang`
--

INSERT INTO `tblkhachhang` (`maKhachHang`, `tenKhachHang`, `anhKhachHang`, `sdt`, `diaChi`, `tenTaiKhoan`, `matKhau`) VALUES
(2, 'luong8', 'http://192.168.0.102/CuaHang/image/72831628151175139.png_860', '0396112459', 'Hải Dương', 'ttt', '888'),
(13, 'Hoàng Văn Lương', 'http://192.168.0.102/CuaHang/image/Luong1629726515156.jpg', '0396112459', 'Xã Tiền Tiến - Tp Hải Dương', 'hoangluong', '123'),
(10, 'Trần Thị Mỹ Linh1', 'http://192.168.0.102/CuaHang/image/Linh1629726213638.jpg', '0399999966', 'Ninh Giang - Hải  Dương', 'mylinh', '123'),
(12, 'Vũ Hữu Hà', 'http://192.168.0.102/CuaHang/image/Ha1629726402238.jpg', '0298755845', 'Gia Lộc - Hải Dương', 'huuha', '123'),
(11, 'Vũ Ngọc Tưởng', 'http://192.168.0.102/CuaHang/image/Tuong1629726323954.jpg', '0125987645', 'Kinh Môn - Hải Dương', 'ngoctuong', '123'),
(1, 'Tăng Xuân Tiền', 'http://192.168.0.102/CuaHang/image/Tien1628559421421.jpg', '0982909228', 'Tp Hải Dương - tỉnh Hải Dương', 'xuantien', '123'),
(15, 'Vũ Ngọc Tưởng', 'http://192.168.0.102/CuaHang/image/Tuong1631541329396.jpg', '055588555', 'Kinh Môn', 'Tuong123', '123');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ykienkhachhang`
--

CREATE TABLE `ykienkhachhang` (
  `id` int(11) NOT NULL,
  `hovaten` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `noidung` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `maKhachHang` int(11) NOT NULL,
  `sodienthoai` varchar(20) COLLATE utf8_vietnamese_ci NOT NULL,
  `email` varchar(55) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `diachigiaohang`
--
ALTER TABLE `diachigiaohang`
  ADD PRIMARY KEY (`khuvuc`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`maDonHang`);

--
-- Chỉ mục cho bảng `giamgia`
--
ALTER TABLE `giamgia`
  ADD PRIMARY KEY (`magiamgia`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`maLoaiSP`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`masanpham`);

--
-- Chỉ mục cho bảng `sanphamcuatoi`
--
ALTER TABLE `sanphamcuatoi`
  ADD PRIMARY KEY (`masanpham`);

--
-- Chỉ mục cho bảng `shop`
--
ALTER TABLE `shop`
  ADD PRIMARY KEY (`maShop`);

--
-- Chỉ mục cho bảng `tblkhachhang`
--
ALTER TABLE `tblkhachhang`
  ADD PRIMARY KEY (`maKhachHang`);

--
-- Chỉ mục cho bảng `ykienkhachhang`
--
ALTER TABLE `ykienkhachhang`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `maDonHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `maLoaiSP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `masanpham` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT cho bảng `sanphamcuatoi`
--
ALTER TABLE `sanphamcuatoi`
  MODIFY `masanpham` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `shop`
--
ALTER TABLE `shop`
  MODIFY `maShop` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `tblkhachhang`
--
ALTER TABLE `tblkhachhang`
  MODIFY `maKhachHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `ykienkhachhang`
--
ALTER TABLE `ykienkhachhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
