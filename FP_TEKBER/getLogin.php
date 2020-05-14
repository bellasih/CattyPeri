<?php 
    require_once('connection.php');
    
    $username = $_GET['user'];
    $password = $_GET['password'];

	$sql = "SELECT `username` FROM `users` 
            WHERE `username` LIKE '$username' AND `password` LIKE '$password'";
	
    $result = array();
    $status = array();
	$r = mysqli_query($database, $sql);

	while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {
		array_push($result,array(
			"user" => $row['username']));
    };

    count($result) == 1? array_push($status,array("total" => "1")) : array_push($status,array("total" => "0"));
 
	//Menampilkan dalam format JSON
	echo json_encode($status);
	
	mysqli_close($database);
?>