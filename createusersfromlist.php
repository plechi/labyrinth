<?session_name ("NeptunRobot");
session_start ();
include 'checkadmin.php';
include 'database.php';
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>Neptun Roboter Simulation</title>
<link rel="stylesheet" href="style.css">
</head>
<body bgcolor="#eeeeee">
<h3>Neptun Roboter Simulation</h3>
<center>
	<? include 'menue.php'; ?>
</center>
<center>
<p>
<b>Daten der empfangenen Datei</b>
</p>

<p>
<?php

// Variabeln festlegen
$max_byte_size = 2097152;
//$allowed_types = "(jpg|jpeg|gif|bmp|png|txt)";
$allowed_types = "(txt|csv)";
// Formular wurde abgeschickt
if($_POST["submit"] == "Upload")
{
	// Wurde wirklich eine Datei hochgeladen?
	if(is_uploaded_file($_FILES["file"]["tmp_name"]))
	{
		// Gültige Endung? ($ = Am Ende des Dateinamens) (/i = Groß- Kleinschreibung nicht berücksichtigen)
		if(preg_match("/\." . $allowed_types . "$/i", $_FILES["file"]["name"]))
		{
			// Datei auch nicht zu groß
			if($_FILES["file"]["size"] <= $max_byte_size)
			{
				// Alles OK -> Datei kopieren
				$name_on_server="/tmp/".$_FILES["file"]["name"];
				echo $test;
				if(copy($_FILES["file"]["tmp_name"], $name_on_server))
				{
					echo "Datei erfolgreich hochgeladen!<br>";
					echo "Name: " . $_FILES["file"]["name"] . "<br>";
					echo "Größe: " . $_FILES["file"]["size"] . " Byte<br>";
					echo "MIME-Type: " . $_FILES["file"]["type"] . "<br>\n";
					readFileOnServer($name_on_server);
				}
				else
				{
					echo "Datei konnte nicht hochgeladen werden.";
				}
			}
			else
			{
				echo "Die Datei darf nur eine Größe von " . $max_byte_size . " Byte besitzen.";
			}
		}
		else
		{
			echo "Die Datei besitzt keine ungültige Endung.";
		}
	}
	else
	{
		echo "Keine Datei zum Hochladen angegeben.";
	}

}
else
{
echo "Bitte benutzen Sie das Upload Formular.";
}
?>
</p>
<?
function passwortgenerator($number)
{
	$pool = "qwertupasdfghkxcvbnm";
	$pool .= "23456789";
	$pool .= "WERTUPLKJHGFDSAXCVBNM";
	srand ((double)microtime()*1000000);
	for($index = 0; $index < $number; $index++)
	{
		$pass_word .= substr($pool,(rand()%(strlen ($pool))), 1);
	}
	return $pass_word;
}

function send_mail($email, $full_name, $username, $pwd, $from)
{
    $email = str_replace("\r", '', $email);
    $email = str_replace("\n", '', $email);
	if(strlen($email)!=0)
	{
		if(mail($email,"Grundlagen der Informatik 2006 - Registration for $full_name",
			"This is an automated email notification of user registration\n" .
			"for \"Grundlagen der Informatik 2006 Labyrinth\".\n" .
			"\n" .
			"  * Name: $full_name \n" .
			"  * Email: $email \n" .
			"  * Login name: $username \n".
			"  * Password: $pwd \n" .
			"\n" .
			"http://www.ist.tugraz.at/labyrinth/\n" .
			"\n" .
			"Please save this email for future reference.\n",
			"From: $from\r\n" .
			"Reply-To: $from\r\n" .
			"X-Mailer: PHP/" . phpversion()))
			echo "mail verschickt an $email";
		else
			echo "\nERROR mail nicht verschickt!\nUsername: $username, Passwort: $pwd\n";
	}
	else
		echo "Username: $username, Passwort: $pwd";
}

function readFileOnServer($name_on_server)
{
	echo "<p>\n";
	echo "<b>Erstelle die Benutzer</b>\n";
	echo "</p>\n";
	echo "<p>\n";
	$lines = file ($name_on_server);
	echo "lese ".count($lines)." Zeilen<br>\n";
	echo "</p>\n";
	echo "<table border='0' cellpadding='10' cellspacing='0' bordercolor='#000000' bgcolor='#ffffff'
				style='border-top-width:2px; border-left-width:2px; border-right-width:2px;  border-bottom-width:2px; padding-top:1cm; padding-left:1cm; padding-right:1cm;'>\n";
// Durchgehen des Arrays und Anzeigen des HTML Source inkl. Zeilennummern
	foreach ($lines as $line_num => $line)
	{
		$line=trim($line," ");//" " => ist kein Leerzeichen! Nicht löschen, steht sonst in der Datenbank!
		$linearray=explode(";", $line);
		list($gruppe, $nummer, $platz, $fname, $vname, $matnr, $studrichtung, $datum, $email, $anmerkung) = $linearray;
		$nummer=trim($nummer);
		$matnr=trim($matnr," ");//" " => ist kein Leerzeichen! Nicht löschen, steht sonst in der Datenbank!
		$fname=trim($fname," ");//" " => ist kein Leerzeichen! Nicht löschen, steht sonst in der Datenbank!
		$vname=trim($vname," ");//" " => ist kein Leerzeichen! Nicht löschen, steht sonst in der Datenbank!
		$email=trim($email," ");//" " => ist kein Leerzeichen! Nicht löschen, steht sonst in der Datenbank!
		if (count($linearray)==10)
		{
			if( is_numeric($nummer) && ($matnr!='') )
			{
				$full_name=$vname." ".$fname;
				$username=$matnr;

				echo "<tr>";
				echo "<td>Zeile #<b>{$line_num}</b></td><td>full_name=".$full_name."</td><td>username=".$username."</td><td>email=".$email."</td><td>";

				if(strlen($username)==0)
				{
					echo "\nFEHLER: Benutzername fehlt</td><td></td>\n";
				}
				else
				{
					$sql = "SELECT * FROM users WHERE (username like '".$username."')";
					$result = mysql_query($sql);
					if (mysql_num_rows($result) > 0)
					{
						//Username allready in use
						$sql = "UPDATE users SET full_name='".mysql_escape_string($full_name)."', email='".mysql_escape_string($email)."' WHERE (username like '".mysql_escape_string($username)."')";
						$result = mysql_query($sql);
						if ($result)
							echo "Benuzter geändert</td><td></td>";
					}
					else
					{
						$pwd=passwortgenerator(5);
						$sql = "INSERT INTO users (username, password, full_name, email, type) VALUES ('".mysql_escape_string($username)."','".md5($pwd)."','".mysql_escape_string($full_name)."','".mysql_escape_string($email)."','0')";
						$result = mysql_query($sql);
						if (!$result)
							echo "FEHLER: Benuzter nicht hinzugefügt</td><td></td>";
						else
						{
							echo "Benuzter hinzugefügt</td>";
							echo "<td>";

							send_mail($email, $full_name, $username, $pwd, "\"Wolfgang Slany\" <wsi@ist.tugraz.at>");

							echo "</td>";
						}
					}
				}
				echo "</tr>\n";
			}
		}
		else
		{
			echo "<tr><td>\n";
			echo "ERROR Falsches format:<br>\n$line<br>\n";
			echo "</td></tr>\n";
		}
	}
	mysql_close();
	echo "</table>\n";
}
?>
<? include 'version.php'; ?>
</center>
</body>
</html>
