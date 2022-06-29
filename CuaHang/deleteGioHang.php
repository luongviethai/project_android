<?php

    include 'config/config.php'; 

    $maKhachHang = $_POST['maKhachHang'];

	$query = "DELETE FROM giohang WHERE maKhachHang = '$maKhachHang'";
	if(mysqli_query($conn,$query)){
		echo "Done";
	} else{
		echo "error";
	}

?>