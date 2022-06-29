<?php
if($_SERVER['REQUEST_METHOD']=='GET'){

	include '../config/config.php';


		
	$maKhachHang = $_GET['maKhachHang'];


	$sql="SELECT tblkhachhang.maKhachHang, tblkhachhang.tenKhachHang, tblkhachhang.anhKhachHang, tblkhachhang.sdt, tblkhachhang.diaChi, tblkhachhang.tenTaiKhoan, tblkhachhang.matKhau
		FROM tblkhachhang
		WHERE maKhachHang = '$maKhachHang'";



	$result = $conn->query($sql);

	$mangDocGia = array();
	if ($result->num_rows > 0) {
		while ($row = $result->fetch_assoc()) {
			array_push($mangDocGia,[
			"maKhachHang"=>$row['maKhachHang'], 
			"tenKhachHang"=>$row['tenKhachHang'],
			"anhKhachHang"=>$row['anhKhachHang'],
			"sdt"=>$row['sdt'],
			"diaChi"=>$row['diaChi'],
			"tenTaiKhoan"=>$row['tenTaiKhoan'], 
			"matKhau"=>$row['matKhau']
			]);
	 	}
	}

	echo json_encode($mangDocGia);

		$conn->close();

}
?>