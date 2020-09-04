<?php

class UserInfo1{
    private $connection;
    private $table = 'image_create';

    public $Name;
    public $Description;
    public $Photo;

    public function __construct($db){
        $this->connection = $db;
    }

    public function read_all_photo(){
        $query = 'SELECT * FROM ' . $this->table;

        $stmt = $this->connection->prepare($query);
        $stmt->execute();

        return $stmt;
    }
}

?> 