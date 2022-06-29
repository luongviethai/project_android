<?php

	$conn=mysqli_connect("localhost","root","","congtyconghe");
	$conn->query("set names 'utf8'");

	if ($conn->connect_error) {
	  die("Connection failed: " . $conn->connect_error);
	}

	$sql="Select * from sanpham;";
	$result = $conn->query($sql);

	$mangSanPham = array();
	if ($result->num_rows > 0) {
	while ($row = $result->fetch_assoc()) {
		array_push($mangSanPham, 
			["masanpham"=>$row['masanpham'], 
			"tensanpham"=>$row['tensanpham'],
			"gia"=>$row['gia'],
			"loai"=>$row['loai'],
			"hinhanh"=>$row['hinhanh'],

			"mota"=>$row['mota'],
			"maLoaiSP"=>$row['maLoaiSP'],
			"maShop"=>$row['maShop'],
			]);
	}
	}

	echo json_encode($mangSanPham);


	$conn->close();

?>