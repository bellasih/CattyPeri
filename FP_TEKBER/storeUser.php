<?php 
	include('connection.php');
    
    $username = $_GET['user'];
    $name     =  $_GET['name'];
    $password = $_GET['password'];
    $email    = $_GET['email'];
    $jk       = $_GET['jk'];
    $hp       = $_GET['hp'];
    $address  = $_GET['address'];

	$sql = "INSERT INTO `users` VALUES ('$username', '$password', '$email', '$name', '$hp', null, '$jk', 0)";
	
	if(mysqli_query($database, $sql)){
        echo 'Data Submit';
    }
    else{
        echo 'Gagal';
    }

	mysqli_close($database);
?>