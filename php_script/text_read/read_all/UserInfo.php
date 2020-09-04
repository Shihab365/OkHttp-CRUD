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

    public function read_all_data(){
        $query = 'SELECT * FROM ' . $this->table;

        $stmt = $this->connection->prepare($query);
        $stmt->execute();

        return $stmt;
    }
}

?> 