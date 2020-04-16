<?php 
	require_once('connection.php');

	$sql = "SELECT * FROM `cats_adoption`";
	
	$result = array();
	$r = mysqli_query($database, $sql);

	while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {
		array_push($result,array(
			"id_adoption" => $row['id_adoption'],
			"id_cat" => $row['id_cat'],
			"adopter" => $row['adopter'],
			"date_adopt" => $row['date_adopt']));
	};
 
	//Menampilkan dalam format JSON
	echo json_encode($result);
	
	mysqli_close($database);
?>