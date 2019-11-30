<?php
header('Content-Type: application/json');
include 'conexion.php';

// Check connection
$nombre=$_POST["nombre"];
$apellido=$_POST["apellido"];
$correo=$_POST["correo"];
$telefono=$_POST["telefono"];
$numerotaxi=$_POST["numerotaxi"];
$placa=$_POST["placa"];
$marca=$_POST["marca"];
$modelo=$_POST["modelo"];
$ciudad=$_POST["ciudad"];
$contra=$_POST["contra"];
$foto=$_POST["foto"];



$data = $conexion -> query("INSERT INTO registrotaxi (nombre,apellido,correo,telefono,numerotaxi,placa,marca,modelo,ciudad,contra,foto)
 values('$nombre','$apellido','$correo','$telefono','$numerotaxi','$placa','$marca','$modelo','$ciudad','$contra','$foto')");

$response = array();
$response ["success"] =true;

echo json_encode($response);

?>