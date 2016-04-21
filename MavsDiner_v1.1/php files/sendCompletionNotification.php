<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'conn.php';	
	setRestaurantStatus();
}

function setRestaurantStatus(){
	global $connect;
	
	$order_status= $_POST["order_status"];
	$restaurant_id = $_POST['restaurant_id'];
	$clicked_order_id= $_POST['order_id'];
	
	$query= "update Orders set order_status = {$order_status} where restaurant_id = {$restaurant_id} and order_id= {$clicked_order_id}";
	
	mysqli_query($connect,$query) or die (mysqli_error($connect));
	mysqli_close($connect);
}




?>