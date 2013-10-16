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
<body bgcolor="eeeeee">
<h3>Neptun Roboter Simulation</h3>
<center>
	<? include 'menue.php'; ?>
</center>
<p>
<b>Benutzer hinzufügen</b>
</p>
<p>
Bitte wählen Sie eine Datei mit der Endung '.txt' oder '.csv' aus, in dem sich Daten in folgendem Format befinden:
</p>
<p>
	Gruppe
<b>;</b>
	LfNr.
<b>;</b>
	Platz
<b>;</b>
	<b>Familienname</b>
<b>;</b>
	<b>Vorname</b>
<b>;</b>
	<b>Matrikelnummer</b>
<b>;</b>
	Studienrichtung
<b>;</b>
	Anmeldedatum
<b>;</b>
	<b>EMail</b>
<b>;</b>
	Anmerkung
 </p>
 <form action="createusersfromlist.php?userid=<?echo $_REQUEST["userid"]?>" method="post" enctype="multipart/form-data" name="upload">
<input type="file" name="file" size="80"><input type="submit" name="submit" value="Upload">
</form>
<? include 'version.php'; ?>
</body>
</html>