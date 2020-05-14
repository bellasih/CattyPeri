<?php 
	require_once('connection.php');

	$sql = "SELECT `id_cat`,`name_cat`,`loc_found`, `photo` FROM `cats` 
            WHERE `isadopt` IS NULL 
            LIMIT 5";
	
	$result = array();
	$r = mysqli_query($database, $sql);

	while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {
		array_push($result,array(
			"id_cat" => $row['id_cat'],
            "name_cat" => $row['name_cat'],
            "photo" => $row['photo'],
            "loc_found" => $row['loc_found']));
	};
 
	//Menampilkan dalam format JSON
	echo json_encode($result);
	
	mysqli_close($database);
?>