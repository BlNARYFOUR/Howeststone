"use strict";

document.addEventListener('DOMContentLoaded', init);
let tutorialli = 1;
let dragged;

function nextTutorial() {

    document.querySelector('#tutorials li:nth-child(' +tutorialli+ ')').className = "hidden";
    tutorialli += 1;
    document.querySelector('#tutorials li:nth-child(' + tutorialli + ')').className = "";
    if (tutorialli > 11){
        document.getElementById('next').removeEventListener('click', nextTutorial);
        document.querySelector('#tutorials').className = "hidden";
    }
}

function tutorial() {
    document.querySelector('#tutorials').className = "";
    document.getElementById('next').addEventListener('click', nextTutorial);

}

function searchTest(e) {
    e.preventDefault();
    console.log(document.getElementById('search').value);

    // Declare variables
    let input, filter, ul, li, img, i;
    input = document.getElementById('search');
    filter = input.value.toUpperCase();
    ul = document.getElementById("cards");
    li =ul.getElementsByTagName('li');

    // Loop through all list items, and hide those who don't match the search query
    for (i = 0; i < li.length; i++) {
        img = li[i].getElementsByTagName("img")[0];
        if (img.getAttribute('id').toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}

function init() {
    /*tutorial();*/

    document.getElementById('search').addEventListener('input', searchTest);
    document.querySelector('#firstadd').addEventListener('click', firstadd);

    document.querySelector('#quickfilter a:first-child').addEventListener('click', viewFullDeck);
    document.querySelector('#quickfilter a:nth-child(2)').addEventListener('click', viewMinions);
    document.querySelector('#quickfilter a:nth-child(3)').addEventListener('click', viewSpells);
    document.querySelector('#quickfilter a:nth-child(4)').addEventListener('click', viewWeapons);


    checkallcards();
    let myCards =MOCKMYCARDS() ;
    MockCardsToDeckBuilder(myCards);

    let cardInDeck = document.querySelectorAll('#cards li');
    for (let i = 0; i < cardInDeck.length; i++){
        cardInDeck[i].addEventListener('dragstart', function () {
            dragged = this.innerHTML;
        });
    }
    document.querySelector('#deck').addEventListener('dragover', function(e) { e.preventDefault();});
    document.querySelector('#deck').addEventListener('drop', function () {
        if ((this.getAttribute('id') === 'deck')){
            try{
                if (dragged.indexOf('draggable') !== -1){
                    addCardToDeck(dragged);
                    dragged = null;
                }
            }catch(error){}
        }
    });

    let inputs = document.querySelectorAll('#secondFilter input');
    for (let i = 0 ; i < inputs.length; i++){
        inputs[i].addEventListener('click', disableFilter)
    }
    inputs = document.querySelectorAll('#thirdFilter input');
    for (let i = 0 ; i < inputs.length; i++){
        inputs[i].addEventListener('click', disableFilter)
    }
    inputs = document.querySelectorAll('#fourthFilter input');
    for (let i = 0 ; i < inputs.length; i++){
        inputs[i].addEventListener('click', disableFilter)
    }
    inputs = document.querySelectorAll('#secondFilter input+label');
    for (let i = 0 ; i < inputs.length; i++){
        inputs[i].addEventListener('click', unselectFilter)
    }
    inputs = document.querySelectorAll('#thirdFilter input+label');
    for (let i = 0 ; i < inputs.length; i++){
        inputs[i].addEventListener('click', unselectFilter)
    }
    inputs = document.querySelectorAll('#fourthFilter input+label');
    for (let i = 0 ; i < inputs.length; i++){
        inputs[i].addEventListener('click', unselectFilter)
    }
}

function MockCardsToDeckBuilder(cards) {

    for (let i = 0; i < cards.length; i++) {
        let imgUrl = cards[i]["img"];
        let imgID = cards[i]["name"];
        //console.log(imgID);
        document.getElementById('cards').innerHTML += '<li class ="cardInDeck"><figure draggable=true><img src="'+imgUrl+'" alt="'+imgID+'" title="'+imgID+'" id="'+imgID+'">'+'</figure></li>';
    }
}






function checkallcards() {
    let cardInDeck = document.querySelectorAll(".cardInDeck");
    for(let i = 0 ; i < cardInDeck.length; i++){
        cardInDeck[i].addEventListener('dblclick', addCardToDeck);
        //cardInDeck[i].addEventListener('mouseover', detailOfCard);
        //cardInDeck[i].addEventListener('mouseout', nodetailOfCard);
    }
    let chosenCards = document.querySelectorAll(".chosenCards");
    let lenghtAllCards = document.querySelectorAll(".two").length + chosenCards.length;
    document.getElementById('cardAmount').innerHTML = lenghtAllCards + '/30';
    document.querySelector('#cards').addEventListener('dragover', function( event ) { event.preventDefault();});
    document.querySelector('#cards').addEventListener('drop', function () {
        if ((this.getAttribute('id') === 'cards') && (dragged !== null)){
            removeCardFromDeck(dragged);
            dragged = null;
        }
    });
    for(let i = 0 ; i < chosenCards.length; i++){
        chosenCards[i].addEventListener('dblclick', removeCardFromDeck);
        chosenCards[i].addEventListener('dragstart', function () {
            dragged = this
        });
    }
}

function unselectFilter(e) {
    e.preventDefault();
    document.getElementById(e.target.getAttribute('for')).checked = document.getElementById(e.target.getAttribute('for')).checked !== true;
}
function disableFilter(e) {
    e.preventDefault();
    e.target.checked = e.target.checked !== true;
}
function firstadd() {
    let chosenCards = document.querySelectorAll(".chosenCards");
    if (chosenCards.length === 30){
        document.querySelector('.save').innerHTML = 'full deck';
    }
    /* apart scherm dat vraagt of deck moet opgeslaan worden*/
    document.querySelector('.main').classList.toggle('hidden');
    document.querySelector('.save').classList.toggle('hidden');
}


function addCardToDeck(e) {
    let dit;
    try {
        dit = this.innerHTML;
    }catch(error) {
        dit = e;
    }
    console.log(dit);
    console.log('hier zit error in vind het aub');
    let chosenCards = document.querySelectorAll(".chosenCards");
    let sameCard = 0;
    for(let i = 0; i < chosenCards.length; i++){
        if (dit === chosenCards[i].innerHTML){
            sameCard += 1;
        }
    }

    if (chosenCards.length >= 30){
        console.error('to much cards');
    }
    else {
        if (sameCard === 0){
            document.getElementById("deck").innerHTML += "<li class='chosenCards'>"+dit+"</li>";
            checkallcards();
        } else {
            let cardType = "normal";
            if (sameCard === 1 && cardType !== "legendary"){
                for(let i = 0; i < chosenCards.length; i++){
                    if (dit === chosenCards[i].innerHTML){
                        chosenCards[i].classList.add("two");
                    }
                }
                // change the old one
                checkallcards();
            } else {
                console.error('cannot add more');
            }
        }
    }
}

function removeCardFromDeck(e) { //remove eventlistener niet vergeten (nu nog zonder)
    let dit;
    try {
        let nouse = this.classList;
        dit = this;
    }catch(error) {
        dit = e;
    }
    if (dit.classList.contains('two')){
        dit.classList.remove('two');
    } else {
        dit.parentNode.removeChild(dit);
    }
    checkallcards();
}
function nodetailOfCard(e){
    this.lastChild.lastChild.style.display = 'none';
}

function detailOfCard(e) {
    this.lastChild.lastChild.style.display = 'block';
}

function viewFullDeck() {

    fetch('threebeesandme/howeststone/get/deckbuilder/showfulldeck',{
        method: 'GET',
    })
        .then(function(res) {
            if(res.ok === true)
                return res.json();
        })
        .then(function(text) {
            let result = text;
            console.log("All chosenCards shown");
        })
        .catch(function(err) {
            console.log("Error 404: Could not connect to the server");
        });
}

function viewMinions() {
    fetch('threebeesandme/howeststone/get/deckbuilder/showminions',{
        method: 'GET',
    })
        .then(function(res) {
            if(res.ok === true)
                return res.json();
        })
        .then(function(text) {
            let result = text;
            console.log("Only minions shown");
        })
        .catch(function(err) {
            console.log("Error 404: Could not connect to the server");
        });
}

function viewSpells() {
    fetch('threebeesandme/howeststone/get/deckbuilder/showspells',{
        method: 'GET',
    })
        .then(function(res) {
            if(res.ok === true)
                return res.json();
        })
        .then(function(text) {
            let result = text;
            console.log("Only spells shown");
        })
        .catch(function(err) {
            console.log("Error 404: Could not connect to the server");
        });
}

function viewWeapons() {
    fetch('threebeesandme/howeststone/get/deckbuilder/showweapons',{
        method: 'GET',
    })
        .then(function(res) {
            if(res.ok === true)
                return res.json();
        })
        .then(function(text) {
            let result = text;
            console.log("Only weapons shown");
        })
        .catch(function(err) {
            console.log("Error 404: Could not connect to the server");
        });
}