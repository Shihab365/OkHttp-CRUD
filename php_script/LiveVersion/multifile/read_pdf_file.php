<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');

include_once 'Database.php';
include_once 'UserInfo.php';

$database = new Database();
$db = $database->connect();
$user_info = new UserInfo($db);

$user_info->ID=isset($_POST['ID']) ? $_POST['ID'] : die();
$user_info->read_single_pdf();
$user_info_array['data'] = array(
	'AllFile'=>$user_info->AllFile
);

print_r (json_encode($user_info_array));

?> 