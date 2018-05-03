<?php

class Database {
    private $serverName = "localhost";
    private $userName = "root";
    private $password = "";
    private $dbName = "howeststone";

    public function addHeroPower($name, $manaCost) {
        $conn = new mysqli($this->serverName, $this->userName, $this->password, $this->dbName);

        $name = htmlspecialchars($name);
        $manaCost = htmlspecialchars($manaCost);

        $sql = "INSERT INTO heropowers (heroPowerName, manaCost) VALUES (\"$name\", \"$manaCost\")";
        $result = false;

        if ($conn->query($sql) === TRUE) {
            $result = true;
        }

        $conn->close();

        return $result;
    }

    public function addMechanic($name) {
        $conn = new mysqli($this->serverName, $this->userName, $this->password, $this->dbName);

        $name = htmlspecialchars($name);

        $sql = "INSERT INTO mechanics (mechanicName) VALUES (\"$name\")";
        $result = false;

        if ($conn->query($sql) === TRUE) {
            $result = true;
        }

        $conn->close();

        return $result;
    }

    public function addHero($name, $heroPowerId, $health) {
        $conn = new mysqli($this->serverName, $this->userName, $this->password, $this->dbName);

        $name = htmlspecialchars($name);
        $heroPowerId = htmlspecialchars($heroPowerId);
        $health = htmlspecialchars($health);

        $sql = "INSERT INTO heroes (heroName, heroPowerId, health) VALUES (\"$name\", \"$heroPowerId\", \"$health\")";
        $result = false;

        if ($conn->query($sql) === TRUE) {
            $result = true;
        }

        $conn->close();

        return $result;
    }

    public function addCard($name, $type, $img, $rarity, $health, $manaCost, $heroId) {
        $conn = new mysqli($this->serverName, $this->userName, $this->password, $this->dbName);

        $name = htmlspecialchars($name);
        $type = htmlspecialchars($type);
        $img = htmlspecialchars($img);
        $rarity = htmlspecialchars($rarity);
        $health = htmlspecialchars($health);
        $manaCost = htmlspecialchars($manaCost);
        $heroId = htmlspecialchars($heroId);

        $sql = "INSERT INTO cards (cardName, cardType, img, rarity, health, manaCost, heroId) VALUES (\"$name\", \"$type\", \"$img\", \"$rarity\", \"$health\" ,\"$manaCost\", \"$heroId\")";
        $result = false;

        if ($conn->query($sql) === TRUE) {
            $result = true;
        }

        $conn->close();

        return $result;
    }

    public function addMechanicToCard($mechanicId, $cardId) {
        $conn = new mysqli($this->serverName, $this->userName, $this->password, $this->dbName);

        $mechanicId = htmlspecialchars($mechanicId);
        $cardId = htmlspecialchars($cardId);

        $sql = "INSERT INTO cardMechanics (mechanicId, cardId) VALUES (\"$mechanicId\", \"$cardId\")";
        $result = false;

        if ($conn->query($sql) === TRUE) {
            $result = true;
        }

        $conn->close();

        return $result;
    }

    public function addDeck($name, $heroId) {
        $conn = new mysqli($this->serverName, $this->userName, $this->password, $this->dbName);

        $name = htmlspecialchars($name);
        $heroId = htmlspecialchars($heroId);

        $sql = "INSERT INTO decks (deckName, heroId) VALUES (\"$name\", \"$heroId\")";
        $result = false;

        if ($conn->query($sql) === TRUE) {
            $result = true;
        }

        $conn->close();

        return $result;
    }

    public function addCardToDeck($deckId, $cardId, $amount) {
        $conn = new mysqli($this->serverName, $this->userName, $this->password, $this->dbName);

        $deckId = htmlspecialchars($deckId);
        $cardId = htmlspecialchars($cardId);
        $amount = htmlspecialchars($amount);

        $sql = "INSERT INTO cardsindecks (deckId, cardId, amount) VALUES (\"$deckId\", \"$cardId\", \"$amount\")";
        $result = false;

        if ($conn->query($sql) === TRUE) {
            $result = true;
        }

        $conn->close();

        return $result;
    }

    public function getHeroPowerIds() {
        $conn = new mysqli($this->serverName, $this->userName, $this->password, $this->dbName);

        $sql = "SELECT heroPowerId FROM heropowers";
        $result = $conn->query($sql);

        $heroPowerIds = $result->fetch_assoc()["heroPowerId"];

        $conn->close();

        return $heroPowerIds;
    }

    public function getHeroIds() {
        $conn = new mysqli($this->serverName, $this->userName, $this->password, $this->dbName);

        $sql = "SELECT heroId FROM heroes";
        $result = $conn->query($sql);

        $heroIds = $result->fetch_assoc()["heroId"];

        $conn->close();

        return $heroIds;
    }

    public function getHeroNames() {
        $conn = new mysqli($this->serverName, $this->userName, $this->password, $this->dbName);

        $sql = "SELECT heroName FROM heroes";
        $result = $conn->query($sql);

        $heroNames = $result->fetch_assoc()["heroName"];

        $conn->close();

        return $heroNames;
    }

    public function getMechanicIds() {
        $conn = new mysqli($this->serverName, $this->userName, $this->password, $this->dbName);

        $sql = "SELECT mechanicId FROM mechanics";
        $result = $conn->query($sql);

        $mechanicIds = $result->fetch_assoc()["mechanicId"];

        $conn->close();

        return $mechanicIds;
    }

    public function getCardIds() {
        $conn = new mysqli($this->serverName, $this->userName, $this->password, $this->dbName);

        $sql = "SELECT cardId FROM cards";
        $result = $conn->query($sql);

        $cardIds = $result->fetch_assoc()["cardId"];

        $conn->close();

        return $cardIds;
    }

    public function getDeckIds() {
        $conn = new mysqli($this->serverName, $this->userName, $this->password, $this->dbName);

        $sql = "SELECT deckId FROM decks";
        $result = $conn->query($sql);

        $deckIds = $result->fetch_assoc()["deckId"];

        $conn->close();

        return $deckIds;
    }
}

/*
CREATE DATABASE howeststone;

USE howeststone;

CREATE TABLE HeroPowers (
	heroPowerId INT(2) AUTO_INCREMENT NOT NULL,
	heroPoWerName VARCHAR(30) NOT NULL,
	manaCost INT(1) NOT NULL,
	CONSTRAINT pk_HeroPowers PRIMARY KEY(heroPowerId)
)ENGINE = INNODB;

CREATE TABLE Mechanics (
	mechanicId INT(1) AUTO_INCREMENT NOT NULL,
	mechanicName VARCHAR(20) NOT NULL,
	CONSTRAINT pk_mechanics PRIMARY KEY(mechanicId)
)ENGINE = INNODB;

CREATE TABLE Heroes (
	heroId INT(2) AUTO_INCREMENT NOT NULL,
	heroName VARCHAR(30) NOT NULL,
	heroPowerId INT(2) NOT NULL,
	health INT(2) NOT NULL,
	CONSTRAINT pk_heroes PRIMARY KEY(heroId),
	CONSTRAINT fk_Heroes FOREIGN KEY(heroPowerId) REFERENCES HeroPowers(heroPowerId)
)ENGINE = INNODB;

CREATE TABLE Cards (
	cardId INT(3) AUTO_INCREMENT NOT NULL,
	cardName VARCHAR(75) NOT NULL,
	cardType VARCHAR(30) NOT NULL,
	img VARCHAR(125) NOT NULL,
	rarity VARCHAR(10) NOT NULL,
	health INT(2),
	damage INT(2),
	manaCost INT(2) NOT NULL,
	heroId INT(2),
	CONSTRAINT pk_cards PRIMARY KEY(cardId),
	CONSTRAINT fk_cards FOREIGN KEY(heroId) REFERENCES Heroes(heroId)
)ENGINE = INNODB;

CREATE TABLE cardMechanics (
	mechanicId INT(1) NOT NULL,
	cardId INT(3) NOT NULL,
	CONSTRAINT pk_cardMechanics PRIMARY KEY(mechanicId, cardId),
	CONSTRAINT fk_cardMechanics FOREIGN KEY(mechanicId) REFERENCES Mechanics(mechanicId),
	CONSTRAINT fk_cardMechanics2 FOREIGN KEY(cardId) REFERENCES Cards(cardId)
)ENGINE = INNODB;

CREATE TABLE Decks (
	deckId INT(2) AUTO_INCREMENT NOT NULL,
	deckName VARCHAR(50) NOT NULL,
	heroId INT(2) NOT NULL,
	CONSTRAINT pk_Decks PRIMARY KEY(deckId),
	CONSTRAINT fk_Decks FOREIGN KEY(heroId) REFERENCES Heroes(heroId)
)ENGINE = INNODB;

CREATE TABLE CardsInDecks (
	deckId INT(2) NOT NULL,
	cardId INT(3) NOT NULL,
	amount INT(1) NOT NULL,
	CONSTRAINT pk_CardsInDecks PRIMARY KEY(deckId, cardId),
	CONSTRAINT fk_CardsInDecks FOREIGN KEY(deckId) REFERENCES Decks(deckId),
	CONSTRAINT fk_CardsInDecks2 FOREIGN KEY(cardId) REFERENCES Cards(cardId)
)ENGINE = INNODB;
 */