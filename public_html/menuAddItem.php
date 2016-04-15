<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'conn.php';	
	menuAddItem();
}

function menuAddItem(){
	global $connect;
	
	$food_item_name= $_POST["food_item_name"];
	$food_item_price= $_POST["food_item_price"];
	$food_item_description= $_POST["food_item_description"];
	//$restaurant_id= $_POST["restaurant_id"];
	$availability= $_POST["availability"];
	
	$query= "Insert into fooditem(food_item_name,food_item_price,food_item_description,restaurant_id,availability) values('$food_item_name','$food_item_price','$food_item_description',10011,{$availability});";
	
	mysqli_query($connect,$query) or die (mysqli_error($connect));
	mysqli_close($connect);
}




?>