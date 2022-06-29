<?php


     include '../config/config.php'; 


 $truyvan="select * from sanpham where maLoaiSP = '3'";

 $data=mysqli_query($conn,$truyvan);


 $mangSanPham= array();

 while($dong=mysqli_fetch_assoc($data)){
    array_push($mangSanPham, [
        "maSP"=>$dong['maSP'], 
        "tenSP"=>$dong['tenSP'],
        "gia"=>$dong['gia'],
        "loai"=>$dong['loai'],
        "hinhAnh"=>$dong['hinhAnh'],
        "moTa"=>$dong['moTa'],
        "maLoaiSP"=>$dong['maLoaiSP'],
        "maShop"=>$dong['maShop']
    ]);
 }

 echo json_encode($mangSanPham);

 ?>