<?php
	$conn = new mysqli("localhost","root","","congtyconghe");
	$conn->query("set names 'utf8'");

	if ($conn->connect_error) {
	  die("Connection failed: " . $conn->connect_error);
	}

	    $tenTaiKhoan = $_POST['tenTaiKhoan'];
	    $matKhau = $_POST['matKhau'];


	$sql = "SELECT maKhachHang 
	FROM tblKhachHang 
	WHERE tenTaiKhoan = '$tenTaiKhoan' AND matKhau = '$matKhau'";

	$result = mysqli_query($conn,$sql);
	$num_rows = mysqli_num_rows($result);
	
			

	if ($num_rows==0) {
		echo "false";
	}else{
		while ($row = $result->fetch_assoc()) {
			$maKhachHang = $row['maKhachHang'];
		}
		  echo $maKhachHang;
		}

	$conn->close();


?>