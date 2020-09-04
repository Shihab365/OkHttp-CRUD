<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');

include_once 'Database.php';
include_once 'UserInfo.php';

$database = new Database();
$db = $database->connect();
$user_info = new UserInfo($db);

$user_info->Product=isset($_POST['Product']) ? $_POST['Product'] : die();
$user_info->read_single_data();
$user_info_array['data'] = array(
    'Product'=>$user_info->Product,
    'Company'=>$user_info->Company,
    'Address'=>$user_info->Address,
    'Email'=>$user_info->Email,
    'Phone'=>$user_info->Phone
);

print_r (json_encode($user_info_array));

?> 