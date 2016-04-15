<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'conn.php';	
	login_customer();
}

function login_customer(){
	global $connect;
	
	$email= $_POST["email"];
	$password= $_POST["password"];
	
	$query= "SELECT customer_id FROM Customer WHERE email_id = '$email' AND password = '$password'";
	
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