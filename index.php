<?php

$message = "";

if( isset($_POST['password']) )
{
  if(strcmp($_POST['password'], "123456") != 0){
    $message = 'Incorrect!';
  } else {
    header('Location: ' . 'success.html');
  }
}
 ?>
 <!DOCTYPE html>
<html>
<head>
<title>Victim Page</title>
</head>

<body style="text-align: center; font-family: Arial">
<h1>Group 10 Form Login</h1>

<form method="post" action="index.php" name="login">
<label>Username: </label><input name="username" type="text" placeholder="Username"><br>
<label>Password: </label><input type="password" name="password">
<br>
<input name="submit" type="submit" value="Submit">
</form>
<?php echo $message; ?>


</body>
</html>
