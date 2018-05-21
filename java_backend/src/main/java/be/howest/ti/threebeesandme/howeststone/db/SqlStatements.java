package be.howest.ti.threebeesandme.howeststone.db;

public class SqlStatements {
    public static final String DROP_DATABASE =
            "DROP DATABASE howeststone";

    public static final String NEW_DATABASE =
            "CREATE DATABASE howeststone"
                    + "USE howeststone"
                    + "CREATE TABLE Heroes ("
                    + "heroId INT(2) AUTO_INCREMENT NOT NULL,"
                    + "heroName VARCHAR(30) NOT NULL,"
                    + "heroPowerId INT(2) NOT NULL,"
                    + "health INT(2) NOT NULL,"
                    + "CONSTRAINT pk_heroes PRIMARY KEY(heroId),"
                    + "CONSTRAINT fk_Heroes FOREIGN KEY(heroPowerId) REFERENCES HeroPowers(heroPowerId)"
                    + ")ENGINE = INNODB  "
                    + "CREATE TABLE Cards ("
                    + "cardId INT(3) AUTO_INCREMENT NOT NULL,"
                    + "cardName VARCHAR(75) NOT NULL,"
                    + "cardType VARCHAR(30) NOT NULL,"
                    + "img VARCHAR(125) NOT NULL,"
                    + "rarity VARCHAR(10) NOT NULL,"
                    + "health INT(2),"
                    + "manaCost INT(2) NOT NULL,"
                    + "heroId INT(2),"
                    + "CONSTRAINT pk_cards PRIMARY KEY(cardId),"
                    + "CONSTRAINT fk_cards FOREIGN KEY(heroId) REFERENCES Heroes(heroId)"
                    + ")ENGINE = INNODB "
                    + "CREATE TABLE Mechanics ("
                    + "mechanicId INT(1) AUTO_INCREMENT NOT NULL,"
                    + "mechanicName VARCHAR(2) NOT NULL,"
                    + "CONSTRAINT pk_mechanics PRIMARY KEY(mechanicId)"
                    + ")ENGINE = INNODB"
                    + "CREATE TABLE cardMechanics ("
                    + "mechanicId INT(1) NOT NULL,"
                    + "cardId INT(3) NOT NULL,"
                    + "CONSTRAINT pk_cardMechanics PRIMARY KEY(mechanicId, cardId),"
                    + "CONSTRAINT fk_cardMechanics FOREIGN KEY(mechanicId) REFERENCES Mechanics(mechanicId),"
                    + "CONSTRAINT fk_cardMechanics2 FOREIGN KEY(cardId) REFERENCES Cards(cardId)"
                    + ")ENGINE = INNODB "
                    + "CREATE TABLE HeroPowers ("
                    + "heroPowerId INT(2) AUTO_INCREMENT NOT NULL,"
                    + "heroPoWerName VARCHAR(30) NOT NULL,"
                    + "manaCost INT(1) NOT NULL,"
                    + "CONSTRAINT pk_HeroPowers PRIMARY KEY(heroPowerId)"
                    + ")ENGINE = INNODB "
                    + "CREATE TABLE Decks ("
                    + "deckId INT(2) AUTO_INCREMENT NOT NULL,"
                    + "deckName VARCHAR(50) NOT NULL,"
                    + "heroId INT(2) NOT NULL,"
                    + "CONSTRAINT pk_Decks PRIMARY KEY(deckId),"
                    + "CONSTRAINT fk_Decks FOREIGN KEY(heroId) REFERENCES Heroes(heroId)"
                    + ")ENGINE = INNODB"
                    + "CREATE TABLE CardsInDecks ("
                    + "deckId INT(2) NOT NULL,"
                    + "cardId INT(3) NOT NULL,"
                    + "amount INT(1) NOT NULL MAX(2)"
                    + "CONSTRAINT pk_CardsInDecks PRIMARY KEY(deckId, cardId),"
                    + "CONSTRAINT fk_CardsInDecks FOREIGN KEY(deckId) REFERENCES Decks(deckId),"
                    + "CONSTRAINT fk_CardsInDecks2 FOREIGN KEY(cardId) REFERENCES Cards(cardId)"
                    + ")ENGINE = INNODB";

    public static final String SELECT_DECKS =
            "SELECT deckName, heroId FROM Decks;";

    public static final String SELECT_HEROES =
            "SELECT * FROM Decks;";

    public static final String SELECT_CARDS =
            "SELECT * FROM Decks;";

    public static final String SELECT_CARDSINDECK =
            "SELECT * FROM Decks;";

    public static final String SELECT_MECHANICS =
            "SELECT * FROM Decks;";

    public static final String INSERT_DECK =
            "INSERT INTO `howeststone `.`Decks` (`deckName`, `heroId`) VALUES (?, ?);";

    public static final String INSERT_CARDSINDECK =
            "INSERT INTO `howeststone `.`CardsInDecks` (`deckId`, `cardId`) VALUES (?, ?);";
}
