<?php
 class GiaoHang{
 	public $KhuVuc, $TienShip;
 	function GioHang($khuvuc, $tienship){
 		$KhuVuc=$khuvuc;
 		$TenShip=$tienship;
 	}
 }
 $ketnoi=mysqli_connect("localhost", "root", "", "congtyconghe");
 mysqli_query($ketnoi, "set names 'utf8'");
 $truyvan="select * from diachigiaohang";
 $data=mysqli_query($ketnoi,$truyvan);
 $mangSanPham= array();
 while($dong=mysqli_fetch_assoc($data)){
 	array_push($mangSanPham, ["khuvuc"=>$dong['khuvuc'], "tienship"=>$dong['tienship']]);
 }
 echo json_encode($mangSanPham);
 ?>