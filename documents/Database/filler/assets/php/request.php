<?php

include_once "classes/Database.php";

$requestPost = isset($_POST['request']) ? $_POST['request'] : null;
$reply = "Not even been in the switchCase?";

switch($requestPost) {
    case "heroPowers":
        $reply = handlePostHeroPowers();
        break;
    case "mechanics":
        $reply = handlePostMechanics();
        break;
    case "heroes":
        $reply = handlePostHeroes();
        break;
    case "cards":
        $reply = handlePostCards();
        break;
    case "cardMechanics":
        $reply = handlePostCardMechanics();
        break;
    case "decks":
        $reply = handlePostDecks();
        break;
    case "cardsInDecks":
        $reply = handlePostcardsInDecks();
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

function handlePostMechanics() {
    $database = new Database();

    $name = isset($_POST['meName']) ? $_POST['meName'] : null;

    return $database->addMechanic($name);
}

function handlePostHeroes() {
    $database = new Database();

    $name = isset($_POST['heroName']) ? $_POST['heroName'] : null;
    $heroesHeroPowerName = isset($_POST['heroesHeroPowerName']) ? $_POST['heroesHeroPowerName'] : null;
    $health = isset($_POST['heroHealth']) ? $_POST['heroHealth'] : null;

    return $database->addHero($name, $heroesHeroPowerName, $health);
}

function handlePostCards() {
    $database = new Database();

    $name = isset($_POST['cardName']) ? $_POST['cardName'] : null;
    $type = isset($_POST['cardType']) ? $_POST['cardType'] : null;
    $img = isset($_POST['cardImg']) ? $_POST['cardImg'] : null;
    $rarity = isset($_POST['cardRarity']) ? $_POST['cardRarity'] : null;
    $health = isset($_POST['cardHealth']) ? $_POST['cardHealth'] : null;
    $manaCost = isset($_POST['cardManaCost']) ? $_POST['cardManaCost'] : null;
    $heroId = isset($_POST['cardHeroName']) ? $_POST['cardHeroName'] : null;

    return $database->addCard($name, $type, $img, $rarity, $health, $manaCost, $heroId);
}

function handlePostCardMechanics() {
    $database = new Database();

    $name = isset($_POST['cmMechanicName']) ? $_POST['cmMechanicName'] : null;
    $card = isset($_POST['cmCardName']) ? $_POST['cmCardName'] : null;

    return $database->addMechanicToCard($name,$card);
}

function handlePostDecks() {
    $database = new Database();

    $name = isset($_POST['deckName']) ? $_POST['deckName'] : null;
    $heroId = isset($_POST['deckHeroName']) ? $_POST['deckHeroName'] : null;

    return $database->addDeck($name,$heroId);
}

function handlePostcardsInDecks() {
    $database = new Database();

    $deckId = isset($_POST['cidDeckName']) ? $_POST['cidDeckName'] : null;
    $cardId = isset($_POST['cidCardName']) ? $_POST['cidCardName'] : null;
    $amount = isset($_POST['cidAmount']) ? $_POST['cidAmount'] : null;

    return $database->addCardToDeck($deckId,$cardId,$amount);
}



