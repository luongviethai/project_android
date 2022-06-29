<?php
 class Giamgia{
 	public $MaGiamGia, $PhanTram;

 	function GiamGia($magiamgia, $phantram){
 		$MaGiamGia=$magiamgia;
 		$PhanTram=$phantram;
 	}
 }

 $ketnoi=mysqli_connect("localhost", "root", "", "congtyconghe");

 mysqli_query($ketnoi, "set names 'utf8'");

 $truyvan="select * from giamgia";

 $data=mysqli_query($ketnoi,$truyvan);


 $mangGiamGia= array();

 while($dong=mysqli_fetch_assoc($data)){
 	array_push($mangGiamGia, ["magiamgia"=>$dong['magiamgia'], "phantram"=>$dong['phantram']]);
 }

 echo json_encode($mangGiamGia);

 ?>