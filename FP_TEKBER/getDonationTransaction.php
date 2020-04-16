<?php 
	require_once('connection.php');

	$sql = "SELECT * FROM `donation`";
	
	$result = array();
	$r = mysqli_query($database, $sql);

	while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {
		array_push($result,array(
			"id_donation" => $row['id_donation'],
			"id_cat" => $row['id_cat'],
			"donator" => $row['donator'],
			"nominal" => $row['nominal'],
			"date_donation" => $row['date_donation']));
	};
 
	//Menampilkan dalam format JSON
	echo json_encode($result);
	
	mysqli_close($database);
?>