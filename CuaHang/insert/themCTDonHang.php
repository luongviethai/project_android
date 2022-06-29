<?php

	include '../config/config.php';


	$masanpham = $_POST['masanpham'];
	$soLuong = $_POST['soLuong'];
	$tien = $_POST['tien'];

	/*$maSP = 25;
	$soLuong = 2;
	$tien = 50000;*/


	$sql = "SELECT maDonHang FROM donhang ORDER BY maDonHang DESC LIMIT 1;";
	$result = $conn->query($sql);


	$maDHNew = 0;
	if ($result->num_rows > 0) {
	  	// output data of each row
		if ($row = $result->fetch_assoc()) {
	    	$maDHNew = $row['maDonHang'];
	  	}
	}


	$cmd="INSERT INTO chitietdonhang
		VALUES ('$maDHNew', '$masanpham', '$soLuong', '$tien')";
	if(mysqli_query($conn,$cmd))
	{
		echo "Done";
	}
	else
	{
		echo "False";
	}


	$conn->close();

?>