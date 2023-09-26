<?php
declare(strict_types=1);
use Firebase\JWT\JWT;
require_once('../vendor/autoload.php');
require_once('../config/connection.php');

//Recepcion de los valores en el cuerpo de la llamada post
// Retrieved from filtered POST data
$json = file_get_contents('php://input'); // Lee el JSON del cuerpo de la solicitud
$data = json_decode($json, true); // Decodifica el JSON en un array asociativo


$usuario = $data['username'];
$contrasena = $data['password'];
$nombre = $data['name'];

//Verifico si existe el usuario
$sql = "SELECT id, username FROM user WHERE username = '$usuario'";
$resultado = $conexion->query($sql);

//$hasValidCredentials = true;
$userNameExists = $resultado->num_rows == 1;

// extract credentials from the request
if ($userNameExists) {
	echo "error_username_exists";
    } else {

        // Username does not exist, insert a new user into the database
        $insertSql = "INSERT INTO user (username, password, token, token_expiracy, nombre) VALUES ('$usuario', MD5('$contrasena'), null, null, '$nombre')";

        if ($conexion->query($insertSql) === TRUE) {
            // User successfully registered
            echo "registration_success";
        } else {
            // Error occurred while inserting the user
            echo "error_registration";
        }
}
