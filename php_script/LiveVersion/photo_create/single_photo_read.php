<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');

include_once 'Database.php';
include_once 'UserInfo.php';

$database = new Database();
$db = $database->connect();
$user_info = new UserInfo($db);

$user_info->Name=isset($_POST['Name']) ? $_POST['Name'] : die();
$user_info->read_single_data();
$user_info_array['data'] = array(
	'Name'=>$user_info->Name,
    'Photo'=>$user_info->Photo
);

print_r (json_encode($user_info_array));

?> 