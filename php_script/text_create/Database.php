<?php
class Database{
    private $host = 'localhost';
    private $db_name = 'api_okhttp';
    private $user_name = 'root';
    private $password = '';

    private $connection = '';

    // Database Connection
    public function connect(){
        $this->connection=null;

        try {
            $this->connection = new PDO('mysql:host=' . $this->host . ';dbname=' .$this->db_name, $this->user_name, $this->password);
            $this->connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch(PDOException $e){
            echo 'Connection Error: ' . $e->getMessage();
        }
        return $this->connection;
    }
}

?>