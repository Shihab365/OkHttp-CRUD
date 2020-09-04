<?php

class UserInfo{
    private $connection;
    private $table = 'multifile_create';

    public $AllFile;

    public function __construct($db){
        $this->connection = $db;
    }

    public function read_single_pdf(){
        $query = 'SELECT AllFile FROM ' . $this->table . ' WHERE ID = ?';

        $stmt = $this->connection->prepare($query);
        $stmt->bindParam(1,$this->ID);
        $stmt ->execute();
        $row=$stmt->fetch(PDO::FETCH_ASSOC);

        $this->AllFile = $row['AllFile'];
    }
}

?> 