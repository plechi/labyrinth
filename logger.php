<?
include 'checkuser.php';
//session_name ("NeptunRobot");
//session_start ();

if (isset ($_SESSION["user_username"]) && ($_REQUEST["userid"] == $_SESSION["user_id"]) )
{
	include 'database.php';

	$user_id=$_REQUEST["userid"];
	$text=$_REQUEST["text"];
	$level=$_REQUEST["level"];

	$sql = "INSERT INTO usage_log (id, time, entry, user_id, level) values ('NULL', NOW(), '".$text."', ".$user_id.", '".$level."')";

	$result = mysql_query($sql);

	if (!$result)
	{
		echo "Error in SQL statement: ".$sql."\n";
		echo mysql_error();
	}

	mysql_close();
}
else
{
	echo "ERROR: ".$_SESSION["user_username"].", ".$_SESSION["user_id"].", ".$_REQUEST["userid"]."<br>\n";
}
?>
