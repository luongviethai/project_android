<?php

	$conn=mysqli_connect("localhost","root","","congtyconghe");
	$conn->query("set names 'utf8'");

	if ($conn->connect_error) {
	  die("Connection failed: " . $conn->connect_error);
	}

	$maKhachHang = $_POST['maKhachHang'];


	$sql="DELETE FROM tblKhachHang WHERE maKhachHang = '$maKhachHang'";
	
	$result = $conn->query($sql);

	if ($result === TRUE) {
	  echo "Done";
	} else {
	  echo "Error deleting record: " . $conn->error;
	}



	$conn->close();

?>