<?php


if($_SERVER['REQUEST_METHOD']=='GET'){

    include '../config/config.php'; 

    $maKhachHang = $_GET['maKhachHang'];


    $sql = "SELECT donhang.maDonHang, shop.maShop, chitietdonhang.masanpham, sanpham.tensanpham,sanpham.hinhanh, chitietdonhang.tien, chitietdonhang.soLuong, donhang.maKhachHang, donhang.tenKhachHang, donhang.diaChi, donhang.sdt, donhang.tongTien, donhang.trangThai 
        FROM chitietdonhang, sanpham, tblkhachhang , shop , donhang
        WHERE sanpham.maShop = shop.maShop AND shop.maKhachHang = tblkhachhang.maKhachHang AND sanpham.masanpham = chitietdonhang.masanpham AND chitietdonhang.maDonHang = donhang.maDonHang AND sanpham.maShop = shop.maShop AND shop.maKhachHang = '$maKhachHang'
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