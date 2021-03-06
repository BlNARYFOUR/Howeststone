"use strict";

document.addEventListener('DOMContentLoaded', init);
let tutorialLi = 1;
let dragged;
let cardTypeFilterChecked = false;
let ManaFilterChecked = false;
let cardRarityFilterChecked = false;

function nextTutorial() {
    document.querySelector('#tutorials li:nth-child(' +tutorialLi+ ')').className = "hidden";
    tutorialLi += 1;
    document.querySelector('#tutorials li:nth-child(' + tutorialLi + ')').className = "";
    if (tutorialLi > 11){
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

    // Declare variable
    let cardsInDeck = document.querySelectorAll('#cards li');
    // TODO fetch for search
    // Loop through all list items, and hide those who don't match the search query
    for (let i = 0; i < cardsInDeck.length; i++) {
        let img = cardsInDeck[i].getElementsByTagName("img")[0];
        if (img.getAttribute('id').toUpperCase().indexOf(document.getElementById('search').value.toUpperCase()) > -1) {
            cardsInDeck[i].style.display = "";
        } else {
            cardsInDeck[i].style.display = "none";
        }
    }
}

function donNotSubmit(e) {
    e.preventDefault();
}

function checkIfCardIsGoingToBeAdded() {
    let rectDrag = dragSrcElement.getBoundingClientRect();
    let deckRect = document.querySelector("#deck").getBoundingClientRect();
    if ((rectDrag.right < deckRect.right) && (rectDrag.left > deckRect.left) && (rectDrag.bottom < deckRect.bottom+62) && (rectDrag.top > deckRect.top-103)){
        addCardToDeck(itemThatIsBeingMoved);
    }
    try{
        dragSrcElement.parentElement.removeChild(dragSrcElement);
    } catch (err){
        console.log('nothing can be removed' + err);
    }
    document.removeEventListener("touchmove", movingOfDragElement, false);
    document.removeEventListener("touchend", checkIfCardIsGoingToBeAdded, false);
    document.removeEventListener("mousemove", movingOfDragElement, false);
    document.removeEventListener("mouseup", checkIfCardIsGoingToBeAdded, false);

}
function checkIfCardIsGoingToBeRemoved() {
    try{
        dragSrcElement.parentElement.removeChild(dragSrcElement);
    } catch (err){
        console.log('nothing can be removed' + err);
    }
    document.removeEventListener("touchmove", movingOfDragElement, false);
    document.removeEventListener("touchend", checkIfCardIsGoingToBeRemoved, false);
    document.removeEventListener("mousemove", movingOfDragElement, false);
    document.removeEventListener("mouseup", checkIfCardIsGoingToBeRemoved, false);
    removeCardFromDeck(itemThatIsBeingMoved);
}

function clickOnCardInCards(e) {
    // TODO fetch
    itemThatIsBeingMoved = e.target;
    dragOffsetX = e.offsetX;
    dragOffsetY = e.offsetY;
    moved = false;
    dragSrcElement = e.target.cloneNode(true);
    document.body.appendChild(dragSrcElement);
    dragSrcElement.removeAttribute('style');
    movingOfDragElement(e);
    dragSrcElement.style.zIndex = '1';
    dragSrcElement.style.width = '23vh';
    dragSrcElement.style.height= '34.83713356vh';
    dragSrcElement.style.position = 'absolute';
    dragSrcElement.style.background = e.target.style.background;
    document.removeEventListener("mousedown", clickOnCardInCards);
    document.removeEventListener("touchstart", clickOnCardInCards);
    document.addEventListener("touchmove", movingOfDragElement, false);
    document.addEventListener("mousemove", movingOfDragElement, false);
    document.addEventListener("mouseup", checkIfCardIsGoingToBeAdded, false);
    document.addEventListener("touchend", checkIfCardIsGoingToBeAdded, false);
}

function clickOnCardInDeck(e) {
    // TODO fetch
    itemThatIsBeingMoved = e.target;
    dragOffsetX = e.offsetX;
    dragOffsetY = e.offsetY;
    moved = false;
    dragSrcElement = e.target.cloneNode(true);
    document.body.appendChild(dragSrcElement);
    dragSrcElement.removeAttribute('style');
    movingOfDragElement(e);
    dragSrcElement.style.zIndex = '1';
    dragSrcElement.style.width = '5vh';
    dragSrcElement.style.height= '7.573289904vh';
    dragSrcElement.style.position = 'absolute';
    dragSrcElement.style.background = e.target.style.background;
    document.removeEventListener("mousedown", clickOnCardInCards);
    document.removeEventListener("touchstart", clickOnCardInCards);
    document.addEventListener("touchmove", movingOfDragElement, false);
    document.addEventListener("mousemove", movingOfDragElement, false);
    document.addEventListener("mouseup", checkIfCardIsGoingToBeRemoved, false);
    document.addEventListener("touchend", checkIfCardIsGoingToBeRemoved, false);
}

function init() {
    /*tutorial();*/
    
    document.querySelector('#deckbuilder aside form').addEventListener('submit', donNotSubmit);
    document.getElementById('search').addEventListener('input', searchTest);
    document.getElementById('search').addEventListener('input', search);
    document.getElementById('sort').addEventListener('change', sort);
    document.getElementById('firstFilter').addEventListener('change',filterCards);
    document.getElementById('secondFilter').addEventListener('change',filterCards);
    document.getElementById('thirdFilter').addEventListener('change',filterCards);
    document.getElementById('fourthFilter').addEventListener('change',filterCards);
    document.querySelector('#firstAdd').addEventListener('click', firstAdd);

    let myCards = MOCKMYCARDS() ;
    MockCardsToDeckBuilder(myCards);
    checkAllCards();

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
    // TODO fetch
    for (let i = 0; i < cards.length; i++) {
        let imgUrl = cards[i]["img"];
        let imgID = cards[i]["name"];

        document.getElementById('cards').innerHTML += '<li class ="cardInDeck"><figure><img src="'+imgUrl+'" alt="'+imgID+'" title="'+imgID+'" id="'+imgID+'">'+'</figure></li>';
    }
}


function checkAllCards() {
    let cardsInDeck = document.querySelectorAll('#cards li');
    for (let i = 0; i < cardsInDeck.length; i++){
        cardsInDeck[i].addEventListener("mousedown", clickOnCardInCards);
        cardsInDeck[i].addEventListener("touchstart", clickOnCardInCards);
        cardsInDeck[i].addEventListener('dblclick', addCardToDeckDblClick);
    }
    let chosenCards = document.querySelectorAll(".chosenCards");
    let lengthAllCards = document.querySelectorAll(".two").length + chosenCards.length;
    document.getElementById('cardAmount').innerHTML = lengthAllCards + '/30';
    for (let i = 0; i < chosenCards.length; i++){
        chosenCards[i].addEventListener('dblclick', removeCardFromDeckDblClick);
    }
}

function unselectFilter(e) {
    e.preventDefault();
    document.getElementById(e.target.getAttribute('for')).checked = document.getElementById(e.target.getAttribute('for')).checked !== true;
}
function disableFilter(e) {
    e.preventDefault();
}
function firstAdd() {
    let chosenCards = document.querySelectorAll(".chosenCards");
    if (chosenCards.length === 30){
        document.querySelector('.save').innerHTML = 'full deck';
    }
    document.querySelector('.main').classList.toggle('hidden');
    document.querySelector('.save').classList.toggle('hidden');
}

function addCardToDeckDblClick(e) {
    addCardToDeck(e.target);
    console.log('does this work?')
}
function addCardToDeck(card) {
    let chosenCards = document.querySelectorAll(".chosenCards");
    let images = document.querySelectorAll(".chosenCards img");
    let sameCard = 0;
    for(let i = 0; i < images.length; i++){
        if (card.getAttribute('id') === images[i].getAttribute('id')){
            sameCard += 1;
        }
    }
    if (chosenCards.length >= 30){
        console.error('to much cards');
    }
    else {
        if (sameCard === 0){
            let cloneCard = card.cloneNode(true);
            let cardHolder = document.createElement("li");
            cardHolder.classList.add('chosenCards');
            document.getElementById("deck").appendChild(cardHolder).appendChild(cloneCard);
            checkAllCards();
        } else {
            let cardType = 'normal';
            if (sameCard === 1 && cardType !== "legendary"){
                for(let i = 0; i < images.length; i++){
                    if (card.getAttribute('id') === images[i].getAttribute('id')){
                        images[i].classList.add("two");
                    }
                }
                // change the old one
                checkAllCards();
            } else {
                console.error('cannot add more');
            }
        }
    }
}
function removeCardFromDeckDblClick(e) {
    removeCardFromDeck(e.target)
}

function removeCardFromDeck(card) { //remove eventlistener niet vergeten (nu nog zonder)
    if (card.classList.contains('two')){
        card.classList.remove('two');
    } else {
        card.parentNode.remove();
    }
    checkAllCards();
}

function detailOfCard(e) {
    this.lastChild.lastChild.style.display = 'block';
}

function search(e) {
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

function sort() {
    let sort =  document.getElementById('sort');
    let sortValue = sort.options[sort.selectedIndex].value;

        console.log(sortValue);

        /*fetch('threebeesandme/howeststone/post/deckbuilder/sort?by='+sortValue, {
                method: 'post',
            })
            .then(function(res) {
                if(res.ok === true)
                    return res.json();
            })

            .catch(function(err) {
                console.log("Error 404: Could not connect to the server");
            });*/
}


function filterCards() {
    let specificCardsFilter =  document.getElementsByName('specificCards');
    let cardTypesFilter = document.getElementsByName('cardTypes');
    let ManaFilter = document.getElementsByName('Mana');
    let cardRarityFilter = document.getElementsByName('cardRarity');


    let filterArray = [];


    for (let i = 0; i < specificCardsFilter.length; i++) {
        if (specificCardsFilter[i].checked){
            filterArray.push(specificCardsFilter[i].value);
        }
    }

    for (let i = 0; i < cardTypesFilter.length; i++) {
        if (cardTypesFilter[i].checked){
            filterArray.push(cardTypesFilter[i].value);
            cardTypeFilterChecked = true;
        }
    }
    if (cardTypeFilterChecked === false) {
        filterArray.push(-1);
    }

    for (let i = 0; i < ManaFilter.length; i++) {
        if (ManaFilter[i].checked){
            filterArray.push(ManaFilter[i].value);
            ManaFilterChecked = true;
        }
    }
    if (ManaFilterChecked === false) {
        filterArray.push(-1);
    }

    for (let i = 0; i < cardRarityFilter.length; i++) {
        if (cardRarityFilter[i].checked){
            filterArray.push(cardRarityFilter[i].value);
            cardRarityFilterChecked = true;
        }
    }
    if (cardRarityFilterChecked === false) {
        filterArray.push(-1);
    }

    console.log(filterArray);

    /*fetch('threebeesandme/howeststone/post/deckbuilder/filterCards', {
        method: 'post',
    })
        .then(function(res) {
            if(res.ok === true)
                return res.json();
        })

        .catch(function(err) {
            console.log("Error 404: Could not connect to the server");
        })*/
}
