<?php

	$conn=mysqli_connect("localhost","root","","congtyconghe");
	$conn->query("set names 'utf8'");

	if ($conn->connect_error) {
	  die("Connection failed: " . $conn->connect_error);
	}


	$tenKhachHang = $_POST['tenKhachHang'];
	$anhKhachHang = $_POST['anhKhachHang'];
	$sdt = $_POST['sdt'];
	$diaChi = $_POST['diaChi'];
	$tenTaiKhoan = $_POST['tenTaiKhoan'];
	$matKhau = $_POST['matKhau'];



	$sql = "INSERT INTO tblKhachHang
	 VALUES (NULL, '$tenKhachHang', '$anhKhachHang', '$sdt', '$diaChi', '$tenTaiKhoan', '$matKhau')";
	

	$result = $conn->query($sql);

	if($result)
	{
		echo "Done";
	}
	else
	{
		echo "False";
	}

	mysqli_close($conn);

?>