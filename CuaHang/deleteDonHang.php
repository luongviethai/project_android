<?php
$conn=mysqli_connect('localhost','root','','congtyconghe');
mysqli_query($conn,"SET NAMES'utf8'");
$masanpham = $_POST['masanpham'];
$query = "DELETE FROM giohang WHERE masanpham = '$masanpham'";
if(mysqli_query($conn,$query)){
	echo "done";
} else{
	echo "error";
}
?>