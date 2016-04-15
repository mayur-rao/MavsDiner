<?php

define('hostname','localhost');
define('user','sxr5833');
define('password','Ab112233');
define('databaseName','sxr5833');

$connect= mysqli_connect(hostname,user,password,databaseName);
//$connect= mysql_connect(hostname,user,password,databaseName,$port,$socket);
if (mysqli_connect_errno()) {
     		printf("Connect failed: %s\n", mysqli_connect_error());
     		exit();
  	}
?>