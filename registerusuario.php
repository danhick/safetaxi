<?php
header('Content-Type: application/json');
include 'conexion.php';

// Check connection
$nombre=$_POST["nombre"];
$apellido=$_POST["apellido"];
$correo=$_POST["correo"];
$edad=$_POST["edad"];
$foto=$_POST["foto"];
$contra=$_POST["contra"];

$data = $conexion -> query("INSERT INTO registroUsuario ( nombre,apellido,correo,edad,contra,foto)
 values('$nombre','$apellido','$correo','$edad','$contra','$foto')");

$response = array();
$response ["success"] =true;

echo json_encode($response);

?>