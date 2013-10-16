<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type"
 content="text/html; charset=iso-8859-1">
  <title>Neptun Roboter Simulation</title>
   <link rel="stylesheet" href="style.css">
</head>
<body
style="color: rgb(255, 255, 204); background-color: rgb(255, 255, 255); background-image: url(bigNeptun.jpg); font-size:14pt;"
 alink="#66ffff" link="#66ffff" vlink="#66ffff">
 <h1 style="margin-left: 40px;">Neptun Roboter Simulation</h1>
 <table>
 <tr>
  <td valign=top>
<div style="margin-left: 30px; margin-top: 40px;">
<nobr>
<a href="inhalt.php">Diamanten in den H&ouml;hlen des Neptun</a>
</nobr>
</div>
 <div style="margin-left: 30px; margin-top: 40px;">
<a href="ueber.php">&Uuml;ber: "Neptun Roboter Simulation"</a>
</div>


<div style="margin-left: 30px; margin-top: 40px;">
Kontakt: <a href="http://einspiel.net/">Wolfgang Slany</a>
</div>

<div style="margin-left: 30px; margin-top: 40px;">
<a href="hilfe.php">Hilfe zum Spiel</a>
</div>
<div style="margin-left: 30px; margin-top: 40px;">
<a href="../">spielen</a>
</div>
</td>
<td>

<h1 style="margin-left: 40px;">Wie spielt man das eigentlich?<br>
</h1>
<div style="margin-left: 40px;">Du musst den Roboter Marvin-10 so
programmieren, dass er durch das Labyrinth f&auml;hrt, dort alle
Diamanten aufsammelt und anschlie&szlig;end damit herauskommt. Wenn du
das Spiel startest, siehst du erst mal eine ganze Menge. Achte auf das
Labyrinth und den Roboter, der ganz
unten am Eingang steht. <br>
<br>
<img alt="Spielstart" src="./snapshot2.png"
 style="width: 800px; height: 458px;"><br>
<br>
Marvin-10 kann am Anfang leider &uuml;berhaupt nichts. Aber du kannst
ihn mit Hilfe der Sensorregeln programmieren, die du links im Bild
siehst:<br>
<br>
<img alt="Sensor_blank" src="sensor_blank.png"
 style="width: 252px; height: 82px;"><br>
<br>
<b>Und wie funktionieren die Sensorregeln?</b> <br>
Das ist ganz einfach:
Links in der Sensorregel beschreibst du einen Zustand und rechts gibst
du dem Roboter Befehle, die er ausf&uuml;hren soll, wenn er in diesem
Zustand ist. Ver&auml;ndere jetzt die erste Sensorregel zum Beispiel so:<br>
<br>
<img alt="Sensor_filled" src="./sensor.png"
 style="width: 252px; height: 82px;"><br>
<br>
Das machst du, indem du mit der linken Maustaste auf die Felder
klickst, die du ver&auml;ndern willst. Aber das hast du sicher schon
gewu&szlig;t. Supergut! Die Sensorregel, die du oben siehst, sagt dem
Roboter:<br>
"WENN du links und rechts eine Wand siehst, DANN gehe geradeaus". <br>
<br>
Dr&uuml;ck doch einmal auf den Startknopf, das ist dieser hier:<br>
<br>
<img style="width: 150px; height: 30px;" alt="Start-Button"
 src="startknopf.png"><br>
<br>
Der Roboter f&auml;hrt nun los. Allerdings
nicht weit, denn Marvin-10 kennt erst diese eine Regel und
er f&auml;hrt immer nur so weit wie er sich auskennt. Du musst
also Marvin-10 noch kl&uuml;ger machen. Ver&auml;ndere dazu die zweite, dritte und
vierte Sensorregel, um ihm zum Beispiel zu sagen: <br>
"WENN nur links ein Weg ist, DANN drehe nach links". <br>
Ich bin sicher, du kannst das alleine programmieren. Vier
Sensorregeln sollten reichen, damit der Roboter durch das einfache
Labyrinth f&auml;hrt und am Ende mit allen Diamanten herauskommt.<br>
<br>
Wenn du gut programmiert hast, holt Marvin-10 alle Diamanten aus dem
Labyrinth heraus und in der Statusleiste unter dem Labyrinth bekommst du diese
Meldung zu sehen:<br>
<br>
<img style="width: 447px; height: 61px;" alt="geschafft"
 src="geschafft.png"><br>
<br>
Wenn du das geschafft hast, dann weisst du bereits wie die
Programmierung funktioniert. Allerdings bleibt es nicht so
einfach!
Klick doch einmal auf <b>Schwierigkeitsgrad</b> ganz oben in der grauen Menüleiste.
So kannst du ein komplizierteres Labyrinth ausw&auml;hlen,
und wenn du wieder Start dr&uuml;ckst, dann werden deine vier Sensorregeln
nicht mehr ausreichen und du musst dir weitere ausdenken. Weitere
Regeln bekommst du &uuml;brigens mit diesem Knopf:<br>
<br>
<img style="width: 159px; height: 33px;" alt="Button" src="neu.png"><br>
<br>
Du hast
dich sicher schon gefragt, wozu die anderen
Schaltfl&auml;chen der Sensorregeln da sind. Erinnere dich daran,
da&szlig; jede Regel,
die du machst, aus zwei Teilen besteht: <br>
Einem WENN-Teil auf der linken und einem DANN-Teil auf der rechten Seite. <br>
Du kannst bestimmen:<br>
"WENN links und rechts W&auml;nde sind UND der Zustand des Roboters blau ist,
DANN fahre geradeaus UND wechsle den Zustand auf rot." <br>
Diese Regel sieht
so aus:<br>
<img alt="Sensor, Farbe" src="./sensor_farbe.png"
 style="width: 252px; height: 79px;"><br>
Au&szlig;erdem kann der Roboter Zahlen auf den Boden schreiben und
lesen. So kann er markieren, wo er schon war. Praktisch, hm? Die
folgende Regel
bedeutet: "WENN links und rechts W&auml;nde sind UND am Boden eine Null
steht, DANN gehe geradeaus und schreibe eine Eins auf den Boden."<br>
<img alt="Sensor, Zahl" src="./sensor_zahl.png"
 style="width: 250px; height: 80px;"><br>
So, jetzt weisst du alles was du brauchst, um Marvin-10 zu
programmieren. <br>Kannst du alle Diamanten kriegen?<p>
Ach ja, wenn dir der Roboter zu langsam ist, kannst du mit dem Schieber die Geschwindigkeit regeln. Du wirst sehen, dass der ganz schön herumflitzen kann!
Wenn du die Geschwindigkeit hingegen auf 0 setzt, dann darfst du bei jeder Bewegung auf den <b>Schritt</b>-Knopf klicken.<br>
Dumm? Nein, ganz und gar nicht, denn so kannst du genau sehen, welche Regel der Roboter verwendet hat und diese sogar ändern!
<p>

<h1 style="margin-left: 40px;">Der Expertenmodus</h1>
<h2 style="margin-left: 20px;">Regelsatz editieren</h2>
Im Expertenmodus <b>Regelsatz editieren</b> kannst du den Roboter auch neue Regeln beibringen!
Du brauchst nicht auf die Knöpfe klicken, sondern du schreibst sie einfach hin.<br>
Dafür musst du wirklich kein Experte sein!<br>
"WENN links und rechts W&auml;nde sind UND der Zustand des Roboters blau ist, DANN fahre geradeaus UND wechsle den Zustand auf rot."
 <br>
Da der Satz aber ziemlich lange und für den Roboter schwer zu verstehen wäre, gibt es eine Abkürzung:<p>
(fgf,blau,)->(,nach vorn,rot)<p>
das heißt soviel wie: <br>
WENN ('links Wand''vorne Gang''rechts Wand' , Zustand blau , keine Bodenmarkierung ) DANN (keine Bodenmarkierung, gehen nach vorne, neuer Zustand rot)<p>

Hier findest du alle Wörter, die der Rooter schon schon versteht:<br>
<table border='1'>
<tr><th>Gelände</th><th>Zustände</th><th>Bodenmarkierungen</th><th>Bewegungsrichtungen</th></tr>
<tr><td> f -> Wand</td><td> grün</td><td>0</td> <td>nach vorn</td></tr>
<tr><td> g -> Gang</td><td> blau</td><td>1</td><td>nach rechts</td></tr>
<tr><td> </td>               <td> rot</td><td>2</td><td>nach links</td></tr>
<tr><td> </td>               <td> gelb</td><td>3</td><td>umdrehen</td></tr>
</table>
<p>
<b>Was, das sind nicht genug Bodenmarkierungen und Zustände?</b><br> Erfinde einfach neue! Du kannst dafür bis zu 30 Zeichen verwenden.<p>

Einen weiteren Vorteil bietet dir der Expertenmode: Wenn du möchtest, dass der Roboter eine deiner Regeln nicht benützt, dann macht er das auch nicht!. Du brauchst nur # vor die Regel setzen.<br>
Also so:<br>
#(fgf,blau,)->(,nach vorn,rot)<p>
Du kannst auch Kommentare schreiben<br>
// Das hier geschriebene brauchst du dir nicht zu merken <p>
Mehrere Zeilen werden so weglassen:<br>
/* Die nachfolgenden Zeilen bitte ignorieren,<br>
denn sie sind streng geheim und gehen dich <br>
wirklich überhaupt nichts an! */<p>

Auf eines musst du noch aufpassen:
Für den Roboter sind 'A' und 'a' NICHT gleich!!!<br>
(FGG,,)->(,nach vorn,) wird er daher nicht verstehen!<p>

<h2 style="margin-left: 20px;">Labyrinth editieren</h2>
Für das Erstellen eines Labyrinths werden Informationen über dessen Größe und Aussehen benötigt.<br>
Da das eine ganze Menge an Daten sind, reichen ein paar einfache Zeichen, wie bei "Regelsatz editieren", nicht mehr aus. Mit <b>XML</b> (Extensible Markup Language) kannst du Regeln erstellen, die von Computerprogrammen leicht verstanden werden können.<p>

Zuerst muss die Neptun Simulation einmal wissen, dass sie jetzt XML zu lesen bekommt und wie das Dokument aussehen soll.<p>
&nbsp &nbsp &nbsp &lt;?xml version="1.0" encoding="iso-8859-1"?&gt;<br>
&nbsp &nbsp &nbsp &lt;!DOCTYPE LabyrinthLevel SYSTEM "LabyrinthLevel.dtd"&gt;<p>

Jetzt sagen wir: "Wir wollen ein neues Level generieren, nimm alles was zwischen
&lt;LabyrinthLevel&gt; und &lt;/LabyrinthLevel&gt; steht."<br>
Achtung! Zwischen diese beiden "Blöcke" definieren wir alles andere!!

Das fertige Dokument sollte dann in etwa so aussehen: <p>
&nbsp &nbsp &nbsp &lt;?xml version="1.0" encoding="iso-8859-1"?&gt;<br>
&nbsp &nbsp &nbsp &lt;!DOCTYPE LabyrinthLevel SYSTEM "LabyrinthLevel.dtd"&gt;<br>
&nbsp &nbsp &nbsp &lt;LabyrinthLevel&gt; <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp...<br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp HIER stehen alle Infos zur Gestaltung des Labyrinths!<br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp...<br>
&nbsp &nbsp &nbsp &lt;/LabyrinthLevel&gt;<p>


<b>Name und Größe</b><p>
Jedes Labyrinth hat einen Namen.<br>
&nbsp &nbsp &nbsp &lt;Name&gt; Mein erstes Labyrinth &lt;/Name&gt;<p>


Die Größe deines Labyrinths gibts du so an:<br>
&nbsp &nbsp &nbsp &lt;Dimension&gt;<br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;width&gt; 20 &lt;/width&gt;<br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;height&gt; 20 &lt;/height&gt;<br>
&nbsp &nbsp &nbsp &lt;/Dimension&gt;<p>

<b>Aussehen</b><p>
Um das Aussehen festzulegen gibt es 3 Möglichkeiten:<br>
<ol>
<li> Fixes Labyrinth: Du zeichnest das Labyrinth genau so wie es dargestellt werden soll
<li> Zufalls-Labyrinth: Du bestimmst nur welche Eigenschaften (Verzweigungen, Plätze, Schleifen...) das Labyrinth haben soll und überlässt das genaue Aussehen der Simulation
<li> Kombiniertes-Labyrinth: Fixes Labyrinth, in welches zufällig weitere Gänge einzeichnet werden
</ol>
<b>Fixes Labyrinth: </b><p>
&nbsp &nbsp &nbsp &lt;Labyrinth&gt; <br>
Welches Symbol (1 Zeichen) in deinem gezeichneten Labyrinth steht für Startpunkt, Wand, Gang und Diamant sowie für die Bodenmarkierungen 0, 1, 2 und 3 <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;StartSymbol&gt; S &lt;/StartSymbol&gt;<br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;WallSymbol&gt; W &lt;/WallSymbol&gt; <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;WaySymbol&gt; . &lt;/WaySymbol&gt; <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;DiamondSymbol&gt; D &lt;/DiamondSymbol&gt; <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;ZeroSymbol&gt; 0 &lt;/ZeroSymbol&gt; <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;OneSymbol&gt; 1 &lt;/OneSymbol&gt; <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;TwoSymbol&gt; 2 &lt;/TwoSymbol&gt; <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;ThreeSymbol&gt; 3 &lt;/ThreeSymbol&gt; <br>


Weitere Symbole können so definiert werden:<br>
&nbsp &nbsp &nbsp &lt;SymbolList&gt; <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;Symbol&gt; <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;CharSymbol&gt; a &lt;/CharSymbol&gt; <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;StringSymbol&gt; Test String &lt;/StringSymbol&gt; <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;/Symbol&gt; <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;Symbol&gt; <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;CharSymbol&gt; b &lt;/CharSymbol&gt; <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;StringSymbol&gt; 2. Test String &lt;/StringSymbol&gt; <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;/Symbol&gt; <br>
&nbsp &nbsp &nbsp &lt;/SymbolList&gt; <br>









Mögliche Werte für die Startrichtung des Roboters: "up", "down", "left", "right"   <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;StartingDirection&gt; UP &lt;/StartingDirection&gt; <br>
Zeichnen des Labyrinths: <br>Die Anzahl der Zeichen muss mit der angegebenen Größe übereinstimmen.<br>
(Verwende die oben festgelegten Zeichen!)<br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;LabyrinthField&gt; <br>
<TT>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp WWWWWWWWWWWWWWWWWWWW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp W.....b...........WW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp W.WWWWWWWWWWWWWWW.WW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp W.WWWWWWWWWWWWWWW.WW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp W.WWWWWWWWWWWWWWW.WW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp W...WWWWWWWWWWWWW.WW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp WWW.WWWWWWWWWWWWW.WW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp WWW.a....D........WW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp WWW.WWWWWWWWWWWWW.WW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp WWW.WWWWWWWWWWWWW.WW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp WWW.WWWWWWWWWWWWW.WW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp WWW.WWWWWWWWWWWWW.WW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp WWW.WWWWWWWWWWWWW.WW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp W.................WW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp W.WWWWWWWWWWWWWWWWWW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp W.WWWWWWWWWWWWWWWWWW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp W.WWWWWWWWWWWWWWWWWW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp W.WWWWWWWWWWWWWWWWWW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp W.WWWWWWWWWWWWWWWWWW <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp WSWWWWWWWWWWWWWWWWWW <br></TT>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;/LabyrinthField&gt; <br>
&nbsp &nbsp &nbsp &lt;/Labyrinth&gt; <p>

<b>Zufalls Labyrinth: </b><p>
&nbsp &nbsp &nbsp &lt;Create&gt; <br>
Anzahl der Diamanten <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;Diamonds&gt; 20 &lt;/Diamonds&gt; <br>
Maximale Anzahl an Wegzellen<br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;WayCells&gt; 150 &lt;/WayCells&gt; <br>
Minimale Länge eines Weges <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;MinWayLenght&gt; 3 &lt;/MinWayLenght&gt; <br>
Maximale Länge eines Weges (Es wird aber nicht verhindert, dass der Weg in die gleiche Richtung weitergebaut wird!)<br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;MaxWayLenght&gt; 5 &lt;/MaxWayLenght&gt; <br>
Kurfen bauen: 1 steht für ja, 0 für nein <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;BuildAngles&gt; 1 &lt;/BuildAngles&gt; <br>
Verzweigungen bauen: 1 steht für ja, 0 für nein <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;BuildTrees&gt; 1 &lt;/BuildTrees&gt; <br>
Schleifen bauen: 0 für nein, Zahlenwert für die maximale Anzahl an Schleifen <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;BuildLoops&gt; 3 &lt;/BuildLoops&gt; <br>
Plätze bauen: 1 steht für ja, 0 für nein <br>
&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &lt;BuildPlaces&gt; 0 &lt;/BuildPlaces&gt; <br>
&nbsp &nbsp &nbsp &lt;/Create&gt; <p>
<b>Fixes Labyrinth mit Zufallsanteil: </b><p>
Verwende einfach <br>
&nbsp &nbsp &nbsp &lt;Labyrinth&gt; <br>
&nbsp &nbsp &nbsp &lt;/Labyrinth&gt; <br>
und <br>
&nbsp &nbsp &nbsp &lt;Create&gt; <br>
&nbsp &nbsp &nbsp &lt;/Create&gt; <br>
</div>
</td>
</tr>
</table>
<? include '../version.php'; ?>
</body>
</html>