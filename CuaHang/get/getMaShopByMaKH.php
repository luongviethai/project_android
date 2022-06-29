<?php

    include '../config/config.php'; 

    $maKhachHang = $_POST['maKH'];


    //$maKhachHang = 2;



	$sql="SELECT shop.maShop,shop.tenShop
		FROM shop, tblkhachhang
		WHERE shop.maKhachHang = tblkhachhang.maKhachHang AND tblkhachhang.maKhachHang = '$maKhachHang'";
		
	$result = $conn->query($sql);

	if ($result->num_rows > 0) {
		while ($row = $result->fetch_assoc()) {
			echo $row['maShop'];
			//echo $row['tenShop'];
		}
	}

	$conn->close();


?>