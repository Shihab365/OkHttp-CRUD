<?php 

include_once 'Database.php';
include_once 'UserInfo.php';

$database = new Database();
$db = $database->connect();
$user_info = new UserInfo($db);

$data = json_decode(file_get_contents("php://input"));

$user_info->Product = $_POST['Product'];
$user_info->Company = $_POST['Company'];
$user_info->Address = $_POST['Address'];
$user_info->Email = $_POST['Email'];
$user_info->Phone = $_POST['Phone'];

if($user_info->insert_data()){
    //echo json_encode(array('message'=>'Successfully Data Inserted'));
}else {
    //echo json_encode(array('message'=>'Data Insert failed'));
}

?>