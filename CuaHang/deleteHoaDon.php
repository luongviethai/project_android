<?php

	$conn=mysqli_connect("localhost","root","","congtyconghe");
	$conn->query("set names 'utf8'");

	if ($conn->connect_error) {
	  die("Connection failed: " . $conn->connect_error);
	}

	$id = $_POST['id'];


	$sql="DELETE FROM hoadon WHERE id = '$id'";
	
	$result = $conn->query($sql);

	if ($result === TRUE) {
	  echo "Done";
	} else {
	  echo "Error deleting record: " . $conn->error;
	}



	$conn->close();

?>