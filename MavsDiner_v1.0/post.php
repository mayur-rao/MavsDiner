<?php 
 
       $cart   = urldecode($_POST["cart"]);
       $filename="androidmessages.txt";
// write (append) the data to the file
file_put_contents($filename,$cart."<br />",FILE_APPEND);
// load the contents of the file to a variable
$androidmessages=file_get_contents($filename);
// display the contents of the variable (which has the contents of the file)
echo $androidmessages;
 
 ?>
