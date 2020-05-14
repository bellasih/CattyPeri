<?php 
	require_once('connection.php');
    
    	$founder = $_GET['user'];
    	$name_cat = $_GET['name_cat'];
    	$condition_cat = $_GET['condition_cat'];
    	$photo = $_GET['photo'];
    	$date_found = date('Y-m-d');
		$loc_found = $_GET['loc_found'];
		$penyakit = $_GET['penyakit'];
    

	$sql = "INSERT INTO `cats` (id_cat, name_cat, type_cat, condition_cat, photo, age, date_found,
                loc_found, founder, gender, penyakit, last_up, isadopt)VALUES (null, '$name_cat', 
                'Calico', '$condition_cat', '$photo', 0, '$date_found', '$loc_found', '$founder', 'Male',
                '$penyakit', '$date_found', null)";
	
	if(mysqli_query($database, $sql)){
        echo 'Data Submit';
    }
    else{
        echo 'Gagal';
    }

	mysqli_close($database);
?>