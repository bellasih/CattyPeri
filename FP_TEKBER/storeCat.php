<?php 
	require_once('connection.php');
    
    	$founder = $_GET['username'];
    	$name_cat = $_GET['name_cat'];
    	$condition_cat = $_GET['condition_cat'];
    	$photo = $_GET['photo'];
    	$date_found = date('Y-m-d', time());
	$loc_found = $_GET['loc_found'];
	$gender = $_GET['gender'];
	$penyakit = $_GET['penyakit'];
    

	$sql = "INSERT INTO `cats` (id_cat, name_cat, type_cat, condition_cat, photo, age, date_found,
                loc_found, founder, gender, penyakit, last_up, isadopt)VALUES (null, '$name_cat', 
                null, '$condition_cat', '$photo', null, '$date_found', '$loc_found', '$founder', '$gender',
                '$penyakit', '$date_found', null)";
	
	if(mysqli_query($database, $sql)){
        echo 'Data Submit';
    }
    else{
        echo 'Gagal';
    }

	mysqli_close($database);
?>