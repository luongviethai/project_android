<?php

	$conn=mysqli_connect("localhost","root","","congtyconghe");
	$conn->query("set names 'utf8'");


	$maKhachHang = $_POST['maKhachHang'];

	$tenKhachHang = $_POST['tenKhachHang'];
	//$anhKhachHang = $_POST['anhKhachHang'];
	$sdt = $_POST['sdt'];
	$diaChi = $_POST['diaChi'];
	$tenTaiKhoan = $_POST['tenTaiKhoan'];
	$matKhau = $_POST['matKhau'];


	//Cập nhật độc giả
	$cmd2 = "UPDATE tblKhachHang
	SET tenKhachHang= '$tenKhachHang' /*, anhDocGia = 'Image'*/, sdt ='$sdt',diaChi='$diaChi',tenTaiKhoan = '$tenTaiKhoan',matKhau='$matKhau' 
	WHERE maKhachHang = '$maKhachHang'";


	if ($conn->query($cmd2) === TRUE) {
	  echo "Done";
	} else {
	  echo "Error updating record: " . $conn->error;
	}





	$conn->close();
?>