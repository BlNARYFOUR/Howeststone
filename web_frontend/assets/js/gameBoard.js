"use strict";
document.addEventListener("DOMContentLoaded", init);
let cardCounter = 0;
let cardBackUrl = "";

function init() {
    //document.querySelector("#tempButtonAddCard").addEventListener('click', addCard);
    setBackground();
    getRandomCardBack();
    makeCardsFan("you", 1);

    /*
    document.getElementById("spark").addEventListener("click", burnFuse);
    document.getElementById("fuse").addEventListener("click", burnFuse);
    */
}

/*
function burnFuse(e) {
    document.getElementById("spark").classList.add("burn");
    document.getElementById("fuse").classList.add("burn");
}
*/

function updateEnemyCards(amountOfCards) {
    let cards = document.querySelector("#gameBoard .enemy .cards ul");
    cards.innerHTML = "";

    for(let i=0; i<amountOfCards; i++) {
        cards.innerHTML += "<li>Card " + (i+1) + "</li>";
    }

    setCards("enemy", cardBackUrl);
    makeCardsFan("enemy", -1);
}

function updateEnemyMana(activeMana, totalMana) {
    document.querySelector("#gameBoard .enemy .manaTotal").innerText = activeMana + "/" + totalMana;
}

function updateMyMana(activeMana, totalMana) {
    let manaList = document.querySelector("#gameBoard .you .manaHolder ul");
    document.querySelector("#gameBoard .you .manaTotal").innerText = activeMana + "/" + totalMana;

    manaList.innerHTML = "";

    for(let i=0; i<activeMana; i++) {
        manaList.innerHTML += '<li class="mana"></li>\r\n';
    }

    for(let i=activeMana; i<totalMana; i++) {
        manaList.innerHTML += '<li class="usedMana"></li>\r\n';
    }
}

function setCards(parent, cardUrl) {
    let cardBacks = document.querySelectorAll(`#gameBoard .${parent} .cards li`);

    for(let i=0; i<cardBacks.length; i++) {
        cardBacks[i].style.background = "no-repeat url(\"" + cardUrl + "\") center 74%";
        cardBacks[i].style.backgroundSize = "125%";
        cardBacks[i].style.color = "transparent";
        cardBacks[i].style.border = "none";
    }
}

function getRandomCardBack() {
    fetch('https://omgvamp-hearthstone-v1.p.mashape.com/cardbacks', {
        method: 'GET',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json',
            'X-Mashape-Key': '5KNrLIRnw4mshXnzDqkRdIvkNbIRp12XlNvjsnNAbrHeNB6jcQ'
        }
    })
    .then(function(res) {
        if(res.ok === true)
            return res.json();
    })
    .then(function(text) {
        let result = text;
        console.log("card backs fetched");
        cardBackUrl = result[0]["img"];
    })
    .catch(function(err) {
        console.log("Error 404: Could not connect to the server");
    });
}

function addCard() {
    cardCounter++;

    if(cardCounter <= 10) {
        document.querySelector('#gameBoard .you .cards ul').innerHTML += "<li>Card " + cardCounter + "</li>";
        makeCardsFan("you", 1);
    }
}

function makeCardsFan(parentClass, gradDirectionIndex) {                                // gradDirectionIndex: Normally -1 or 1
    let parent = document.getElementsByClassName(parentClass);                          // so 'enemy' or 'you'
    let cards = document.querySelectorAll(`#gameBoard .${parentClass} .cards li`);      // select all the list items
    let totalWidth = document.querySelector(`#gameBoard .main`).offsetWidth;            // value in px ( of #gameboard .main )
    let cardWidth;

    try {
        cardWidth = cards[0].offsetWidth;                                               // value in px
    }
    catch(e) {
        console.log(`No cards of ${parentClass} to fan...`);
    }

    let totalFanWidth = 0.3 * totalWidth;                                               // value in px
    let extraLeft = (totalWidth-totalFanWidth) / 2;                                     // amount of padding that needs to be added to centre the fan
    let diff = cardWidth * cards.length - totalFanWidth;                                // total amount of width from cards that needs to be divided
                                                                                        // over cards with overlap
    let overlap = cardWidth - diff / (cards.length-1);                                  // overlap per card

    let minIndex = 0;
    let amountOfCards = cards.length;

    let gradAddOnPerCard = amountOfCards*gradDirectionIndex;                            // value in grad
    let currentGrad = -gradAddOnPerCard * Math.floor(amountOfCards/2);
    let transformOriginHeight;

    if(amountOfCards%2 === 0) {                                                         // make the rotation evenly spread
        currentGrad += gradAddOnPerCard/2
    }

    if(gradDirectionIndex < 0) {
        transformOriginHeight = "top";
    }
    else {
        transformOriginHeight = "bottom";
    }

    if(diff > 0) {
        for(let i = minIndex; i < amountOfCards; i++) {
            if(gradDirectionIndex*currentGrad < 0) {
                cards[i].style.transformOrigin = "right " + transformOriginHeight;
            }
            else {
                cards[i].style.transformOrigin = "left " + transformOriginHeight;
            }

            cards[i].style.position = "absolute";
            cards[i].style.bottom = "-0.5vh";
            cards[i].style.left = (((overlap*i + extraLeft) / window.innerHeight) * 100) + "vh";
            cards[i].style.transform = "rotate(" + currentGrad + "grad)";
            cards[i].classList.remove("notFanned");
            currentGrad += gradAddOnPerCard;
        }
        console.log(`cards of ${parentClass} fanned...`);
    }
    else {
        for(let i = minIndex; i < amountOfCards; i++) {
            cards[i].style.position = "relative";
            cards[i].style.bottom = "-2vh";
            cards[i].style.marginRight = "-2vh";
            cards[i].classList.add("notFanned");
        }
        console.log(`cards of ${parentClass} did not need to be fanned...`);
    }
}

function setBackground() {
    let rand = getRandomInt(2) + 1;

    document.getElementById("gameBoard").style.background = "no-repeat black url(assets/media/board_0" + rand + ".jpg) center center";
    document.getElementById("gameBoard").style.backgroundSize = "177.77vh 100vh";
}

function getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max));
}