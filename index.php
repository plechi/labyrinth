<?
session_name ("NeptunRobot");
session_start (); ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>Neptun Robot</title>
<link rel="stylesheet" href="style.css">
<script src="switchlayer.js" type="text/javascript"></script>
</head>

<body bgcolor="#eeeeee">
			<h3>Neptun Roboter Simulation</h3>
			<center>
			<form action="login.php" method="post">
			<table border="0" cellpadding="10" cellspacing="0" bgcolor="#ffffff"
				style="border-top-width:0px; border-left-width:0px; border-right-width:0px;  border-bottom-width:0px;">

				<tr><td>
				<table width="100%" style="padding-top:1cm; padding-left:1cm; padding-right:1cm;">
				<tr>
					<td>
						<center>
						<b>
						Login
						</b>
						</center>
					</td>
				</tr>
				</table>
				<table width="100%" style="padding-top:0.5cm; padding-left:1cm; padding-right:1cm;">
				<?php
				if (isset ($_REQUEST["fehler"]))
					{
					  echo "<tr><td colspan='2' style='padding-bottom:1cm; padding-left:1cm; padding-right:0.5cm;'>";
					  echo "<center><b>Username or password incorrect!<br>Please try it again.</b></center>";
					  echo "</td></tr>";
					}
				?>
				<tr>
					<td style="padding-left:1cm" width="50%">
						 Benutzername:
					</td>
					<td width="50%">
						<input type="text" name="name" size="20">
					</td>
				</tr>
 				<tr>
 					<td width="50%" style="padding-left:1cm">
 						 Passwort:
  			  		</td>
  					<td width="50%">
 						 <input type="password" name="pwd" size="20"><br>
					</td>
				</tr>
<!--
				<tr>
					<td width="50%" style="padding-left:1cm">
						 Test:
					</td>
					<td width="50%">
						<input type="checkbox" name="admin" value="admin"  onclick="javascript:switchlayer('admin');">
					</td>
				</tr>

				</table>
				<table width="100%" id="admin" style="display:none; padding-left:1cm; padding-right:1cm;">
				<tr>
					<td style="padding-left:1cm"  width="50%">
						 Adminname:
					</td>
					<td width="50%">
						<input type="text" name="adminname" size="20">
					</td>
				</tr>
 				<tr>
 					<td width="50%" style="padding-left:1cm">
 						Adminpasswort:
  			  		</td>
  					<td width="50%">
 						 <input type="password" name="adminpwd" size="20"><br>
					</td>
				</tr>
-->
				</table>
				<table width="100%" style="padding-left:1cm; padding-right:1cm; padding-top:1cm; padding-bottom:1cm;">
				<tr>
					<td align="center">
						<input type="submit" value="Login">
					</td>
				</tr>
				</table>
				</td></tr>
		  </table>
			</form>
			</center>
			<p>
				<b>Kontakt: </b><a href="http://einspiel.net/">Wolfgang Slany</a>
		    </p>
		    <p>
				Cookies und JavaScript müssen für diese Seite aktiviert sein!<br>
				Ein Browserplugin für Java Version 1.5 wird ebenfalls benötigt.
			</p>
</body>
</html>
