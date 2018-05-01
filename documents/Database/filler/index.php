<?php

include_once "assets/php/classes/LoadPages.php";
include_once "assets/php/classes/Database.php";

$database = new Database();

LoadPages::home();