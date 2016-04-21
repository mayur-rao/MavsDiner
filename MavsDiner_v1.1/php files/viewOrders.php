<?php
//echo "hello";
if($_SERVER["REQUEST_METHOD"]=="GET"){
	require 'conn.php';	
	viewOrders();
}

function viewOrders(){
	global $connect;
	
	//$query = "select sum(c.quantity) as total_quantity, o.order_id from Cart c, Orders o where o.order_id=c.order_id and o.restaurant_id =10011 group by o.order_id";
	$restaurant_id = $_GET['restaurant_id'];
	$query = "select sum(c.quantity) as total_quantity, o.order_id from Cart c, Orders o where o.order_id=c.order_id and o.order_status=0 and o.restaurant_id =$restaurant_id group by o.order_id";
	
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