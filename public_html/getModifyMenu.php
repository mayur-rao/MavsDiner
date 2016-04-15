<?php
//echo "hello";
if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'conn.php';	
	getModifyMenu();
}

function getModifyMenu(){
	global $connect;
	
	$food_item_id= $_POST["food_item_id"];
	$food_item_name= $_POST["food_item_name"];
	$food_item_price= $_POST["food_item_price"];
	$food_item_description= $_POST["food_item_description"];
	$availability = $_POST["availability"];
	
	$query= "update fooditem set food_item_name = '{$food_item_name}', food_item_price = {$food_item_price}, food_item_description = '{$food_item_description}', availability = {$availability} where food_item_id = {$food_item_id}";
	
	mysqli_query($connect,$query) or die (mysqli_error($connect));
	mysqli_close($connect);
}


?>