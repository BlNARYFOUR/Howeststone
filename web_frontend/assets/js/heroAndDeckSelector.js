"use strict";

document.addEventListener('DOMContentLoaded', init);

function init() {
    loadHeroes();
    document.querySelectorAll('#heroes');
}

function showHeroes(heroes) {
    for (let i = 0; i < heroes.length; i++) {
        document.querySelector('.heroes').innerHTML += '<li><a href="#" class="' + heroes[i].toLowerCase() + '"><h2>' + heroes[i] + '</h2></a></li>';

        let heroHtmlObj = document.querySelector('.heroes .'+heroes[i].toLowerCase());

        heroHtmlObj.style.background = "url('images/portrait"+heroes[i]+".png') center center no-repeat";
        heroHtmlObj.style.backgroundSize = "150%";
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