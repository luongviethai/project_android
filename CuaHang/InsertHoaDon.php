<?php
$conn=mysqli_connect('localhost','root','','congtyconghe');
mysqli_query($conn,"SET NAMES'utf8'");

$masanpham = $_POST['masanpham'];
$maKhachHang=$_POST['maKhachHang'];
$tensanpham=$_POST['tensanpham'];
//$tenkhachhang=$_POST['tenkhachhang'];

$diachi = $_POST['diachi'];
$sdt = $_POST['sdt'];
$tien = $_POST['tien'];

$query = "INSERT INTO hoadon VALUES(null,'masanpham','maKhachHang',$tensanpham','$diachi','$sdt','$tien')";
if(mysqli_query($conn,$query)){
	echo "done";
} else{
	echo "error";
}
?>