<?php 
	require_once('connection.php');

	$username=$_GET['user'];
	if(isset($_GET['id_notif'])){
		$id = $_GET['id_notif'];
		$sql = "UPDATE `history` SET `isread` = 1 WHERE `id_notif` = '$id'"; 
	}
	else{
		$sql = "SELECT * FROM `history` WHERE `user` LIKE '$username' AND `isread` = 0";
	}
	
	$result = array();
	$r = mysqli_query($database, $sql);

	if(!isset($_GET['id_notif'])){
		while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {
			array_push($result,array(
				"id_history" => $row['id_notif'],
				"user" => $row['user'],
				"msg" => $row['msg'],
				"date" => $row['date_notif']));
		};
		
		//Menampilkan dalam format JSON
		echo json_encode($result);
	}
	
	mysqli_close($database);
?>