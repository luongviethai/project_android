<?php
	include 'config/config.php'; 

	$maKhachHang = $_POST['maKhachHang'];

	$masanpham = $_POST['masanpham'];
	$soLuong = $_POST['soLuong'];
	$tien = $_POST['tien'];
	$query = "UPDATE giohang 
		SET soLuong = '$soLuong',tien= '$tien'
		WHERE maKhachHang = '$maKhachHang' AND masanpham = '$masanpham' ";

	if(mysqli_query($conn,$query)){
		echo "done";
	} else{
		echo "error";
	}
?>