<?php
//echo "hello";
if($_SERVER["REQUEST_METHOD"]=="GET"){
	require 'conn.php';	
	vendorDashboard();
}

function vendorDashboard(){
	global $connect;
	
	$restaurant_id = $_GET['restaurant_id'];
	
	$query= "select restaurant_name from Restaurant where restaurant_id = $restaurant_id";
	
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