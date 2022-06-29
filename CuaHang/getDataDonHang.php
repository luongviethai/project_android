<?php


if($_SERVER['REQUEST_METHOD']=='GET'){

    include 'config/config.php'; 

    //$maKhachHang = $_GET['maKhachHang'];


     $sql = "SELECT donhang.maDonHang, chitietdonhang.masanpham, sanpham.tensanpham,sanpham.hinhanh, chitietdonhang.soLuong, chitietdonhang.tien, donhang.maKhachHang, donhang.tenKhachHang, donhang.diaChi, donhang.sdt, donhang.tongTien, donhang.trangThai
            FROM donhang, chitietdonhang, sanpham, tblkhachhang 
            WHERE donhang.maDonHang = chitietdonhang.maDonHang AND donhang.maKhachHang = tblkhachhang.maKhachHang AND chitietdonhang.masanpham = sanpham.masanpham 
            ORDER BY donhang.maDonHang DESC;

            ";

     $data=mysqli_query($conn,$sql);
     $mangGiaohang= array();
     while($dong=mysqli_fetch_assoc($data)){
        array_push($mangGiaohang, [
            "maDonHang"=>$dong['maDonHang'],
            "masanpham"=>$dong['masanpham'],
            "tensanpham"=>$dong['tensanpham'],
            "hinhanh"=>$dong['hinhanh'],
            "soLuong"=>$dong['soLuong'],
            "gia"=>$dong['tien'],
            "maKhachHang"=>$dong['maKhachHang'],
            "tenKhachHang"=>$dong['tenKhachHang'],
            "diaChi"=>$dong['diaChi'],
            "sdt"=>$dong['sdt'], 
            "tien"=>$dong['tongTien'],
            "trangThai"=>$dong['trangThai']
        ]);
     }
     echo json_encode($mangGiaohang);

 }
 ?>