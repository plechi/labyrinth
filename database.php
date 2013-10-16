<?
/* database name */
$dbname = "labyrinth";

/* make connection to database */
mysql_connect("localhost","username","secret_pwd") OR DIE("Unable to connect to database server");
@mysql_select_db($dbname) or die("Unable to select database");
?>
