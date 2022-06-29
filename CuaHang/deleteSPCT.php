<?php

	$conn=mysqli_connect("localhost","root","","congtyconghe");
	$conn->query("set names 'utf8'");

	if ($conn->connect_error) {
	  die("Connection failed: " . $conn->connect_error);
	}

	$masanpham = $_POST['masanpham'];


	$sql="DELETE FROM sanpham WHERE masanpham = '$masanpham'";
	
	$result = $conn->query($sql);

	if ($result === TRUE) {
	  echo "Done";
	} else {
	  echo "Error deleting record: " . $conn->error;
	}



	$conn->close();

?>