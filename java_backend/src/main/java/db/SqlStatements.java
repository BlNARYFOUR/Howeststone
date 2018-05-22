package db;

public class SqlStatements {
    public static final String DROP_DATABASE =
            "DROP DATABASE howeststone";

    public static final String CREATE_DATABASE =
            "CREATE DATABASE howeststone;\n"
                    + "USE howeststone;\n"
                    + "CREATE TABLE Mechanics ( "
                    + "mechanicId INT(3) AUTO_INCREMENT NOT NULL, "
                    + "mechanicType VARCHAR(45) NOT NULL,"
                    + "CONSTRAINT pk_mechanics PRIMARY KEY(mechanicId)"
                    + ")ENGINE = INNODB;"
                    + "CREATE TABLE Abilities (\n"
                    + "\tabilityId INT(3) AUTO_INCREMENT NOT NULL, \n"
                    + "        abilityName VARCHAR(45) NOT NULL,\n"
                    + "        CONSTRAINT pk_Abilities PRIMARY KEY(AbilityId)\n"
                    + ")ENGINE = INNODB;\n"
                    + "CREATE TABLE Heroes (\n"
                    + "\theroId INT(2) AUTO_INCREMENT NOT NULL,\n"
                    + "        heroName VARCHAR(45) NOT NULL,\n"
                    + "        CONSTRAINT pk_heroes PRIMARY KEY(heroId)\n"
                    + ")ENGINE = INNODB;\n"
                    + "CREATE TABLE Cards (\n"
                    + "\tcardId INT(3) AUTO_INCREMENT NOT NULL,\n"
                    + "        cardName VARCHAR(45) NOT NULL,\n"
                    + "        cardType VARCHAR(45) NOT NULL,\n"
                    + "        img VARCHAR(100) NOT NULL,\n"
                    + "        rarity VARCHAR(45) NOT NULL,\n"
                    + "        health INT(2),\n"
                    + "        attack INT(2),\n"
                    + "        manaCost INT(2) NOT NULL,\n"
                    + "        heroId INT(2),\n"
                    + "        CONSTRAINT pk_cards PRIMARY KEY(cardId),\n"
                    + "        CONSTRAINT fk_cards FOREIGN KEY(heroId) REFERENCES Heroes(heroId)\n"
                    + ")ENGINE = INNODB;\n"
                    + "CREATE TABLE cardMechanics (\n"
                    + "\tcardMechId INT (3) AUTO_INCREMENT NOT NULL,\n"
                    + "        mechanicId INT(3) NOT NULL,\n"
                    + "        target VARCHAR(45),\n"
                    + "        VALUE INT(3),\n"
                    + "        CONSTRAINT pk_cardMechanics PRIMARY KEY(cardMechId),\n"
                    + "        CONSTRAINT fk_cardMechanics FOREIGN KEY(mechanicId) REFERENCES Mechanics(mechanicId)\n"
                    + ")ENGINE = INNODB;\n"
                    + "CREATE TABLE CardAbilities (\n"
                    + "\tabilityId INT(3) NOT NULL,\n"
                    + "        cardId INT(3) NOT NULL,\n"
                    + "        cardMechId INT(3) NOT NULL,\n"
                    + "        CONSTRAINT pk_CardAbilities PRIMARY KEY(abilityId, cardId, cardMechId),\n"
                    + "        CONSTRAINT fk_CardAbilities FOREIGN KEY(abilityId) REFERENCES Abilities(abilityId),\n"
                    + "        CONSTRAINT fk_CardAbilities2 FOREIGN KEY(cardId) REFERENCES Cards(cardId),\n"
                    + "        CONSTRAINT fk_CardAbilities3 FOREIGN KEY(cardMechId) REFERENCES CardMechanics(cardMechId)\n"
                    + ")ENGINE = INNODB;\n"
                    + "CREATE TABLE Decks (\n"
                    + "\tdeckId INT(2) AUTO_INCREMENT NOT NULL,\n"
                    + "        deckName VARCHAR(45) NOT NULL,\n"
                    + "        heroId INT(2) NOT NULL,\n"
                    + "        CONSTRAINT pk_Decks PRIMARY KEY(deckId),\n"
                    + "        CONSTRAINT fk_Decks FOREIGN KEY(heroId) REFERENCES Heroes(heroId)\n"
                    + ")ENGINE = INNODB;\n"
                    + "CREATE TABLE CardsInDecks (\n"
                    + "\tdeckId INT(2) NOT NULL,\n"
                    + "        cardId INT(3) NOT NULL,\n"
                    + "        amount INT(1) NOT NULL,\n"
                    + "        CONSTRAINT pk_CardsInDecks PRIMARY KEY(deckId, cardId),\n"
                    + "        CONSTRAINT fk_CardsInDecks FOREIGN KEY(deckId) REFERENCES Decks(deckId),\n"
                    + "        CONSTRAINT fk_CardsInDecks2 FOREIGN KEY(cardId) REFERENCES Cards(cardId)\n"
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

    public static final String INSERT_CARDS_IN_DECK =
            "INSERT INTO `howeststone`.`CardsInDecks` (`deckId`, `cardId`) VALUES (?, ?);";

    public static final String INSERT_ABILITY =
            "INSERT INTO `howeststone`.`Abilities` (`abilityName`) VALUES (?)";
}
