<?php

$db = new PDO('mysql:host=localhost;dbname=howeststone');

$mechanicNames = ["Charge", "Divine Shield", "Windfury", "Battlecry", "Taunt", "Poisonous", "Deathrattle", "Enrage", "Stealth", "Turn End"];
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

/*
foreach($mechanicNames as $mechanicName) {
    $stmt = $db->prepare("insert into mechanics (mechanicName) values (:name)");
    $stmt->bindParam(":name", $mechanicName);
    $stmt->execute();
}
*/

$jsondata = file_get_contents('minions.json');
$data =json_decode($jsondata, true);

$stmt = $db->prepare("insert into cards (cardName, cardType, img, rarity, attack, health, manaCost, heroId, race, abilityType1, abilityType2, abilityValue1, abilityValue2) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");

foreach ($data as $row) {
    $stmt->bindParam(1, $row['name']);
    $stmt->bindParam(2, $row['type']);
    $stmt->bindParam(3, $row['img']);
    $stmt->bindParam(4, $row['rarity']);
    $stmt->bindParam(5, $row['health']);
    $stmt->bindParam(6, $row['attack']);
    $stmt->bindParam(7, $row['cost']);

    $stmt->bindParam(8, $row['heroId']);
    $stmt->bindParam(9, $row['race']);
    $stmt->bindParam(10, $row['race']);
    $stmt->bindParam(11, $row['race']);
    $stmt->bindParam(12, $row['race']);
    $stmt->bindParam(13, $row['race']);
}

