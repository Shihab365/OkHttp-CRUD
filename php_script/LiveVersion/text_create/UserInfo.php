<?php

class UserInfo{
    private $connection;
    private $table = 'text_create';

    public $Product;
    public $Company;
    public $Address;
    public $Email;
    public $Phone;

    public function __construct($db){
        $this->connection = $db;
    }

    public function insert_data(){
        $query = 'INSERT INTO ' . $this->table . '
         SET
          Product = :Product, 
          Company = :Company, 
          Address = :Address, 
          Email = :Email, 
          Phone = :Phone';

        $stmt = $this->connection->prepare($query);
        
        $Product = htmlspecialchars(strip_tags($this->Product));
        $Company = htmlspecialchars(strip_tags($this->Company));
        $Address = htmlspecialchars(strip_tags($this->Address));
        $Email = htmlspecialchars(strip_tags($this->Email));
        $Phone = htmlspecialchars(strip_tags($this->Phone));
        
        $stmt->bindParam(':Product', $Product);
        $stmt->bindParam(':Company', $Company);
        $stmt->bindParam(':Address', $Address);
        $stmt->bindParam(':Email', $Email);
        $stmt->bindParam(':Phone', $Phone);

        if($stmt->execute()){
            return true;
        }else{
            return false;   
        }   
    }
}

?> 