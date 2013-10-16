<?
session_name ("NeptunRobot");
session_start ();

include 'checkadmin.php';
include 'database.php';

$sql = "SELECT ".

    "* ".

  "FROM ".

    "users ".

  "WHERE ".

    "(username like '".$_REQUEST["username"]."')";

$result = mysql_query($sql);

if (mysql_num_rows($result) > 0)
{
	mysql_close();
   //Username allready in use
  	header ("Location: createuser.php?userid=".$_REQUEST["userid"]."&fehler2=1");
}
else if($_REQUEST["pwd1"] != $_REQUEST["pwd2"])
{
	mysql_close();
  //passwords not the same
	header ("Location: createuser.php?userid=".$_REQUEST["userid"]."&fehler3=1");
}
else if ($_REQUEST["username"] == "")
{
	mysql_close();
  //username empty
	header ("Location: createuser.php?userid=".$_REQUEST["userid"]."&fehler4=1");
}
else if ($_REQUEST["pwd1"] == "")
{
	mysql_close();
  //password empty
	header ("Location: createuser.php?userid=".$_REQUEST["userid"]."&fehler5=1");
}
else
{
  if ($_REQUEST["admin"] == "on")
    $admin = 1;
  else
    $admin = 0;
  $sql = "INSERT INTO users (username, password, full_name, email, type) VALUES ('".
         $_REQUEST["username"]."','".md5($_REQUEST["pwd1"])."','".$_REQUEST["fullname"]."','".$_REQUEST["email"]."',".$admin.")";
  $result = mysql_query($sql);
  if (!$result)
	header ("Location: createuser.php?userid=".$_REQUEST["userid"]."&fehler6=1");
	
  mysql_close();
  
  header ("Location: createuser.php?userid=".$_REQUEST["userid"]."&erfolg=1");

}


?>
