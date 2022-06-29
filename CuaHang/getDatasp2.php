<?php
 class SanPham{
 	public $MaSp, $TenSanPham, $Gia, $Loai,$HinhAnh, $MoTa;

 	function SanPham($masp, $tensanpham, $gia, $loai, $hinhanh, $mota){
 		$MaSp=$masp;
 		$TenSanPham=$tensanpham;
 		$Gia=$gia;
 		$Loai=$loai;
 		$HinhAnh=$hinhanh;
 		$MoTa=$mota;
 	}
 }

 $ketnoi=mysqli_connect("localhost", "root", "", "congtyconghe");

 mysqli_query($ketnoi, "set names 'utf8'");

 $truyvan="select * from sanpham";

 $data=mysqli_query($ketnoi,$truyvan);


 $mangSanPham= array();

 while($dong=mysqli_fetch_assoc($data)){
 	array_push($mangSanPham, ["masp"=>$dong['masp'], "tensanpham"=>$dong['tensanpham'],"gia"=>$dong['gia'],"loai"=>$dong['loai'],"hinhanh"=>$dong['hinhanh'],"mota"=>$dong['mota']]);
 }

 echo json_encode($mangSanPham);

 ?>