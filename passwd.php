<?
include 'checkuser.php';
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

<table border="0" cellpadding="0" cellspacing="0" align='center' valign='middle'>
	<tr>
		<td align="right" valign="middle">
			<? include 'menue.php'; ?>
			<form action="passwd_change.php" method="post">
				<input type="hidden" name="userid" value="<?echo $_REQUEST["userid"]?>">
				<table border="0" cellpadding="10" cellspacing="0" bordercolor="#000000" bgcolor="#ffffff"
				style="border-top-width:2px; border-left-width:2px; border-right-width:2px;  border-bottom-width:2px; padding-top:1cm; padding-left:1cm; padding-right:1cm;">
				<tr>
					<td colspan="2">
						<center>
						<h2>
						Passwort ändern
						</h2>
						</center>
					</td>
				</tr>
				<?php
					if (isset ($_REQUEST["erfolg"]))
					{
					  echo "<tr><td colspan='2'>";
					  echo "<center><b>Password geändert!</b></center>";
					  echo "</td></tr>";
					}
					else if (isset ($_REQUEST["fehler3"]))
					{
					  echo "<tr><td colspan='2'>";
					  echo "<center><b>Zwei unterschiedliche Passwörter eingegeben!</b><br></center>";
					  echo "</td></tr>";
					}
					else if (isset ($_REQUEST["fehler5"]))
					{
					  echo "<tr><td colspan='2'>";
					  echo "<center><b>Kein Passwort eingegeben!</b></center>";
					  echo "</td></tr>";
					}
					else if (isset ($_REQUEST["fehler6"]))
					{
					  echo "<tr><td colspan='2'>";
					  echo "<center><b>Interner Fehler! Passwort konnte nicht geändert werden.<br>Bitte kontaktieren Sie den Systemadministrator.</b></center>";
					  echo "</td></tr>";
					}

$sql = "SELECT * FROM users WHERE (id like '".mysql_escape_string($_REQUEST["userid"])."')";

$result = mysql_query($sql);
$row = mysql_fetch_array($result);

				?>
				<tr>
 					<td width="50%" style="padding-left:2cm">
 						<b> Passwort:</b>
  					</td>
  					<!--<td>
  						<input type="text" name="pwd" size="20"><br>
  					</td>-->
  					<td width="50%">
 						<input type="password" name="pwd1" size="20" maxlength="32"><br>
					</td>
					</tr>
	 				<tr>
	 					<td width="50%" style="padding-left:2cm">
	 						<b> Passwort wiederholen:</b>
	  				</td>
	  				<td width="50%">
	 					<input type="password" name="pwd2" size="20" maxlength="32"><br>
					</td>
				</tr>
				<tr>
 					<td width="50%" style="padding-left:2cm">
 						<b> Name:</b>
  				</td>
  				<td width="50%">
 						<input type="text" name="fullname" size="50"  maxlength="100" value="<? echo $row["full_name"]; ?>"><br>
					</td>
				</tr>
				<tr>
 					<td width="50%" style="padding-left:2cm">
 						<b> E-mail:</b>
  				</td>
  				<td width="50%">
 						<input type="text" name="email" size="50" maxlength="100" value="<? echo $row["email"]; ?>"><br>
					</td>
				</tr>
				<tr>
	  			<td>
	  			</td>
	  			<td>
  					<input type="submit" value="Submit">
					</td>
				</tr>
			  </table>
			  </form>
		  </td>
	  </tr>
	  <tr>
			<td colspan="2" align="center" style="padding-bottom:1cm">
			<? include 'version.php'; ?>
		    <b>Cookies müssen für diese Seite erlaubt sein!</b>
		  </td>
	  </tr>
  </table>

</center>
</BODY>
</HTML>
