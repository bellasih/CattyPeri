<?php 
	require_once('connection.php');

	$sql = "SELECT * FROM `cats` WHERE `isadopt` IS NULL";
	
	$result = array();
	$r = mysqli_query($database, $sql);

	while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {
		array_push($result,array(
			"id_cat" => $row['id_cat'],
			"name_cat" => $row['name_cat'],
			"type_cat" => $row['type_cat'],
			"photo" => $row['photo'],
			"date_found" => $row['date_found']));
	};
 
	//Menampilkan dalam format JSON
	echo json_encode($result);
	
	mysqli_close($database);
?>