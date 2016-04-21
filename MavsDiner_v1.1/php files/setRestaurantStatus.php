<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'conn.php';	
	setRestaurantStatus();
}

function setRestaurantStatus(){
	global $connect;
	
	$restaurant_id= $_POST["restaurant_id"];
	$status= $_POST["status"];
	
	$query= "update Restaurant set status = '{$status}' where restaurant_id = {$restaurant_id}";
	
	mysqli_query($connect,$query) or die (mysqli_error($connect));
	mysqli_close($connect);
}




?>