"use strict";

document.addEventListener('DOMContentLoaded', init);

function init() {
    //loadHeroesDeckChooser();
}

function loadHeroesDeckChooser() {
    let heroes = ["Mage", "Paladin"];
    for (let i = 0; i < heroes.length; i++) {
        document.querySelector('.heroes').innerHTML += '<li><a href="#" class="' + heroes[i].toLowerCase() + '"><h2>' + heroes[i] + '</h2></a></li>';
        document.querySelector('#class' + heroes[i] +'DeckChooser').style.background = "url('images/portrait" + heroes[i] + ".png') center center no-repeat";
        document.querySelector('#class' + heroes[i] +'DeckChooser').style.backgroundSize = "150%"
    }
}