<?php
    $conn = mysqli_connect("localhost","id12342493_root","root007","id12342493_android");
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