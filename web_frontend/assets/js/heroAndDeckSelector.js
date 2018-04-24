"use strict";

let selectedHero = "";
let selectedDeck = "";
let selectedDeckObj = null;

document.addEventListener('DOMContentLoaded', init);

function init() {
    loadHeroes();
    loadDecks();

    document.getElementById('gotoDeckSelector').addEventListener('click', gotoDeckSelector);
    document.getElementById('playGame').addEventListener('click', playGame);
    document.getElementById('gotoDeckBuilder').addEventListener('click', gotoDeckBuilder);
}

function showDecks(decks) {
    for (let i=0; i<decks.length; i++) {
            document.querySelector("#deckSelector .decks").innerHTML += '<li><a href="#">' + decks[i] + '</a></li>';

            if(i===0) {
                selectedDeck = decks[i];
            }
    }

    let selectableDecks = document.querySelectorAll('#deckSelector .decks li a');
    for(let i=0; i<selectableDecks.length; i++) {
        selectableDecks[i].addEventListener("click", handleSelectedDeck);
    }
}

function handleSelectedDeck(e) {
    let clickedDeck = this;

    if(selectedDeckObj !== null) {
        selectedDeckObj.style.backgroundColor = "";
    }

    selectedDeckObj = clickedDeck;
    selectedDeck = clickedDeck.innerText;

    console.log(selectedDeck);

    this.style.backgroundColor = "rgba(0, 0, 0, 0.2)";
}

function loadDecks() {
    /*
    fetch('threebeesandme/howeststone/get/decks', {
        method: 'GET'
    })
    .then(function(res) {
        if(res.ok === true)
            return res.json();
    })
    .then(function(text) {
        let result = text;
        showDecks(result)
    })
    .catch(function(err) {
        console.log("Error: Could not load the heroes :'(");
    });
    */

    showDecks(["Some Deck :)", "Deck 1", "Deck 2", "OP 1 shot", "Noob deck"]);
}

function showHeroes(heroes) {
    let heroesHtml = document.querySelectorAll('.heroes');

    for (let i=0; i<heroes.length; i++) {
        if(i===0) {
            selectedHero = heroes[i].toLowerCase();
        }

        for(let j=0; j<heroesHtml.length; j++) {
            heroesHtml[j].innerHTML += '<li><a href="#" class="' + heroes[i].toLowerCase() + '"><h2>' + heroes[i] + '</h2></a></li>';
        }

        document.querySelector("#deckbuilder #hero").innerHTML += '<a href="#">' + heroes[i] + '</a>';
        let heroHtmlObj = document.querySelectorAll('.heroes .' + heroes[i].toLowerCase());

        for(let j=0; j<heroHtmlObj.length; j++) {
            heroHtmlObj[j].style.background = 'url("assets/media/' + heroes[i] + '.png") center center no-repeat';
            heroHtmlObj[j].style.backgroundSize = "145%";
        }
    }

    let selectableHeroes = document.querySelectorAll('.heroes li a');
    for(let i=0; i<selectableHeroes.length; i++) {
        selectableHeroes[i].addEventListener("click", handleSelectedHero);
    }
}

function loadHeroes() {
    /*
    fetch('threebeesandme/howeststone/get/heroes', {
        method: 'GET'
    })
    .then(function(res) {
        if(res.ok === true)
            return res.json();
    })
    .then(function(text) {
        let result = text;
        showHeroes(result)
    })
    .catch(function(err) {
        console.log("Error: Could not load the heroes :'(");
    });
    */

    showHeroes(["Mage", "Paladin"]);    // TODO: Must be replaced later by 'result'
}

function handleSelectedHero(e) {
    let clickedHero = this;
    let heroName = clickedHero.classList[0];
    selectedHero = heroName;

    console.log(heroName);

    let selectedHeroName = document.querySelectorAll(".selectedHeroName");
    let backgroundHolders = document.querySelectorAll(".selectedHero");

    for(let i = 0; i < selectedHeroName.length; i++) {
        selectedHeroName[i].innerHTML = heroName;
        backgroundHolders[i].style.backgroundImage = `url('assets/media/${heroName}.png')`;
    }

    document.querySelector("#vsScreen .player").innerHTML = `<img src="assets/media/${heroName}.png" alt="playerHero" title="playerHero">`;
}

function gotoDeckSelector() {
    document.getElementById('deckSelector').className = "";
    document.getElementById('heroSelector').className = "hidden";

    let heroName = document.querySelector("#heroSelector .selectedHeroName").innerText;

    sendSelectedHero(heroName);
}

function sendSelectedHero(heroName) {
    console.log("Send selected hero: " + heroName);

    /*
    fetch('threebeesandme/howeststone/post/pregame/hero', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'playerHero': heroName                   // TODO: IMPORTANT!!!
        }
    })
    .then(function(res) {
        if(res.ok === true)
            return res.json();
    })
    .then(function(text) {
        let result = text;
        showHeroes(result)
    })
    .catch(function(err) {
        console.log("Error: Could not load the heroes :'(");
    });
    */
}

function playGame() {
    sendSelectedDeck(selectedDeck);
    setBackground();
    document.getElementById('vsScreen').className = "";
    document.getElementById('gameBoard').className = "";
    makeCardsFan("you", 1);
    document.getElementById('deckSelector').className = "hidden";
    setTimeout(function playRealGame() {
        document.getElementById('vsScreen').className = "hidden";
        document.getElementById('replaceCardScreen').className = "";
    }, 3000);
}

function gotoDeckBuilder() {
    let heroes = document.querySelectorAll("#deckbuilder #hero a");

    for(let i=0; i<heroes.length; i++) {
        console.log("Gets in here: " + selectedHero);
        if(heroes[i].innerText.toLowerCase() === selectedHero) {
            heroes[i].style.backgroundColor = "grey";
        }
        else {
            heroes[i].style.backgroundColor = "white";
        }
    }

    document.getElementById('heroChooser').className = "hidden";
    document.getElementById('deckbuilder').className = "";
}

function sendSelectedDeck(deckName) {
    console.log("Send selected deck: " + deckName);

    /*
    fetch('threebeesandme/howeststone/post/pregame/hero', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'deck': deckName                   // TODO: IMPORTANT!!!
        }
    })
    .then(function(res) {
        if(res.ok === true)
            return res.json();
    })
    .then(function(text) {
        let result = text;
        showHeroes(result)
    })
    .catch(function(err) {
        console.log("Error: Could not load the heroes :'(");
    });
    */
}