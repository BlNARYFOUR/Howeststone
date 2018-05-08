<?php
$db = new PDO('mysql:host=localhost;dbname=howeststone');

$jsondata = file_get_contents('minions.json');
$data =json_decode($jsondata, true);

$stmt = $db->prepare("insert into json values(?,?,?,?,?,?,?,?,?,?)");

foreach ($data as $row) {
    $stmt->bindParam(1, $row['cardName']);
    $stmt->bindParam(2, $row['cardType']);
    $stmt->bindParam(3, $row['img']);
    $stmt->bindParam(4, $row['rarity']);
    $stmt->bindParam(5, $row['health']);
    $stmt->bindParam(6, $row['damage']);
    $stmt->bindParam(7, $row['manaCost']);
    $stmt->bindParam(8, $row['heroId']);
    $stmt->bindParam(9, $row['race']);
}

include_once "assets/php/classes/LoadPages.php";
include_once "assets/php/classes/Database.php";

LoadPages::home();