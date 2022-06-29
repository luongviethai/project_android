<?php

	require "connect.php";
	$tenKhachHang = $_POST['tenKhachHang'];
	$anhKhachHang = $_POST['anhKhachHang'];
	$sdt = $_POST['sdt'];
	$diaChi = $_POST['diaChi'];
	$tenTaiKhoan = $_POST['tenTaiKhoan'];
	$matKhau = $_POST['matKhau'];

	if (strlen($tenKhachHang)>0 && strlen($anhKhachHang)>0 && strlen($sdt)>0 && strlen($diaChi)>0 && strlen($tenTaiKhoan)>0 && strlen($matKhau)>0) {

		$query = "INSERT INTO tblkhachhang VALUES (null, '$tenKhachHang','$anhKhachHang','$sdt','$diaChi','$tenTaiKhoan','$matKhau')";
		$data  = mysqli_query ($con,$query);
		if($data){
			echo "Success";

		}else{
			echo "Fail";

		}
	}else{
		echo"Null";

	}

	

?>