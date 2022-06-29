<?php

    require "connect.php";
    $tensanpham = $_POST['tensanpham'];
    $gia = $_POST['gia'];
    $loai = $_POST['loai'];
    $hinhanh = $_POST['hinhanh'];
    $mota = $_POST['mota'];
    $maLoaiSP = $_POST['maLoaiSP'];
    $maShop = $_POST['maShop'];
    

    if (strlen($tensanpham)>0 && strlen($gia)>0 && strlen($loai)>0 && strlen($hinhanh)>0 && strlen($mota)>0 && strlen($maLoaiSP)>0 && strlen($maShop)>0 ) {

        $query = "INSERT INTO sanpham VALUES (null,'$tensanpham','$gia','$loai','$hinhanh','$mota','$maLoaiSP','$maShop')";
        $data  = mysqli_query ($con,$query);
        if($data){
            echo "Success";

        }else{
            echo "Fail";

        }
    }else{
        echo"Null";

    }

    

?>