"use strict";

let selectedHero = "";
let selectedDeck = "";
let selectedDeckObj = null;
let activePlayer;

document.addEventListener('DOMContentLoaded', init);

function init() {
    getAllHeroes();

    document.getElementById('gotoDeckSelector').addEventListener('click', gotoDeckSelector);

    document.getElementById('playGame').addEventListener('click', selectDeck);
    document.getElementById('addDeck').addEventListener('click', addDeck);
    document.getElementById('gotoDeckBuilder').addEventListener('click', gotoDeckBuilder);
}

function addDeck() {
    deckbuilderSelectAndDeselectHero();
    document.getElementById('deckSelector').className = "hidden";
    document.getElementById('deckbuilder').className = "";
    document.getElementById('gotoRechooseDeck').removeEventListener('click', gotoRechooseDeck);
    document.getElementById('gotoRechooseDeck').setAttribute("id", "gotoChooseDeck");
    document.getElementById('gotoChooseDeck').addEventListener('click', gotoChooseDeck);
}

function showDecks(decks) {
    document.querySelector("#deckSelector .decks").innerHTML = "";
    for (let i = 0; i < decks.length; i++) {
        document.querySelector("#deckSelector .decks").innerHTML += '<li><a href="#">' + decks[i] + '</a></li>';

        if (i === 0) {
            selectedDeck = decks[i];
        }
    }

    let selectableDecks = document.querySelectorAll('#deckSelector .decks li a');
    for (let i = 0; i < selectableDecks.length; i++) {
        selectableDecks[i].addEventListener("click", handleSelectedDeck);
    }
}

function handleSelectedDeck(e) {
    let clickedDeck = this;

    if (selectedDeckObj !== null) {
        selectedDeckObj.style.backgroundColor = "";
    }

    selectedDeckObj = clickedDeck;
    selectedDeck = clickedDeck.innerText;

    this.style.backgroundColor = "rgba(0, 0, 0, 0.2)";
}

function getAllDecks() {
    fetch('/threebeesandme/get/heroanddeckselector/decks', {
        method: 'GET'
    })
        .then(function (res) {
            if (res.ok === true)
                return res.json();
        })
        .then(function (text) {
            let result = text;
            showDecks(result)
        })
        .catch(function (err) {
            console.log("Error: Could not load the heroes :'(");
        });


    //showDecks(["Some Deck :)", "Deck 1", "Deck 2", "OP 1 shot", "Noob deck"]);
}

function showHeroes(heroes) {
    let heroesHtml = document.querySelectorAll('.heroes');

    for (let i = 0; i < heroes.length; i++) {
        if (i === 0) {
            selectedHero = heroes[i]
        }

        for (let j = 0; j < heroesHtml.length; j++) {
            heroesHtml[j].innerHTML += '<li><a href="#" class="' + heroes[i] + '"><h2>' + heroes[i] + '</h2></a></li>';
        }

        document.querySelector("#deckbuilder #hero").innerHTML += '<a href="#">' + heroes[i] + '</a>';
        let heroHtmlObj = document.querySelectorAll('.heroes .' + heroes[i]);

        for (let j = 0; j < heroHtmlObj.length; j++) {
            heroHtmlObj[j].style.background = 'url("assets/media/' + heroes[i] + '.png") center center no-repeat';
            heroHtmlObj[j].style.backgroundSize = "145%";
        }
    }

    let selectableHeroes = document.querySelectorAll('.heroes li a');
    for (let i = 0; i < selectableHeroes.length; i++) {
        selectableHeroes[i].addEventListener("click", handleSelectedHero);
    }
}

function getAllHeroes() {
    fetch('/threebeesandme/get/heroanddeckselector/heroes', {
        method: 'GET'
    })
        .then(function (res) {
            if (res.ok === true) {
                return res.json();
            }
            else {
                return "ERROR";
            }
        })
        .then(function (text) {
            let result = text;
            showHeroes(result)
        })
        .catch(function (err) {
            console.log("Error: Could not load the heroes :'(");
        });
}

function handleSelectedHero(e) {
    let clickedHero = this;
    let heroName = clickedHero.classList[0];
    selectedHero = heroName;

    let selectedHeroName = document.querySelectorAll(".selectedHeroName");
    let backgroundHolders = document.querySelectorAll(".selectedHero");

    for (let i = 0; i < selectedHeroName.length; i++) {
        selectedHeroName[i].innerHTML = heroName;
        backgroundHolders[i].style.backgroundImage = `url('assets/media/${heroName}.png')`;
    }
}

function gotoDeckSelector() {
    let heroName = document.querySelector("#heroSelector .selectedHeroName").innerText;
    handleHeroSelection(heroName);
    document.getElementById('deckSelector').className = "";
    document.getElementById('heroSelector').className = "hidden";
}

function handleHeroSelection(heroName) {
    fetch('/threebeesandme/post/heroanddeckselector/hero', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: heroName
    })
        .then(function (res) {
            if (res.ok === true)
                return res.json();
            else
                return "ERROR";
        })
        .then(function (text) {
            getAllDecks();
        })
        .catch(function (err) {
            console.log("Error: Could not send the selected hero :'(");
        });

}
function selectDeck(e) {
    e.preventDefault();
    handleDeckSelection(selectedDeck);
}
function makeGame() {

    fetch('/threebeesandme/get/gameboard/begin', {
        method: 'GET',
    })
    .then(function(res) {
        if(res.ok === true)
            return res.json();
        else
            return "ERROR";
    })
    .then(function(text) {
        activePlayer = text;
        gameBoardSetup();
    })
    .catch(function(err) {
        console.log("Error: cannot start game");
    });
}
function playGame() {
    document.getElementById('vsScreen').className = "";
    document.getElementById('gameBoard').className = "";
    //makeCardsFan("you", 1);
    document.getElementById('deckSelector').className = "hidden";
    setTimeout(function playrealGame() {
        document.getElementById('vsScreen').className = "hidden";
        document.getElementById('replaceCardScreen').className = "";
        activateReplaceCards();
    }, 3000);
}

function gotoDeckBuilder() {
    deckbuilderSelectAndDeselectHero();

    document.getElementById('heroChooser').className = "hidden";
    document.getElementById('deckbuilder').className = "";
}

function deckbuilderSelectAndDeselectHero() {
    let heroes = document.querySelectorAll("#deckbuilder #hero a");

    for (let i = 0; i < heroes.length; i++) {
        console.log("Gets in here: " + selectedHero);
        if (heroes[i].innerText === selectedHero) {
            heroes[i].style.backgroundColor = "grey";
        }
        else {
            heroes[i].style.backgroundColor = "white";
        }
    }
}

function handleDeckSelection(deckName) {
    fetch('/threebeesandme/post/heroanddeckselector/deck', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: deckName
    })
    .then(function(res) {
        if(res.ok === true)
            return res.json();
        else
            return "ERROR";
    })
    .then(function(text) {
        makeGame();
    })
    .catch(function(err) {
        console.log("Error: Could not send the selected deck :'(");
    });

}