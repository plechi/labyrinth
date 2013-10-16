<a href='logout.html'>Logout</a>&nbsp;
<a href='robot.php?userid=<?echo $_REQUEST["userid"]?>'>zum Spiel</a>
&nbsp;&nbsp;<? echo "<a href='passwd.php?userid=".$_REQUEST["userid"]."'>Passwort ändern</a>\n"; ?>

<br>

<?
if ($_SESSION["user_type"] > 0)
{
	echo "<a href='createuser.php?userid=".$_REQUEST["userid"]."'>Benutzer erstellen</a>\n";
	echo "&nbsp;&nbsp;<a href='createusersfromlist_form.php?userid=".$_REQUEST["userid"]."'>Benutzer erstellen aus Datei</a>\n";
	echo "&nbsp;&nbsp;<a href='sql_userdata.php?userid=".$_REQUEST["userid"]."'>Log abfragen</a>\n";
}
?>