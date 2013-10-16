<?php
// Wird ausgeführt um mit der Ausgabe des Headers zu warten.
session_name ("NeptunRobot");
ob_start ();



session_start ();

session_unset ();

session_destroy ();



header ("Location: index.php");

ob_end_flush ();

?>
