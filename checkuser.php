<?
session_name ("NeptunRobot");
session_start();
if (!isset ($_SESSION["user_username"]))
{
  header ("Location: index.php");
}
else if ($_REQUEST["userid"] != $_SESSION["user_id"])
{
	header ("Location: index.php");
}
?>
