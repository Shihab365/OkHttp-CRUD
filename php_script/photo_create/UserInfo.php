<?php

class UserInfo{
    private $connection;
    private $table = 'image_create';

    public $Name, $Photo;

    public function __construct($db){
        $this->connection = $db;
    }

    public function read_single_data(){
        $query = 'SELECT Name, Photo FROM ' . $this->table . ' WHERE Name = ?';

        $stmt = $this->connection->prepare($query);
        $stmt->bindParam(1,$this->Name);
        $stmt ->execute();
        $row=$stmt->fetch(PDO::FETCH_ASSOC);

        $this->Name = $row['Name'];
        $this->Photo = $row['Photo'];
    }
}

?> 