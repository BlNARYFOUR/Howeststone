"use strict";

document.addEventListener('DOMContentLoaded', init);

function init() {
    mockCards();
    //click event: "kaart toevoegen in deckbuilder"
    let cardInDeck = document.querySelectorAll(".cardInDeck");
    for(let i = 0 ; i < cardInDeck.length; i++){
        cardInDeck[i].addEventListener('dblclick', addCardToDeck);
        cardInDeck[i].addEventListener('mouseover', detailOfCard);
        cardInDeck[i].addEventListener('mouseout', nodetailOfCard);
    }


    // click event: "kaart verwijderen in deckbuilder"


    console.log('test');
}
    /*getAllCards();
}

function getAllCards() {
    console.log('test');
    fetch('/API/getAllCards', {headers: {
            'content-type': 'application/json'}})
        .then(function (response) {
        if (response === ok) return response
    }).then(function (text) {
        console.log(text);
    })
}*/


function mockCards() {
    let total = 5;
    for (let i = 1; i <= total; i++) {
        mockCard('card'+i);
        mockCard('card'+i);
    }

}

function mockCard(card) {
   document.getElementById('cards').innerHTML += "<li class ='cardInDeck'><figure><img src='images/"+card+".png' alt="+card+" title="+card+"><img src='images/"+card+".png' alt="+card+" title="+card+"></figure></li>";
}

function addCardToDeck(e) {
    document.getElementById("deck").innerHTML += "<li class='chosenCards'>"+this.innerHTML+"</li>";
   
    let chosenCards = document.querySelectorAll(".chosenCards");
    for(let i = 0 ; i < chosenCards.length; i++){
        chosenCards[i].addEventListener('dblclick', removeCardFromDeck);
    }
}

function removeCardFromDeck(e) { //remove eventlistener niet vergeten (nu nog zonder)
    this.parentNode.removeChild(this);
}
function nodetailOfCard(e){

    this.lastChild.lastChild.style.display = 'none';
}

function detailOfCard(e) {

    this.lastChild.lastChild.style.display = 'block';
}