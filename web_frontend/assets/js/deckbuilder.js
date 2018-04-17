"use strict";

document.addEventListener('DOMContentLoaded', init);
let tutorialli = 1;

function volgendeTutorial() {

    document.querySelector('#tutorials li:nth-child(' +tutorialli+ ')').className = "hidden";
    tutorialli += 1;
    document.querySelector('#tutorials li:nth-child(' + tutorialli + ')').className = "";
    if (tutorialli > 11){
        document.getElementById('next').removeEventListener('click', volgendeTutorial);
        document.querySelector('#tutorials').className = "hidden";
    }
}

function tutorial() {
    document.querySelector('#tutorials').className = "";
    document.getElementById('next').addEventListener('click', volgendeTutorial);

}

function init() {
    tutorial();


    document.querySelector('#firstadd').addEventListener('click', firstadd);
    mockCards();
    checkallcards();
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
function checkallcards() {
    let cardInDeck = document.querySelectorAll(".cardInDeck");
    for(let i = 0 ; i < cardInDeck.length; i++){
        cardInDeck[i].addEventListener('dblclick', addCardToDeck);
        cardInDeck[i].addEventListener('mouseover', detailOfCard);
        cardInDeck[i].addEventListener('mouseout', nodetailOfCard);
    }
    let chosenCards = document.querySelectorAll(".chosenCards");
    for(let i = 0 ; i < chosenCards.length; i++){
        chosenCards[i].addEventListener('dblclick', removeCardFromDeck);
        let noImg = chosenCards[i].lastChild.lastChild;
        noImg.style.display = 'none';
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
    /* apart scherm dat vraagt of deck moet opgeslaan worden*/
    document.querySelector('.main').classList.toggle('hidden');
    document.querySelector('.save').classList.toggle('hidden');
}
function mockCards() {
    let total = 5;
    for (let i = 1; i <= total; i++) {
        mockCard('card'+i);
    }
}

function mockCard(card) {
   document.getElementById('cards').innerHTML += "<li class ='cardInDeck'><figure><img src='images/"+card+".png' alt="+card+" title="+card+"><img src='images/"+card+".png' alt="+card+" title="+card+"></figure></li>";
}

function addCardToDeck(e) {
    document.getElementById("deck").innerHTML += "<li class='chosenCards'>"+this.innerHTML+"</li>";
    checkallcards();
}

function removeCardFromDeck(e) { //remove eventlistener niet vergeten (nu nog zonder)
    this.parentNode.removeChild(this);
    checkallcards();
}
function nodetailOfCard(e){
    this.lastChild.lastChild.style.display = 'none';
}

function detailOfCard(e) {
    this.lastChild.lastChild.style.display = 'block';
}