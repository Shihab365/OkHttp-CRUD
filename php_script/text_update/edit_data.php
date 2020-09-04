<?php 
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

include_once 'Database.php';
include_once 'UserInfo.php';

$database = new Database();
$db = $database->connect();
$user_info = new UserInfo($db);

$data = json_decode(file_get_contents("php://input"));

$user_info->Product = $_POST['Product'];
$user_info->Address = $_POST['Address'];
$user_info->Phone = $_POST['Phone'];

if($user_info->edit_data()){
    //echo json_encode(array('message'=>'Successfully Data edit'));
}else {
    //echo json_encode(array('message'=>'Data edit failed'));
}

?>