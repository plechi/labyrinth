<?
session_name ("NeptunRobot");
session_start ();

include 'checkuser.php';
include 'database.php';


if($_REQUEST["pwd1"] != $_REQUEST["pwd2"])
{
	mysql_close();
  //passwords not the same
	header ("Location: passwd.php?userid=".$_REQUEST["userid"]."&fehler3=1");
}
else if ($_REQUEST["pwd1"] == "")
{
	mysql_close();
  //password empty
	header ("Location: passwd.php?userid=".$_REQUEST["userid"]."&fehler5=1");
}
else
{
  $sql = "UPDATE users SET password='".md5($_REQUEST["pwd1"])."', full_name='".$_REQUEST["fullname"]."', email='".$_REQUEST["email"]."' WHERE ( id like '".$_SESSION["user_id"]."' )";
  $result = mysql_query($sql);

  mysql_close();

  if (!$result)
	header ("Location: passwd.php?userid=".$_REQUEST["userid"]."&fehler6=1");
  else
	header ("Location: passwd.php?userid=".$_REQUEST["userid"]."&erfolg=1");

}


?>
