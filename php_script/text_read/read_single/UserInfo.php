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

    public function read_single_data(){
        $query = 'SELECT Product,Company,Address,Email,Phone FROM ' . $this->table . ' WHERE Product = ?';

        $stmt = $this->connection->prepare($query);
        $stmt->bindParam(1,$this->Product);
        $stmt ->execute();
        $row=$stmt->fetch(PDO::FETCH_ASSOC);

        $this->Product = $row['Product'];
        $this->Company = $row['Company'];
        $this->Address = $row['Address'];
        $this->Email = $row['Email'];
        $this->Phone = $row['Phone'];
    }
}

?> 