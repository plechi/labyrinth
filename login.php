<?
session_name ("NeptunRobot");
session_start ();

include 'database.php';

$error=0;

if($_REQUEST["adminname"]!="" && $_REQUEST["adminpwd"]!="")
{
	$sql_ = "SELECT * FROM users WHERE (username like '".$_REQUEST["adminname"]."') AND (password = '".md5 ($_REQUEST["adminpwd"])."')";

	$result_ = mysql_query($sql_);

	if (mysql_num_rows($result_) > 0)
	{
   		//Benutzerdaten in ein Array auslesen.
  		$data_ = mysql_fetch_array ($result_);

		if($data_["type"]=="1")
		{
	   		//Sessionvariablen erstellen und registrieren
	  		$_SESSION["admin"] = "true";
		}
		else
		{
			  $error=2;
		}

	}
	else
	{
	  $error=3;
	}

}


$sql = "SELECT ".
    "* ".
  "FROM ".
    "users ".
  "WHERE ".
    "(username like '".$_REQUEST["name"]."') AND ".
    "(password = '".md5 ($_REQUEST["pwd"])."')";

$result = mysql_query($sql);

if (mysql_num_rows($result) > 0)
{
   //Benutzerdaten in ein Array auslesen.
  $data = mysql_fetch_array ($result);

   //Sessionvariablen erstellen und registrieren
  $_SESSION["user_username"] = $data["username"];
  $_SESSION["user_id"] = $data["id"];
  $_SESSION["user_type"] = $data["type"];

}
else
{	
	$error=1;
}

mysql_close();

if($error==0)
{
	header ("Location: robot.php?userid=".$data["id"]);
}
else
{
	header ("Location: index.php?fehler=$error");
}
?>
