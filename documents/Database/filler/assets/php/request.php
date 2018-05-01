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
    $requestPost = isset($_POST['request']) ? $_POST['request'] : null;
    $requestPost = isset($_POST['request']) ? $_POST['request'] : null;

    return null;
}