<?php
 class GiaoHang{
 	public $Id,$TenSanpham, $TenKhachHang,$DiaChi,$SDT,$Tien;
 	function GioHang($id, $tensanpham,$tenkhachhang,$diachi,$sdt,$tien){
 		$Id = $id;
 		$TenSanPham = $tensanpham;
 		$TenKhachHang=$tenkhachhang;
 		$DiaChi = $diachi;
 		$SDT =$sdt;
 		$Tien = $tien;
 	}
 }
 $ketnoi=mysqli_connect("localhost", "root", "", "congtyconghe");
 mysqli_query($ketnoi, "set names 'utf8'");
 $truyvan="select * from hoadon";
 $data=mysqli_query($ketnoi,$truyvan);
 $mangGiaohang= array();
 while($dong=mysqli_fetch_assoc($data)){
 	array_push($mangGiaohang, ["id"=>$dong['id'],"masanpham"=>$dong['masanpham'],"maKhachHang"=>$dong['maKhachHang'], "tensanpham"=>$dong['tensanpham'],"diachi"=>$dong['diachi'],"sdt"=>$dong['sdt'], "tien"=>$dong['tien']]);
 }
 echo json_encode($mangGiaohang);
 ?>