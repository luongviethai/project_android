<?php

    require "connect.php";
    $tensanpham = $_POST['tensanpham'];
    $gia = $_POST['gia'];
   // $loai = $_POST['loai'];
    $hinhanh = $_POST['hinhanh'];
    $mota = $_POST['mota'];
   // $maLoaiSP = $_POST['maLoaiSP'];
    //$maShop = $_POST['maShop'];
    

    if (strlen($tensanpham)>0 && strlen($gia)>0 && strlen($hinhanh)>0 && strlen($mota)>0 ) {

        $query = "INSERT INTO sanphamcuatoi VALUES (null, '$tensanpham','$gia','$hinhanh','$mota)";
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