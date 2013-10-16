<?
session_name ("NeptunRobot");
session_start ();
include 'checkadmin.php' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>Neptun Roboter Simulation</title>
<link rel="stylesheet" href="style.css">
</head>

<body bgcolor="#eeeeee">
<h3>Neptun Roboter Simulation</h3>
<center>
<table border="0" cellpadding="0" cellspacing="0" align='center' valign='middle'>
	<tr>
		<td align="right" valign="middle">
			<? include 'menue.php'; ?>
			<form action="register.php" method="post">
				<input type="hidden" name="userid" value="<?echo $_REQUEST["userid"]?>">
				<table border="0" cellpadding="10" cellspacing="0" bordercolor="#000000" bgcolor="#ffffff"
				style="border-top-width:2px; border-left-width:2px; border-right-width:2px;  border-bottom-width:2px; padding-top:1cm; padding-left:1cm; padding-right:1cm;">
				<tr>
					<td colspan="2">
						<center>
						<h2>
						Eintragen
						</h2>
						</center>
					</td>
				</tr>
				<?php
					if (isset ($_REQUEST["erfolg"]))
					{
					  echo "<tr><td colspan='2'>";
					  echo "<center><b>Benutzer erfolgreich angelegt</b></center>";
					  echo "</td></tr>";
					}
					else if (isset ($_REQUEST["fehler2"]))
					{
					  echo "<tr><td colspan='2'>";
					  echo "<center><b>Benutzername wird bereits verwendet!<br>Bitte wählen Sie einen anderen.</b></center>";
					  echo "</td></tr>";
					}
					else if (isset ($_REQUEST["fehler3"]))
					{
					  echo "<tr><td colspan='2'>";
					  echo "<center><b>Zwei unterschiedliche Passwörter eingegeben!<br>Bitte versuchen Sie es noch einmal.</b></center>";
					  echo "</td></tr>";
					}
					else if (isset ($_REQUEST["fehler4"]))
					{
					  echo "<tr><td colspan='2'>";
					  echo "<center><b>Kein Benutzername eingegeben!<br>Bitte füllen Sie das Formular vollständig aus.</b></center>";
					  echo "</td></tr>";
					}
					else if (isset ($_REQUEST["fehler5"]))
					{
					  echo "<tr><td colspan='2'>";
					  echo "<center><b>Kein Passwort eingegeben!<br>Bitte füllen Sie das Formular vollständig aus.</b></center>";
					  echo "</td></tr>";
					}
					else if (isset ($_REQUEST["fehler6"]))
					{
					  echo "<tr><td colspan='2'>";
					  echo "<center><b>Interner Fehler! Benutzer konnte nicht angelegt werden.<br>Bitte kontaktieren Sie den Systemadministrator.</b></center>";
					  echo "</td></tr>";
					}
				?>
				<tr>
				<td style="padding-left:2cm">
					<b> Benutzername:</b>
				</td>
				<td>
					<input type="text" name="username" size="20" maxlength="40">
				</td>
				</tr>
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
 						<input type="text" name="fullname" size="50" maxlength="32" ><br>
					</td>
				</tr>
				<tr>
 					<td width="50%" style="padding-left:2cm">
 						<b> E-mail:</b>
  				</td>
  				<td width="50%">
 						<input type="text" name="email" size="50" maxlength="100"><br>
					</td>
				</tr>
				<tr>
 					<td width="50%" style="padding-left:2cm">
 						<b> Admin:</b>
  				</td>
  				<td width="50%">
 						<input type="checkbox" name="admin"><br>
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
</body>
</html>