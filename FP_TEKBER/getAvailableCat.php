<?php 
	require_once('connection.php');

	$sql = "";

	if(isset($_GET['id_cat']) && isset($_GET['user'])){
		$id_cat = $_GET['id_cat'];
		$user = $_GET['user'];

		$sql = "SELECT `name_cat` FROM `cats`
				   WHERE `id_cat` = '$id_cat'";
		
		$result = "";
		$r = mysqli_query($database, $sql);

		while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {
			$result = $row['name_cat'];
		};

		$sql = "UPDATE `cats` SET `isadopt` = 1
				WHERE `id_cat` = '$id_cat'";
		$r = mysqli_query($database, $sql);
		$date = date('Y-m-d');

		$result .= " : Cat is adopted successfully by you .. Thank you :)";

		$sql = "INSERT INTO `cats_adoption` VALUES(null,'$id_cat','$user','$date')";
		$r = mysqli_query($database, $sql);

		$sql = "INSERT INTO `history` VALUES(null,'$user','$result',0,'$date','$id_cat')";
		$r = mysqli_query($database, $sql);
	}
	else if(isset($_GET['id_cat'])){
		$id_cat = $_GET['id_cat'];
		$sql = "SELECT * FROM `cats` WHERE `id_cat`='$id_cat'";
	}
	else{
		$sql = "SELECT * FROM `cats` WHERE `isadopt` IS NULL";
	}

	if(!isset($_GET['user'])){
		$result = array();
		$r = mysqli_query($database, $sql);

		while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {
			array_push($result,array(
				"id_cat" => $row['id_cat'],
				"name_cat" => $row['name_cat'],
				"type_cat" => $row['type_cat'],
				"age" => $row['age'],
				"loc_found" => $row['loc_found'],
				"condition_cat" => $row['condition_cat'],
				"photo" => $row['photo'],
				"date_found" => $row['date_found']));
		};
		//Menampilkan dalam format JSON
		echo json_encode($result);
	}
	
	mysqli_close($database);
?>