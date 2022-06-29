<?php
$conn=mysqli_connect('localhost','root','','congtyconghe');
mysqli_query($conn,"SET NAMES'utf8'");
$masanpham = $_POST['masanpham'];
$tensanpham=$_POST['tensanpham'];
$hinhanh=$_POST['hinhanh'];
$soluong = $_POST['soluong'];
$tien = $_POST['tien'];
$query = "INSERT INTO giohang VALUES('$masanpham','$tensanpham','$hinhanh','$soluong','$tien')";
if(mysqli_query($conn,$query)){
	echo "done";
} else{
	echo "error";
}
?>