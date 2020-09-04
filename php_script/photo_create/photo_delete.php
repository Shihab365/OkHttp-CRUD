<?php
    $conn = mysqli_connect("localhost","root","","api_okhttp");
	if(isset($_POST['Name'])){

        $sqlPic = "SELECT Photo FROM image_create WHERE Name = '$_POST[Name]'";
        $rsPic = mysqli_query($conn,$sqlPic);
        $rowPic = mysqli_fetch_assoc($rsPic);
        unlink($rowPic['Photo']);

        $sqlDel = "DELETE FROM image_create WHERE Name='$_POST[Name]'";
        if(mysqli_query($conn,$sqlDel)){

        }

    }

?>