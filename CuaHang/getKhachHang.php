<?php

	$conn=mysqli_connect("localhost","root","","congtyconghe");
	$conn->query("set names 'utf8'");

	if ($conn->connect_error) {
	  die("Connection failed: " . $conn->connect_error);
	}

	$sql="Select * from tblKhachHang;";
	$result = $conn->query($sql);

	$mangKhachHang = array();
	if ($result->num_rows > 0) {
	while ($row = $result->fetch_assoc()) {
		array_push($mangKhachHang, 
			["maKhachHang"=>$row['maKhachHang'], 
			"tenKhachHang"=>$row['tenKhachHang'],
			"anhKhachHang"=>$row['anhKhachHang'],
			"sdt"=>$row['sdt'],
			"diaChi"=>$row['diaChi'],
			"tenTaiKhoan"=>$row['tenTaiKhoan'], 
			"matKhau"=>$row['matKhau']]);
	}
	}

	echo json_encode($mangKhachHang);


	$conn->close();

?>