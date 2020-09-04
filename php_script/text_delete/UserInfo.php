<?php

class UserInfo{
    private $connection;
    private $table = 'text_create';

    public $Product;

    public function __construct($db){
        $this->connection = $db;
    }

    public function delete_data(){
        $query = 'DELETE FROM ' . $this->table . ' WHERE Product = :Product';
        
        $stmt = $this->connection->prepare($query);
        
        $Product = htmlspecialchars(strip_tags($this->Product));
        
        $stmt->bindParam(':Product', $Product);

        if($stmt->execute()){
            return true;
        }else{
            return false;   
        }   
    }
}

?> 