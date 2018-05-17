package be.howest.ti.threebeesandme.howeststone.db; /*
  Created by Bert on 16/05/2018.
  Have a nice day!
 */

public class SqlStatements {
    public static final String SElECT_DECKS =
            "SELECT deckName, heroId FROM Decks;";

    public static final String SElECT_HEROES =
            "SELECT * FROM Heroes;";

    public static final String SElECT_CARDS =
            "SELECT * FROM Cards;";

    public static final String SElECT_CARDSINDECK =
            "SELECT * FROM CardsInDecks;";

    public static final String SElECT_MECHANICS =
            "SELECT * FROM Mechanics;";

    public static final String INSERT_DECK =
            "INSERT INTO `howeststone `.`Decks` (`deckName`, `heroId`) " +
                    "VALUES (?, ?);";

    public static final String DELETE_CARD =
            "DELETE FROM 'CardsInDecks" +
                    "WHERE cardId= ('?');";

    public static final String DELETE_CARD_COPY =
            "UPDATE INTO `howeststone `.`CardsInDecks` (`amount`) " +
                    "VALUES (1);" + "WHERE cardId = ('?') AND deckId = ('?')";

    public static final String ADD_CARD =
            "INSERT INTO `howeststone `.`CardsInDecks` (`deckId`, `cardId`, `amount`) " +
            "VALUES (?, ?, 1);";

    public static final String ADD_CARD_COPY =
            "UPDATE INTO `howeststone `.`CardsInDecks` (`amount`) " +
                    "VALUES (2);" + "WHERE cardId = ('?') AND deckId = ('?')";



    public static final String INSERT_CARDSINDECK =
            "INSERT INTO `howeststone `.`CardsInDecks` (`deckId`, `cardId`) " +
                    "VALUES (?, ?);";
}