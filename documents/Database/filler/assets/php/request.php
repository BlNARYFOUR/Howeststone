<?php

include_once "classes/Database.php";

$requestPost = isset($_POST['request']) ? $_POST['request'] : null;
$reply = "Not even been in the switchCase?";

switch($requestPost) {
    case "heroPowers":
        $reply = handlePostHeroPowers();
        break;
    case "mechanics":
        $reply = null;
        break;
    case "heroes":
        $reply = null;
        break;
    case "cards":
        $reply = null;
        break;
    case "cardMechanics":
        $reply = null;
        break;
    case "decks":
        $reply = null;
        break;
    case "cardsInDecks":
        $reply = null;
        break;
    default:
        $reply = "Unrecognized request";
        break;
}

echo json_encode("$reply");

function handlePostHeroPowers() {
    $database = new Database();

    $name = isset($_POST['hpName']) ? $_POST['hpName'] : null;
    $manaCost = isset($_POST['hpManaCost']) ? $_POST['hpManaCost'] : null;

    return $database->addHeroPower($name, $manaCost);
}

function handlePostMechnanics() {
    $database = new Database();

    $name = isset($_POST['meName']) ? $_POST['meName'] : null;

    return $database->addMechanic($name);
}

function handlePostHeroes() {
    $database = new Database();

    $name = isset($_POST['heroName']) ? $_POST['heroName'] : null;
    $heroesHeroPowerName = isset($_POST['heroesHeroPowerName']) ? $_POST['meName'] : null;
    $health = isset($_POST['heroHealth']) ? $_POST['heroHealth'] : null;

    return $database->addHero($name, $heroesHeroPowerName, $health);
}
