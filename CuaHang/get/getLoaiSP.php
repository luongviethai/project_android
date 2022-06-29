<?php

	    include '../config/config.php';


	$sql="SELECT * 
	FROM loaisanpham
	ORDER BY maLoaiSP DESC";
	
	$result = $conn->query($sql);

	$mangKetQua = array();
	if ($result->num_rows > 0) {
		while ($row = $result->fetch_assoc()) {
			array_push($mangKetQua,[
				"maLoaiSP"=>$row['maLoaiSP'], 
				"tenLoaiSP"=>$row['tenLoaiSP']
			]);
		}
	}

	echo json_encode($mangKetQua);

	$conn->close();


?>