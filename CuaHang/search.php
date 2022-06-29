<?php
 class SanPham{
 	public $MaSanpPham, $TenSanPham, $Gia, $Loai,$HinhAnh, $MoTa;

 	function SanPham($masanpham, $tensanpham, $gia, $loai, $hinhanh, $mota){
 		$MaSanPham=$masanpham;
 		$TenSanPham=$tensanpham;
 		$Gia=$gia;
 		$Loai=$loai;
 		$HinhAnh=$hinhanh;
 		$MoTa=$mota;
 	}
 }

 $ketnoi=mysqli_connect("localhost", "root", "", "congtyconghe");

 mysqli_query($ketnoi, "set names 'utf8'");
 $LoaiDoAn = $_POST['loai'];

 $truyvan="select * from sanpham where loai LIKE '$LoaiDoAn%'";

 $data=mysqli_query($ketnoi,$truyvan);


 $mangSanPham= array();

 while($dong=mysqli_fetch_assoc($data)){
 	array_push($mangSanPham, ["masanpham"=>$dong['masanpham'], "tensanpham"=>$dong['tensanpham'],"gia"=>$dong['gia'],"loai"=>$dong['loai'],"hinhanh"=>$dong['hinhanh'],"mota"=>$dong['mota']]);
 }
 echo json_encode($mangSanPham);

 ?>