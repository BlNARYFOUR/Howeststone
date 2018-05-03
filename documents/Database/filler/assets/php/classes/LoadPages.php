<?php

class LoadPages {
    public static function home() {
        ?>
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <link type="text/css" rel="stylesheet" href="assets/css/screen.css" />
            <title>My Login</title>
        </head>
        <body>

        <header>
            <h1>Welcome to The MySQL Howeststone Helper!</h1>
        </header>

        <main>
            <span class="result"></span>

            <form id="heroPowers">
                <h2>Add a Hero Power</h2>
                <input type="text" name="hpName" id="hpName" placeholder="Name" required="required" />
                <input type="text" name="hpManaCost" id="hpManaCost" placeholder="Mana Cost" required="required" />
                <input type="text" name="hpDmg" id="hpDmg" placeholder="Damage" />
                <input type="submit" name="hpBut" id="hpBut" value="Add this!" />
            </form>

            <form id="mechanics">
                <h2>Add a Mechanic</h2>
                <input type="text" name="meName" id="meName" placeholder="Name" required="required" />
                <input type="submit" name="meBut" id="meBut" value="Add this!" />
            </form>

            <form id="heroes">
                <h2>Add a Hero</h2>
                <input type="text" name="heroName" id="heroName" placeholder="Name" required="required" />
                <label for="heroesHeroPowerName">Hero Power Name: </label>
                <select name="heroesHeroPowerName" id="heroesHeroPowerName" required="required">
                </select>
                <input type="text" name="heroHealth" id="heroHealth" placeholder="Health" required="required" />
                <input type="submit" name="heroBut" id="heroBut" value="Add this!" />
            </form>

            <form id="cards">
                <h2>Add a Card</h2>
                <input type="text" name="cardName" id="cardName" placeholder="Name" required="required" />
                <input type="text" name="cardType" id="cardType" placeholder="Type" required="required" />
                <input type="text" name="cardImg" id="cardImg" placeholder="Image link" required="required" />
                <input type="text" name="cardRarity" id="cardRarity" placeholder="Rarity" required="required" />
                <input type="text" name="cardHealth" id="cardHealth" placeholder="Health" required="required" />
                <input type="text" name="cardManaCost" id="cardManaCost" placeholder="Mana Cost" required="required" />
                <label for="cardHeroName">Hero Name: </label>
                <select name="cardHeroName" id="cardHeroName" required="required">
                </select>
                <input type="submit" name="cardBut" id="cardBut" value="Add this!" />
            </form>

            <form id="cardMechanics">
                <h2>Add a Mechanic to a Card</h2>
                <label for="cmMechanicName">Mechanic Name: </label>
                <select name="cmMechanicName" id="cmMechanicName" required="required">
                </select>
                <label for="cmCardName">Card Name: </label>
                <select name="cmCardName" id="cmCardName" required="required">
                </select>
                <input type="submit" name="cmBut" id="cmBut" value="Add this!" />
            </form>

            <form id="decks">
                <h2>Add a Deck</h2>
                <input type="text" name="deckName" id="deckName" placeholder="Name" required="required" />
                <label for="deckHeroName">Hero Name: </label>
                <select name="deckHeroName" id="deckHeroName" required="required">
                </select>
                <input type="submit" name="deckBut" id="deckBut" value="Add this!" />
            </form>

            <form id="cardsInDecks">
                <h2>Add a Card to a Deck</h2>
                <label for="cidDeckName">Hero Name: </label>
                <select name="cidDeckName" id="cidDeckName" required="required">
                </select>
                <label for="cidCardName">Hero Name: </label>
                <select name="cidCardName" id="cidCardName" required="required">
                </select>
                <input type="text" name="cidAmount" id="cidAmount" placeholder="Amount" required="required" />
                <input type="submit" name="cidBut" id="cidBut" value="Add this!" />
            </form>
        </main>

        <script src="assets/js/submitForm.js"></script>
        </body>
        </html>
        <?php
    }
}