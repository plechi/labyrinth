<?php include 'checkuser.php'; ?> <!--check for right login -->
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
<table align='center' width="900">
	<tr>
		<td align='right'>
			<? include 'menue.php'; ?>
		</td>
	</tr>
	<tr>
		<td>
			<P>
			  <APPLET code="gui/TUGLabyrinth.class" width="950" height="580" mayscript="true" archive="labyrinth.jar">
			  <PARAM name="cabbase" value="labyrinth.cab">
			  <PARAM name="userid" value="<?php echo $_REQUEST["userid"] ?>" >
			  <PARAM name="logging" value="4095" >
			  <PARAM name="version" value="<? include 'versionsnumber.php'; ?>" >
<?
if($_SESSION["admin"]=="true")
{
	//echo "			<PARAM name=\"typ\" value=\"exam\" >\n";
	echo "			<PARAM name=\"typ\" value=\"normal\" >\n";
	echo "			<PARAM name=\"abs_filename\" value=\"config/games/Game.xml\">\n";
}
else
{
	//echo "			<PARAM name=\"typ\" value=\"exercise\" >\n";
	echo "			<PARAM name=\"typ\" value=\"normal\" >\n";
	echo "			<PARAM name=\"abs_filename\" value=\"config/games/Game.xml\">\n";
}
?>
			  </APPLET>
			 <? include 'version.php'; ?>
			</P>
			<b>Kontakt: </b><a href="http://einspiel.net/">Wolfgang Slany</a>
		</td>
	</tr>
</table>
</center>
</BODY>
</HTML>
