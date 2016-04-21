<?php
//echo "hello";
if($_SERVER["REQUEST_METHOD"]=="GET"){
	require 'conn.php';	
	viewOrdersList();
}

function viewOrdersList(){
	global $connect;
	
	//$order_id= $_POST['order_id'];
	
	//$query = "select f.food_item_id,f.food_item_name,c.quantity,c.order_id, c.restaurant_id from cart c,fooditem f where c.food_item_id=f.food_item_id and c.restaurant_id='10011' and c.order_id= '{$order_id}';";
	
	$restaurant_id = $_GET['restaurant_id'];
	$clicked_order_id=$_GET['clicked_order_id'];
	
	$query = "select f.food_item_id,f.food_item_name as food_item_name,c.quantity as quantity,c.order_id as order_id, c.restaurant_id from Cart c,Food_Item f where c.food_item_id=f.food_item_id and c.restaurant_id=$restaurant_id and order_id= $clicked_order_id;";
	
	$result= mysqli_query($connect,$query);
	$number_of_rows= mysqli_num_rows($result);
	
	$temp_array= array();
	
	if($number_of_rows>0){
		while($row= mysqli_fetch_assoc($result)){
			$temp_array[]= $row;
		}
	}
	
	//header('Content-type: aplication/json');
	//echo json_encode(array("fooditem"=>$temp_array));
	echo json_encode($temp_array);
	
	mysqli_close($connect);
}


?>