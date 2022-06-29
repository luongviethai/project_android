<?php

	include '../config/config.php';


	$maKhachHang = $_POST['maKhachHang'];
	$tenKhachHang = $_POST['tenKhachHang'];
	$diaChi = $_POST['diaChi'];
	$sdt = $_POST['sdt'];
	$tien = $_POST['tien'];
	$ngayDatHang = $_POST['ngayDatHang'];


	/*$maKH = 2;
	$diaChi = "HN";
	$sdt = "0585885";
	$ngayDatHang = "2020-09-09";
	*/
	//$tien = 32454600;


	$sql = "INSERT INTO donhang
	VALUES (NULL, '$maKhachHang', '$tenKhachHang', '$diaChi', '$sdt', '$tien', '$ngayDatHang', NULL, DEFAULT)";


	$result = $conn->query($sql);


	if ($result === TRUE) {
	  echo "Done";
	} else {
	  echo "Error updating record: " . $conn->error;
	}


	$conn->close();

?>