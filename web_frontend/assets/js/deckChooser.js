"use strict";

document.addEventListener('DOMContentLoaded', init);

function init() {
    loadHeroesDeckChooser();
}

function loadHeroesDeckChooser() {
    let heroes = ["Mage", "Paladin"];
    for (let i = 0; i < heroes.length; i++) {
        document.querySelector('#listOfHeroes').innerHTML += "<li><a href='#' id='class" + heroes[i] + "DeckChooser'>" + heroes[i] + "</a></li>";
        document.querySelector('#class' + heroes[i] +'DeckChooser').style.background = "url('images/portrait" + heroes[i] + ".png') center center no-repeat";
        document.querySelector('#class' + heroes[i] +'DeckChooser').style.backgroundSize = "150%"
    }
}