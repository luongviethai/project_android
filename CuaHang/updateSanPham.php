<?php

	$conn=mysqli_connect("localhost","root","","congtyconghe");
	$conn->query("set names 'utf8'");


	$masanpham = $_POST['masanpham'];

	$tensanpham = $_POST['tensanpham'];
	//$anhKhachHang = $_POST['anhKhachHang'];
	$gia = $_POST['gia'];
	$loai = $_POST['loai'];
	
	$mota = $_POST['mota'];



	$cmd2 = "UPDATE sanpham
	SET tensanpham= '$tensanpham' /*, anhDocGia = 'Image'*/, gia ='$gia',loai='$loai',mota = '$mota' 
	WHERE masanpham = '$masanpham'";


	if ($conn->query($cmd2) === TRUE) {
	  echo "Done";
	} else {
	  echo "Error updating record: " . $conn->error;
	}





	$conn->close();
?>