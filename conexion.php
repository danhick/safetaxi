<?php
$hostname='mysql.webcindario.com';
$database='safetaxi';
$username='safetaxi';
$password='admin';

$conexion=new mysqli($hostname,$username,$password,$database);
if($conexion->connect_errno){
    echo "El sitio web está experimentado problemas";
}
?>