<?
include 'checkadmin.php';
include 'database.php';
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>Neptun Roboter Simulation</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="style.css">

</HEAD>
<BODY>
<h3>Neptun Roboter Simulation</h3>
<center>
	<? include 'menue.php'; ?>
</center>
<p>
<h3>Logeinträge abfragen</h3>
<?
$USERNAME=$_POST["username"];

echo "<form action='$PHP_SELF?userid=".$_REQUEST["userid"]."' method='post'>\n";

    $sql = 'SELECT * FROM `users` ORDER BY `username` ASC';
        /* fetching result from querry*/
    $result = MYSQL_QUERY($sql);
        /* How many rows in the result? */
    $number = MYSQL_NUMROWS($result);
    if($number==0)
	    echo "Keine Benutzer eingetragen!";
    else
		echo $number." Benutzer eingetragen &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;wähle Benutzername:&nbsp;";


	echo "<select name ='username'>\n";

    $i = 0;
    while ($i < $number)
    {
      $id = mysql_result($result, $i, 'id');
      $username = mysql_result($result, $i, 'username');
      if($_POST["username"]==$id)
      	echo "\t\t\t<option selected value=\"$id\">$username</option>\n";
      else
      	echo "\t\t\t<option value=\"$id\">$username</option>\n";
      $i++;
    }
?>
	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type='submit' value='abfragen' name='abfragen'>
</form>
<?
	if($_POST["abfragen"])
	{
		$USER=$_POST["username"];

		$sql ="SELECT * FROM `users` WHERE `id`=".mysql_escape_string($USER)."";
		$result = MYSQL_QUERY($sql);
		$result_ = mysql_fetch_array($result);

		echo "Logeinträge für Benutzer <b>".$result_["username"]."</b><p>";

		$sql ="SELECT * FROM `usage_log` WHERE `user_id`='$USER'";

		$result = MYSQL_QUERY($sql);
    	$number = MYSQL_NUMROWS($result);
    	if($number==0)
	    	echo "Keinen Eintrag gefunden!";
    	else
    	{
			echo $number." Einträge gefunden<p>\n";
			echo "<center>";
			echo "<table>";
			$i = 0;
			$old_level ="";
    		while ($i < $number)
    		{
      			//$id = mysql_result($result, $i, 'id');
      			$time = mysql_result($result, $i, 'time');
      			$entry = mysql_result($result, $i, 'entry');
      			$level = mysql_result($result, $i, 'level');
      			$i++;

      			if($old_level!=$level)
      			{
	      			echo "</table>";
	      			echo "<h3>".$level."</h3>";
	      			echo "<table>";
	      			$old_level=$level;
	      		}

      			echo "<tr><td valign='top'>";
      			echo "<b>".$level."</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n";
      			echo "<td valign='top'>".$time."&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n";
      			echo "<td>".nl2br(utf8_decode($entry))."</td>\n";
      			echo "</tr>";
			}
			echo "</table>";
			echo "</center>";
		}
	}
?>
<? include 'version.php'; ?>
</BODY>
</HTML>
