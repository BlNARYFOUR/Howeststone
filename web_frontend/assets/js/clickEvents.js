"use strict";

document.addEventListener('DOMContentLoaded', init);

function init() {
    document.getElementById('gotoPlay').addEventListener('click', gotoPlay);
    document.getElementById('gotoHome').addEventListener('click', gotoHome);
    document.getElementById('gotoDeck').addEventListener('click', gotoDeck);
    document.getElementById('gotoHero').addEventListener('click', gotoHero);
    document.getElementById('gotoDeckBuilder').addEventListener('click', gotoDeckBuilder);
    document.getElementById('gotoNoDeck').addEventListener('click', gotoNoDeck);
    document.getElementById('addDeck').addEventListener('click', addDeck);

}

function gotoHome() {
    document.getElementById('mainMenu').className = "";
    document.getElementById('heroPicker').className = "hidden";
}

function gotoPlay() {
    setTimeout(goto, 4800);     // This should be shown while loading ;)
    startLoadingScreen();

    function goto() {
        stopLoadingScreen();
        document.getElementById('mainMenu').className = "hidden";
        document.getElementById('heroPicker').className = "";
    }
}

function gotoDeck() {
    document.getElementById('deckPicker').className = "";
    document.getElementById('heroPicker').className = "hidden";
}

function gotoHero() {
    document.getElementById('deckPicker').className = "hidden";
    document.getElementById('heroPicker').className = "";
}


function gotoDeckBuilder() {
    // ontbrekende htmlpagina
    document.getElementById('mainMenu').className = "hidden";
    document.getElementById('deckbuilder').className = "";
}

function gotoNoDeck() {
    //
    document.getElementById('mainMenu').className = "";
    document.getElementById('deckbuilder').className = "hidden";
}

function addDeck() {
    document.getElementById('deckPicker').className = "hidden";
    document.getElementById('deckbuilder').className = "";
    document.getElementById('gotoNoDeck').removeEventListener('click', gotoNoDeck);
    document.getElementById('gotoNoDeck').setAttribute("id", "gotoChooseDeck");
    document.getElementById('gotoChooseDeck').addEventListener('click', gotoChooseDeck);
}
// werkt dit??
function gotoChooseDeck() {
    document.getElementById('gotoChooseDeck').removeEventListener('click', gotoChooseDeck);
    document.getElementById('gotoChooseDeck').setAttribute("id", "gotoNoDeck");
    document.getElementById('deckPicker').className = "";
    document.getElementById('deckbuilder').className = "hidden";
    document.getElementById('gotoNoDeck').addEventListener('click', gotoNoDeck);
}
