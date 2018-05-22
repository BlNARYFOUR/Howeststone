package db;

public class SqlStatements {
    public static final String DROP_DATABASE =
            "DROP DATABASE howeststone";

    public static final String NEW_DATABASE =
            "CREATE DATABASE howeststone"
                    + "CREATE TABLE Mechanics ("
                    + "mechanicId INT(3) AUTO_INCREMENT NOT NULL,"
                    + "mechanicType VARCHAR(45) NOT NULL,"
                    + "CONSTRAINT pk_mechanics PRIMARY KEY(mechanicId)"
                    + ")ENGINE = INNODB;"
                    + "CREATE TABLE Abilities ("
                    + "abilityId INT(3) AUTO_INCREMENT NOT NULL,"
                    + "abilityName VARCHAR(45) NOT NULL,"
                    + "CONSTRAINT pk_Abilities PRIMARY KEY(AbilityId)"
                    + ")ENGINE = INNODB;"
                    + "CREATE TABLE CardAbilities ("
                    + "abilityId INT(3) NOT NULL,"
                    + "cardId INT(3) NOT NULL,"
                    + "cardMechId INT(3) NOT NULL,"
                    + "CONSTRAINT pk_CardAbilities PRIMARY KEY(abilityId, cardId, cardMechId),"
                    + "CONSTRAINT fk_CardAbilities FOREIGN KEY(abilityId) REFERENCES Abilities(abilityId),"
                    + "CONSTRAINT fk_CardAbilities2 FOREIGN KEY(cardId) REFERENCES Cards(cardId),"
                    + "CONSTRAINT fk_CardAbilities3 FOREIGN KEY(cardMechId) REFERENCES CardMechanics(cardMechId)"
                    + ")ENGINE = INNODB;"
                    + "CREATE TABLE Heroes ("
                    + "heroId INT(2) AUTO_INCREMENT NOT NULL,"
                    + "heroName VARCHAR(45) NOT NULL,"
                    + "CONSTRAINT pk_heroes PRIMARY KEY(heroId)"
                    + ")ENGINE = INNODB;"
                    + "CREATE TABLE Cards ("
                    + "cardId INT(3) AUTO_INCREMENT NOT NULL,"
                    + "cardName VARCHAR(45) NOT NULL,"
                    + "cardType VARCHAR(45) NOT NULL,"
                    + "img VARCHAR(100) NOT NULL,"
                    + "rarity VARCHAR(45) NOT NULL,"
                    + "health INT(2),"
                    + "attack INT(2),"
                    + "manaCost INT(2) NOT NULL,"
                    + "heroId INT(2),"
                    + "CONSTRAINT pk_cards PRIMARY KEY(cardId),"
                    + "CONSTRAINT fk_cards FOREIGN KEY(heroId) REFERENCES Heroes(heroId)"
                    + ")ENGINE = INNODB;"
                    + "CREATE TABLE cardMechanics ("
                    + "cardMechId INT (3) AUTO_INCREMENT NOT NULL,"
                    + "mechanicId INT(3) NOT NULL,"
                    + "target VARCHAR(45),"
                    + "value INT(3),"
                    + "CONSTRAINT pk_cardMechanics PRIMARY KEY(cardMechId),"
                    + "CONSTRAINT fk_cardMechanics FOREIGN KEY(mechanicId) REFERENCES Mechanics(mechanicId)"
                    + ")ENGINE = INNODB;"
                    + "CREATE TABLE Decks ("
                    + "deckId INT(2) AUTO_INCREMENT NOT NULL,"
                    + "deckName VARCHAR(45) NOT NULL,"
                    + "heroId INT(2) NOT NULL,"
                    + "CONSTRAINT pk_Decks PRIMARY KEY(deckId),"
                    + "CONSTRAINT fk_Decks FOREIGN KEY(heroId) REFERENCES Heroes(heroId)"
                    + ")ENGINE = INNODB;"
                    + "CREATE TABLE CardsInDecks ("
                    + "deckId INT(2) NOT NULL,"
                    + "cardId INT(3) NOT NULL,"
                    + "amount INT(1) NOT NULL MAX(2),"
                    + "CONSTRAINT pk_CardsInDecks PRIMARY KEY(deckId, cardId),"
                    + "CONSTRAINT fk_CardsInDecks FOREIGN KEY(deckId) REFERENCES Decks(deckId),"
                    + "CONSTRAINT fk_CardsInDecks2 FOREIGN KEY(cardId) REFERENCES Cards(cardId)"
                    + ")ENGINE = INNODB;";


    public static final String SELECT_DECKS =
            "SELECT deckName, heroId FROM howeststone.Decks;";

    public static final String SELECT_HEROES =
            "SELECT * FROM howeststone.Decks;";

    public static final String SELECT_CARDS =
            "SELECT * FROM howeststone.Decks;";

    public static final String SELECT_CARDSINDECK =
            "SELECT * FROM howeststone.Decks;";

    public static final String SELECT_MECHANICS =
            "SELECT * FROM howeststone.Decks;";

    public static final String INSERT_DECK =
            "INSERT INTO `howeststone`.`Decks` (`deckName`, `heroId`) VALUES (?, ?);";

    public static final String INSERT_CARDSINDECK =
            "INSERT INTO `howeststone`.`CardsInDecks` (`deckId`, `cardId`) VALUES (?, ?);";
}
