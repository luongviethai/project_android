<?php
$conn=mysqli_connect('localhost','root','','cuahang');
mysqli_query($conn,"SET NAMES'utf8'");
$query = "DELETE FROM sanpham";
if(mysqli_query($conn,$query)){
	echo "done";
} else{
	echo "error";
}
?>