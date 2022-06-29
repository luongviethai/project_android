<?php

if($_SERVER['REQUEST_METHOD']=='GET'){


    require "../config/config.php";


    $maKhachHang = $_GET['maKhachHang'];


 $truyvan="SELECT giohang.masanpham, sanpham.tensanpham, sanpham.hinhanh, giohang.soLuong, giohang.tien
        FROM giohang, sanpham
        WHERE giohang.masanpham = sanpham.masanpham
        AND maKhachHang='$maKhachHang'
        ";

 $data=mysqli_query($conn,$truyvan);


 $mangGioHang= array();

 while($dong=mysqli_fetch_assoc($data)){
 	array_push($mangGioHang, [
        "masanpham"=>$dong['masanpham'], 
        "tensanpham"=>$dong['tensanpham'],
        "hinhanh"=>$dong['hinhanh'],
        "soLuong"=>$dong['soLuong'],
        "tien"=>$dong['tien']
    ]);
 }

 echo json_encode($mangGioHang);

}

 ?>