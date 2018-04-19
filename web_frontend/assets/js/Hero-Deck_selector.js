"use strict";

document.addEventListener('DOMContentLoaded', init);

function init() {
    loadHeroes();
}

function loadHeroes() {
    let heroes = ["Mage", "Paladin"];
    for (let i = 0; i < heroes.length; i++) {
        document.querySelector('#heroes').innerHTML += "<li><a href='#' id='class"+heroes[i]+"'>"+heroes[i]+"</a></li>";
        document.querySelector('#class'+heroes[i]).style.background = "url('images/portrait"+heroes[i]+".png') center center no-repeat";
        document.querySelector('#class'+heroes[i]).style.backgroundSize = "150%"
    }
}