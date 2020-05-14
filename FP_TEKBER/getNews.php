<?php 
	require_once('connection.php');

	$sql = "";
	if(isset($_GET['id_news'])){
		$id_news = $_GET['id_news'];
		$sql = "SELECT * FROM `news_article` WHERE `id_news` = '$id_news'";
	}
	else{
		$sql = "SELECT * FROM `news_article`";
	}
	
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