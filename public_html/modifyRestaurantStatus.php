<?php
//echo "hello";
if($_SERVER["REQUEST_METHOD"]=="GET"){
	require 'conn.php';	
	modifyRestaurantStatus();
}

function modifyRestaurantStatus(){
	global $connect;
	
	$query= "select status from restaurant where restaurant_id = 10011";
	
	$result= mysqli_query($connect,$query);
	$number_of_rows= mysqli_num_rows($result);
	
	$temp_array= array();
	
	if($number_of_rows>0){
		while($row= mysqli_fetch_assoc($result)){
			$temp_array[]= $row;
		}
	}
	
	echo json_encode($temp_array);
	
	mysqli_close($connect);
}


?>