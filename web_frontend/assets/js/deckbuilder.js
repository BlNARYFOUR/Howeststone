"use strict";

document.addEventListener('DOMContentLoaded', init);

function init() {
    mockCards();
    //click event: "kaart toevoegen in deckbuilder"
    let cardInDeck = document.querySelectorAll(".cardInDeck");
    for(let i = 0 ; i < cardInDeck.length; i++){
        cardInDeck[i].addEventListener('dblclick', addCardToDeck);
    }

    // click event: "kaart verwijderen in deckbuilder"
    //document.querySelector(".chosenCards").addEventListener('dblclick', removeCardFromDeck);
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
   document.getElementById('cards').innerHTML += "<li class ='cardInDeck'><img src='images/"+card+".png' alt="+card+" title="+card+"></li>";
}

function addCardToDeck(e) {
    let target = event.target;
    let parent = target.parentElement;
    console.log(parent.innerHTML);
    document.getElementById("deck").innerHTML += "<li class='chosenCards'><a href='#'>"+parent.innerHTML+"</a></li>";
}

//function removeCardFromDeck(e) {
//    document.getElementById("deck").innerHTML -="<li class='chosenCards'><a href='#'>"+e.target.innerHTML+"</a></li>";
//    document.getElementById("cards").innerHTML +="<li class='cardsInDeck'><a href='#'>"+e.target.innerHTML+"</a></li>";
//}
