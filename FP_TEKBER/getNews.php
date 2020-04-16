<?php 
	require_once('connection.php');

	$sql = "SELECT * FROM `news_article`";
	
	$result = array();
	$r = mysqli_query($database, $sql);

	while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {
		array_push($result,array(
			"id_news" => $row['id_news'],
			"title_news" => $row['title_news'],
			"content_news" => $row['content_news'],
			"date" => $row['date']));
	};
 
	//Menampilkan dalam format JSON
	echo json_encode($result);
	
	mysqli_close($database);
?>