"use strict";

document.addEventListener('DOMContentLoaded', init);

function init() {
    loadHeroes();
}

function showHeroes(heroes) {
    let heroesHtml = document.querySelectorAll('.heroes');

    for (let i=0; i<heroes.length; i++) {
        for(let j=0; j<heroesHtml.length; j++) {
            heroesHtml[j].innerHTML += '<li><a href="#" class="' + heroes[i].toLowerCase() + '"><h2>' + heroes[i] + '</h2></a></li>';
        }

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

    console.log(heroName);

    let selectedHeroName = document.querySelectorAll(".selectedHeroName");
    let backgroundHolders = document.querySelectorAll(".selectedHero");

    for(let i = 0; i < selectedHeroName.length; i++) {
        selectedHeroName[i].innerHTML = heroName;
        backgroundHolders[i].style.backgroundImage = `url('assets/media/${heroName}.png')`;
    }

    document.querySelector("#vsScreen .player").innerHTML = `<img src="assets/media/${heroName}.png" alt="playerHero" title="playerHero">`;
}