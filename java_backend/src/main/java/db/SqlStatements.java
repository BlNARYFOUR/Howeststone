package db;

public final class SqlStatements {

    public static final String CREATE_ABILITIES =
            "CREATE TABLE howeststone.Abilities ( abilityId INT(3) AUTO_INCREMENT NOT"
                    + " NULL, abilityName VARCHAR(45) NOT NULL, CONSTRAINT"
                    + " pk_Abilities PRIMARY KEY(AbilityId))ENGINE = INNODB;";

    public static final String CREATE_HEROES =
            "CREATE TABLE howeststone.Heroes ( heroId INT(2) AUTO_INCREMENT NOT NULL, heroName VARCHAR(45) NOT NULL,"
                    + " CONSTRAINT pk_heroes PRIMARY KEY(heroId) )ENGINE = INNODB;";

    public static final String CREATE_CARDS =
            "CREATE TABLE howeststone.Cards ( cardId INT(3) AUTO_INCREMENT NOT NULL, cardName VARCHAR(45) NOT NULL,"
                    + " cardType VARCHAR(45) NOT NULL, race VARCHAR(45), img VARCHAR(100) NOT NULL,"
                    + " rarity VARCHAR(45) NOT NULL, health INT(2), attack INT(2), manaCost INT(2) NOT NULL,"
                    + " durability INT(2), heroId INT(2), CONSTRAINT pk_cards PRIMARY KEY(cardId),"
                    + " CONSTRAINT fk_cards FOREIGN KEY(heroId) REFERENCES Heroes(heroId) )ENGINE = INNODB;";

    public static final String CREATE_CARD_MECHANICS =
            "CREATE TABLE howeststone.cardMechanics ( cardMechId INT (3) AUTO_INCREMENT NOT NULL, mechanicId INT(3)"
                    + " NOT NULL, target VARCHAR(45), mechValue VARCHAR(45), CONSTRAINT pk_cardMechanics PRIMARY"
                    + " KEY(cardMechId), CONSTRAINT fk_cardMechanics FOREIGN KEY(mechanicId) REFERENCES"
                    + " Mechanics(mechanicId) )ENGINE = INNODB;";

    public static final String CREATE_CARD_ABILITIES =
            "CREATE TABLE howeststone.CardAbilities ( abilityId INT(3) NOT NULL, cardId INT(3) NOT NULL,"
                    + " cardMechId INT(3) NOT NULL, CONSTRAINT pk_CardAbilities PRIMARY KEY(abilityId, cardId,"
                    + " cardMechId), CONSTRAINT fk_CardAbilities FOREIGN KEY(abilityId) REFERENCES"
                    + " Abilities(abilityId), CONSTRAINT fk_CardAbilities2 FOREIGN KEY(cardId)"
                    + " REFERENCES Cards(cardId), CONSTRAINT fk_CardAbilities3 FOREIGN KEY(cardMechId)"
                    + " REFERENCES CardMechanics(cardMechId) )ENGINE = INNODB;";

    public static final String CREATE_DECKS =
            "CREATE TABLE howeststone.Decks ( deckId INT(2) AUTO_INCREMENT NOT NULL, deckName VARCHAR(45) NOT"
                    + " NULL, heroId INT(2) NOT NULL, CONSTRAINT pk_Decks PRIMARY KEY(deckId), CONSTRAINT"
                    + " fk_Decks FOREIGN KEY(heroId) REFERENCES Heroes(heroId) )ENGINE = INNODB;";

    public static final String CREATE_CARDS_IN_DECKS =
            "CREATE TABLE howeststone.CardsInDecks ( deckId INT(2) NOT NULL, cardId INT(3) NOT NULL, amount INT(1)"
                    + " NOT NULL, CONSTRAINT pk_CardsInDecks PRIMARY KEY(deckId, cardId), CONSTRAINT"
                    + " fk_CardsInDecks FOREIGN KEY(deckId) REFERENCES Decks(deckId), CONSTRAINT"
                    + " fk_CardsInDecks2 FOREIGN KEY(cardId) REFERENCES Cards(cardId))ENGINE = INNODB;";

    public static final String DROP_DB =
            "DROP DATABASE howeststone;";

    public static final String CREATE_DB =
            "CREATE DATABASE howeststone;";

    public static final String CREATE_MECHANICS =
            "CREATE TABLE howeststone.Mechanics ( mechanicId INT(3) AUTO_INCREMENT NOT NULL, mechanicType VARCHAR(45)"
                    + " NOT NULL, CONSTRAINT pk_mechanics PRIMARY KEY(mechanicId) )ENGINE = INNODB;";

    public static final String INSERT_NONE_MECHANIC =
            "INSERT INTO howeststone.mechanics (mechanicType) VALUES (\"none\");";

    public static final String INSERT_NONE_CARD_MECHANIC =
            "INSERT INTO howeststone.cardmechanics (mechanicId, target, mechValue) VALUES (1, \"none\", \"none\");";


    public static final String SELECT_DECKS =
            "SELECT deckName, heroId FROM howeststone.Decks;";

    public static final String SELECT_HEROES =
            "SELECT * FROM HOWESTSTONE.Decks;";

    public static final String SELECT_CARDS =
            "SELECT * FROM HOWESTSTONE.cards;";

    public static final String SELECT_DECK_ID =
            "SELECT deckId FROM HOWESTSTONE.Decks WHERE deckName = ?";

    public static final String SELECT_HERO_ID =
            "SELECT heroId FROM HOWESTSTONE.Heroes WHERE heroName = ?";

    public static final String SELECT_MECHANIC_ID =
            "SELECT mechanicId FROM HOWESTSTONE.Mechanics WHERE mechanicType = ?";

    public static final String SELECT_ABILITY_ID =
            "SELECT abilityId FROM HOWESTSTONE.Abilities WHERE abilityName = ?";

    public static final String SELECT_CARDS_IN_DECK =
            "SELECT cardId, amount FROM HOWESTSTONE.CardsInDecks WHERE deckId = ?";

    public static final String SELECT_MECHANICS =
            "SELECT * FROM HOWESTSTONE.Mechanics;";

    public static final String IS_LEGENDARY =
            "SELECT rarity = \"Legendary\" AS `isLegendary` FROM howeststone.cards WHERE cardId = ?";

    public static final String IS_UNCOLLECTABLE =
            "SELECT MAX(abilities.abilityName = \"Uncollectable\") AS `isUncollectable`"
                    + " FROM howeststone.cards"
                    + " LEFT JOIN howeststone.cardabilities ON cards.cardId=cardabilities.cardId"
                    + " JOIN howeststone.abilities ON cardabilities.abilityId=abilities.abilityId"
                    + " WHERE cards.cardId = ?";

    public static final String SELECT_AMOUNT_OF_CARDS_IN_DECK =
            "SELECT amount FROM HOWESTSTONE.CardsInDecks WHERE deckId = ? AND cardId = ?";

    public static final String INSERT_DECK =
            "INSERT INTO `HOWESTSTONE`.`Decks` (`deckName`, `heroId`) VALUES (?, ?);";

    public static final String INSERT_CARD_TO_DECK =
            "INSERT INTO `HOWESTSTONE`.`CardsInDecks` (`deckId`, `cardId`, `amount`) VALUES (?, ?, ?);";

    public static final String INSERT_ABILITY =
            "INSERT INTO `HOWESTSTONE`.`Abilities` (`abilityName`) VALUES (?)";

    public static final String INSERT_HERO =
            "INSERT INTO `HOWESTSTONE`.`Heroes` (`heroName`) VALUES (?)";

    public static final String INSERT_MECHANIC =
            "INSERT INTO `HOWESTSTONE`.`Mechanics` (`mechanicType`) VALUES (?)";

    public static final String INSERT_CARD_MECHANIC =
            "INSERT INTO `HOWESTSTONE`.`CardMechanics` (`mechanicId`, `target`, `mechValue`) VALUES (?, ?, ?)";

    public static final String INSERT_CARD_ABILITY =
            "INSERT INTO `HOWESTSTONE`.`CardAbilities` (`abilityId`, `cardId`, `cardMechId`) VALUES (?, ?, ?)";

    public static final String INSERT_CARD =
            "INSERT INTO `HOWESTSTONE`.`Cards` (`cardName`, `cardType`,`race` ,`img`, `rarity`, `health`, `attack`,"
                    + " `manaCost`, `durability`, `heroId`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_AMOUNT_OF_CARD_IN_DECK =
            "UPDATE `HOWESTSTONE`.`CardsInDecks` SET amount = ? WHERE deckId = ? AND cardId = ?";

    public static final String GET_TOTAL_AMOUNT_OF_CARDS =
            "SELECT MAX(cardId) AS amountOfCards FROM howeststone.cards";

    public static final String GET_HEROES =
            "SELECT * FROM heroes WHERE heroName != \"Neutral\"";

    public static final String GET_DECKS =
            "SELECT * FROM decks JOIN heroes ON decks.heroId = heroes.heroId WHERE heroName = ?";

    public static final String GET_CARD_IN_DECK = "SELECT cards.*, cardsindecks.amount FROM decks"
            + " JOIN cardsindecks ON cardsindecks.deckId=decks.deckId JOIN cards ON cards.cardId ="
            + " cardsindecks.cardId WHERE deckName = ?";


    public static final String GET_ABILITIES_OF_CARD = "SELECT cardabilities.abilityId, "
            + "cardabilities.cardMechId, abilities.abilityName, cardmechanics.target, cardmechanics.mechValue,"
            + " mechanics.mechanicType FROM cardabilities JOIN abilities ON cardabilities.abilityId=abilities.abilityId"
            + " JOIN cardmechanics ON cardmechanics.cardMechId=cardabilities.cardMechId JOIN mechanics ON"
            + " mechanics.mechanicId=cardmechanics.mechanicId WHERE cardId = ?";

    private SqlStatements() { }
}
