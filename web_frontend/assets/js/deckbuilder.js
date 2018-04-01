"use strict";

document.addEventListener('DOMContentLoaded', init);

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

function init() {
    document.querySelector('#firstadd').addEventListener('click', firstadd);
    mockCards();
    checkallcards();
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