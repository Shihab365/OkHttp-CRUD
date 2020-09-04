<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');

include_once 'Database.php';
include_once 'UserInfo.php';

$database = new Database();
$db = $database->connect();
$user_info = new UserInfo($db);

$result = $user_info->read_all_data();
$num = $result->rowCount();


if($num > 0){
    $user_info_array = array();
    $user_info_array['data'] = array();

    while($row = $result->fetch(PDO::FETCH_ASSOC)){
        extract($row);
        $user_data = array(
            'Product'=>$Product,
            'Company'=>$Company,
            'Address'=>$Address,
            'Email'=>$Email,
            'Phone'=>$Phone
        );
        array_push($user_info_array['data'],$user_data);
    }
    echo json_encode($user_info_array);
}else{
    echo json_encode(array('message'=>'no data found'));
}

?>