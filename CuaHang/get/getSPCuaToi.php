<?php
if($_SERVER['REQUEST_METHOD']=='GET'){


$conn = new mysqli("localhost","root","","congtyconghe");
	$conn->query("set names 'utf8'");

	if ($conn->connect_error) {
	  die("Connection failed: " . $conn->connect_error);
	}

	$maKhachHang = $_GET['maKhachHang'];


$sql = "SELECT tblkhachhang.maKhachHang, sanpham.masanpham, sanpham.tensanpham, sanpham.gia, sanpham.loai, sanpham.hinhanh, sanpham.mota, sanpham.maLoaiSP, sanpham.maShop
FROM shop, tblkhachhang, sanpham
WHERE sanpham.maShop = shop.maShop AND shop.maKhachHang = tblkhachhang.maKhachHang
AND tblkhachhang.maKhachHang = '$maKhachHang'";

	$result = $conn->query($sql);

	$mangSanPham = array();
	if ($result->num_rows > 0) {
	while ($row = $result->fetch_assoc()) {
		array_push($mangSanPham, 
			["masanpham"=>$row['masanpham'], 
			"tensanpham"=>$row['tensanpham'],
			"gia"=>$row['gia'],

			"loai"=>$row['loai'],
			"hinhanh"=>$row['hinhanh'],
			"mota"=>$row['mota'],
			"maLoaiSP"=>$row['maLoaiSP'],
			"maShop"=>$row['maShop']]);

	}
	}

	echo json_encode($mangSanPham);

		$conn->close();

}
?>