<?php

	$conn=mysqli_connect("localhost","root","","congtyconghe");
	$conn->query("set names 'utf8'");

	if ($conn->connect_error) {
	  die("Connection failed: " . $conn->connect_error);
	}



	$tensanpham = $_POST['tensanpham'];
	$gia = $_POST['gia'];
	$loai = $_POST['loai'];
	$hinhanh = $_POST['hinhanh'];
	$mota = $_POST['mota'];




	$sql = "INSERT INTO sanphamcuatoi
	 VALUES (NULL, '$tensanpham', '$loai', '$loai', '$hinhanh', '$mota')";
	

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