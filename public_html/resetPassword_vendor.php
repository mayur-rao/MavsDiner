<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'conn.php';	
	reset();
}

function reset(){
	global $connect;
	
	$email= $_POST["email"];
	$oldPassword= $_POST["oldPassword"];
	$newPassword = $_POST["newPassword"];
	
	$query= "SELECT email_id FROM Restaurant WHERE email_id = '$email' and password = '$oldPassword' limit 1";
	
	$result= mysqli_query($connect,$query);
	$number_of_rows= mysqli_num_rows($result);
	if($number_of_rows>0){
		$query1= "UPDATE Restaurant SET password = '$newPassword' WHERE email_id = '$email'";
		mysqli_query($connect,$query1);
		echo "query executed";
	}
	else{
		echo "Either email-id or password does not match";
	}

	mysqli_close($connect);
}

?>