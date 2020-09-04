<?php
	$conn = mysqli_connect("localhost","root","","api_okhttp");

	if(isset($_POST['submit'])){
		$sqlUpdate = "UPDATE image_create SET Description = '$_POST[Description]' WHERE Name = '$_POST[Name]'";

		$pic = "SELECT Photo FROM image_create WHERE Name = '$_POST[Name]'";
		$rsPic = mysqli_query($conn,$pic);
        $rowPic = mysqli_fetch_assoc($rsPic);

		mysqli_query($conn,$sqlUpdate);
		
		if($_FILES['files']['name']){
			unlink($rowPic['Photo']);
			$picName = "uploads/".$_FILES['files']['name'];

			move_uploaded_file($_FILES['files']['tmp_name'], $picName);
			$picUpdate = "UPDATE image_create SET Photo = '$picName' WHERE Name = '$_POST[Name]'";
			mysqli_query($conn,$picUpdate);
		}

	}

?>