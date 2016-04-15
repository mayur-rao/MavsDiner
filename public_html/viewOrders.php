<?php
//echo "hello";
if($_SERVER["REQUEST_METHOD"]=="GET"){
	require 'conn.php';	
	viewOrders();
}

function viewOrders(){
	global $connect;
	
	$query = "select sum(quantity) as total_quantity, order_id from cart group by order_id";
	
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