<?php
 class GioHang{
 	public $MaSanPham, $TenSanPham,$HinhAnh, $SoLuong,$Tien;

 	function GioHang($masanpham, $tensanpham, $hinhanh, $soluong, $tien){
 		$MaSanPham=$masanpham;
 		$TenSanPham=$tensanpham;
 		$HinhAnh=$hinhanh;
 		$SoLuong=$soluong;
 		$Tien=$tien;
 	}
 }

 $ketnoi=mysqli_connect("localhost", "root", "", "congtyconghe");

 mysqli_query($ketnoi, "set names 'utf8'");

 $truyvan="select * from giohang";

 $data=mysqli_query($ketnoi,$truyvan);


 $mangGioHang= array();

 while($dong=mysqli_fetch_assoc($data)){
 	array_push($mangGioHang, ["masanpham"=>$dong['masanpham'], "tensanpham"=>$dong['tensanpham'],"hinhanh"=>$dong['hinhanh'],"soluong"=>$dong['soluong'],"tien"=>$dong['tien']]);
 }

 echo json_encode($mangGioHang);

 ?>