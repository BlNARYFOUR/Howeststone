<?php

$db = new PDO('mysql:host=localhost;dbname=howeststone');

$mechanicNames = ["Charge", "Divine Shield", "Windfury", "Battlecry", "Taunt", "Poisonous", "Deathrattle", "enrage", "Stealth"];
$heroNames = ["Mage", "Paladin"];
/*
 * Charge
 * Divine Shield
 * Windfury
 * Battlecry
 * Taunt
 * Poisonous
 * Deathrattle
 * Enrage
 * Stealth
 */

foreach($heroNames as $heroName) {
    $stmt = $db->prepare("insert into heroes (heroName) values (:name)");
    $stmt->bindParam(":name", $heroName);
    $stmt->execute();
}

/*foreach($mechanicNames as $mechanicName) {
    $stmt = $db->prepare("insert into mechanics (mechanicName) values (:name)");
    $stmt->bindParam(":name", $mechanicName);
    $stmt->execute();
}
*/

/*
$jsondata = file_get_contents('minions.json');
$data =json_decode($jsondata, true);

$stmt = $db->prepare("insert into cards (cardName, cardType, img, rarity, attack, health, manaCost, heroId) values (?,?,?,?,?,?,?,?,?)");

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

