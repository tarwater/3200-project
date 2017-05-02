<?php

$message = "";

$username = "admin";
$password = "Aa";

if( isset($_POST['password']) )
{
  if(strcmp($_POST['password'], $password) != 0 || strcmp($_POST['username'], $username) != 0){
    $message = '<div id="message">Incorrect!</div>';
  } else {
    header('Location: success.html');
  }
}
 ?>
 <!DOCTYPE html>
<html>
<head>
<title>Victim Page</title>
<style>
#message{
color: red;
margin-top: 20px;
font-size: 2em;
}
</style>
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
