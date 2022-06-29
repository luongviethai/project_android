<?php
	include '../config/config.php'; 

	$maKhachHang = $_POST['maKhachHang'];
	$masanpham = $_POST['masanpham'];
	$soLuong = $_POST['soLuong'];
	$tien = $_POST['tien'];

	/*$maKhachHang = 2;
	$masanpham = 4;
	$soLuong = 5;
	$tien = 98760;*/

	$sql = "SELECT * FROM giohang
		WHERE maKhachHang = '$maKhachHang' AND masanpham = '$masanpham'";

	$result = $conn->query($sql);
	if ($result->num_rows > 0) {
		//echo "Có";

		while ($row = $result->fetch_assoc()) {
			$soLuongOld = $row['soLuong'];
			$tienOld = $row['tien'];
	 	}

	 	//echo $soLuongOld;
	 	//echo "-".$tienOld;

	 	$soLuongEnd = $soLuongOld + $soLuong;
	 	$tienEnd = $tienOld + $tien;
	
		echo "MaKH: ".$maKhachHang."-"."masp: ".$masanpham."-số: ".$soLuongOld."-mới: ".$soLuongEnd."-giá: ".$tienOld;

		$sql1 = "UPDATE giohang SET soLuong = '$soLuongEnd', tien = '$tienEnd' 
			WHERE maKhachHang = '$maKhachHang' AND masanpham = '$masanpham'";
		if(mysqli_query($conn,$sql1)){
			echo "Done";
		} else{
			echo "error";
		}
		
	}
	else
	{
		//echo "Không";

		$sql2 = "INSERT INTO giohang VALUES('$maKhachHang','$masanpham','$soLuong','$tien')";
		if(mysqli_query($conn,$sql2)){
			echo "Done";
		} else{
			echo "error";
		}
	}

	
	
?>