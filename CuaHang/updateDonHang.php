<?php

	$conn=mysqli_connect("localhost","root","","congtyconghe");
	$conn->query("set names 'utf8'");


	$id = $_POST['id'];

	$tensanpham = $_POST['tensanpham'];
	$tenkhachhang = $_POST['tenkhachhang'];
	//$anhKhachHang = $_POST['anhKhachHang'];
	$diachi = $_POST['diachi'];
	$sdt = $_POST['sdt'];
	$tien = $_POST['tien'];
	
	

	//Cập nhật độc giả
	$cmd2 = "UPDATE hoadon
	SET tensanpham= '$tensanpham' , tenkhachhang = 'tenkhachhang',diachi='$diachi', sdt ='$sdt',tien = '$tien'
	WHERE id = '$id'";


	if ($conn->query($cmd2) === TRUE) {
	  echo "Done";
	} else {
	  echo "Error updating record: " . $conn->error;
	}





	$conn->close();
?>