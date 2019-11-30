<?php
include 'conexion.php';
$usu_correo=$_POST['correo'];
$usu_password=$_POST['contra'];

//$usu_usuario="h";
//$usu_password="1234";

$sentencia=$conexion->prepare("SELECT * FROM registroUsuario WHERE correo=? AND contra=?");
$sentencia->bind_param('ss',$usu_correo,$usu_password);
$sentencia->execute();

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()) {
         echo json_encode($fila,JSON_UNESCAPED_UNICODE);     
}
$sentencia->close();
$conexion->close();
?>