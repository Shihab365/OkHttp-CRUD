<?php
    $conn = mysqli_connect("localhost","root","","api_okhttp");

    $Name = $_POST['Name'];
    $Description = $_POST['Description'];
	$target="uploads/".basename($_FILES['files']['name']);

	if(isset($_REQUEST['submit'])){
		if(move_uploaded_file($_FILES['files']['tmp_name'],$target)){
			$query = "INSERT INTO image_create (Name, Description, Photo) values ('$Name', '$Description', '$target')";
			if(mysqli_query($conn,$query)){
					
			}
		}
		else{
			
		}
}

?>