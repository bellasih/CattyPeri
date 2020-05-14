<?php 
	require_once('connection.php');
    
    $user       = $_GET['user'];
    $nominal    = $_GET['nominal'];
    $date       = date('Y-m-d');
    
    $sql = "INSERT INTO `donation` VALUES (null, null, '$user','$nominal', '$date')";
    $r   = mysqli_query($database, $sql);
    
    $msg = "Thank you for your donation : Rp".$nominal;
    $sql = "INSERT INTO `history` VALUES(null,'$user','$msg',0,'$date',null)";
	$r = mysqli_query($database, $sql);

	mysqli_close($database);
?>