package be.howest.ti.threebeesandme.howeststone.db; /*
  Created by Bert on 16/05/2018.
  Have a nice day!
 */

public class SqlStatements {
    public static final String SElECT_DECKS =
            "SELECT deckName, heroId FROM Decks;";

    public static final String SElECT_HEROES =
            "SELECT * FROM Decks;";

    public static final String SElECT_CARDS =
            "SELECT * FROM Decks;";

    public static final String SElECT_CARDSINDECK =
            "SELECT * FROM Decks;";

    public static final String SElECT_MECHANICS =
            "SELECT * FROM Decks;";

    public static final String INSERT_DECK =
            "INSERT INTO `howeststone `.`Decks` (`deckName`, `heroId`) " +
                    "VALUES (?, ?);";

    public static final String INSERT_CARDSINDECK =
            "INSERT INTO `howeststone `.`CardsInDecks` (`deckId`, `cardId`) " +
                    "VALUES (?, ?);";
}