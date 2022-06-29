<?php

	$conn=mysqli_connect("localhost","root","","congtyconghe");
	$conn->query("set names 'utf8'");

	if ($conn->connect_error) {
	  die("Connection failed: " . $conn->connect_error);
	}

?>