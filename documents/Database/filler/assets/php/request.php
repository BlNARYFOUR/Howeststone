<?php

session_start();
session_regenerate_id(true);

include_once "classes/Database.php";

$request = isset($_POST['request']) ? $_POST['request'] : null;
$reply = "Not even been in the switchCase?";
$salt = "SaltyIsThisSalt";

switch($request) {
    case "login":
        $reply = login($salt);
        break;
    case "register":
        $reply = register($salt);
        break;
    case "logout":
        $reply = logout();
        break;
    default:
        $reply = "Unrecognized request";
        break;
}

echo json_encode("$reply");

function logout() {
    session_unset();

    setcookie("usr", "", time() - 3600, "/");
    setcookie("pwd", "", time() - 3600, "/");

    return "Logout succeeded";
}

function login($salt) {
    $result = "Login failed";
    $database = new Database();

    $usr = isset($_POST['usrName']) ? $_POST['usrName'] : null;
    $pwd = isset($_POST['pwd']) ? md5($_POST['pwd'] . $salt) : null;
    $rememberMe = isset($_POST['keepLogin']) ? $_POST['keepLogin'] : false;

    if($usr == "") {
        $result = "Enter a username";
    }
    else if($pwd == "") {
        $result = "Enter a password";
    }
    else {
        $succeeded = $database->isValidUser($usr, $pwd);
        if($succeeded) {
            $result = "Login succeeded";

            if($rememberMe) {
                setcookie("usr", $usr, time() + 31536000, "/");
                setcookie("pwd", $pwd, time() + 31536000, "/");
            }

            $_SESSION['usr'] = $usr;
            $_SESSION['pwd'] = $pwd;
        }
        else {
            $result = "Invalid username or password";
        }
    }

    return $result;
}

function register($salt) {
    $result = "Registration failed";
    $database = new Database();

    $usr = isset($_POST['desUsrName']) ? $_POST['desUsrName'] : "";
    $pwd = isset($_POST['desPwd']) ? md5($_POST['desPwd'] . $salt) : "";
    $pwdConfirm = isset($_POST['desPwdConfirm']) ? md5($_POST['desPwdConfirm'] . $salt) : "";

    if($usr == "") {
        $result = "Enter a username";
    }
    else if($database->findUser($usr)) {
        $result = "Username already exists";
    }
    else if($pwd == "") {
        $result = "Enter a password";
    }
    else if($pwd != $pwdConfirm) {
        $result = "Passwords not matching";
    }
    else {
        $succeeded = $database->addUser($usr, $pwd, "");
        if($succeeded) {
            $result = "Registration succeeded";
            $_SESSION['usr'] = $usr;
            $_SESSION['pwd'] = $pwd;
        }
        else {
            $result = "Unknown error";
        }
    }

    return $result;
}