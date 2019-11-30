<?php
include 'conexion.php';
$usu_numerotaxi=$_POST['numerotaxi'];


//$usu_usuario="h";
//$usu_password="1234";

$sentencia=$conexion->prepare("SELECT * FROM registrotaxi WHERE numerotaxi=?");
$sentencia->bind_param('s',$usu_numerotaxi);
$sentencia->execute();

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()) {
         echo json_encode($fila,JSON_UNESCAPED_UNICODE);     
}
$sentencia->close();
$conexion->close();
?>