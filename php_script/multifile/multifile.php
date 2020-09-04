<?php
    $conn = mysqli_connect("localhost","root","","api_okhttp");

	$target1="uploads/".basename($_FILES['files1']['name']);
	$target2="uploads/".basename($_FILES['files2']['name']);
	$target3="uploads/".basename($_FILES['files3']['name']);

	if(isset($_REQUEST['submit'])){
		move_uploaded_file($_FILES['files1']['tmp_name'],$target1);
		move_uploaded_file($_FILES['files2']['tmp_name'],$target2);
		move_uploaded_file($_FILES['files3']['tmp_name'],$target3);
		$query = "INSERT INTO multifile_create (GalleryFile, CameraFile, AllFile) values ('$target1', '$target2','$target3')";
		if(mysqli_query($conn,$query)){
					
		}
	}

?>