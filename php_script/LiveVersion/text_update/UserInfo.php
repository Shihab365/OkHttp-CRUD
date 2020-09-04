<?php

class UserInfo{
    private $connection;
    private $table = 'text_create';

    public $Product;
    public $Address;
    public $Phone;

    public function __construct($db){
        $this->connection = $db;
    }

    public function edit_data(){
        $query = 'UPDATE ' . $this->table . '
         SET Address = :Address, Phone = :Phone WHERE Product = :Product';
        
        $stmt = $this->connection->prepare($query);
        
        $Product = htmlspecialchars(strip_tags($this->Product));
        $Address = htmlspecialchars(strip_tags($this->Address));
        $Phone = htmlspecialchars(strip_tags($this->Phone));
        
        $stmt->bindParam(':Product', $Product);
        $stmt->bindParam(':Address', $Address);
        $stmt->bindParam(':Phone', $Phone);

        if($stmt->execute()){
            return true;
        }else{
            return false;   
        }   
    }
}

?> 