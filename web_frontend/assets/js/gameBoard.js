"use strict";

document.addEventListener("DOMContentLoaded", init);

let cardCounter = 0;
let cardBackUrl = "";
let dragSrcEl = null;

function init() {
    setupDraggingOfCards();
    //document.querySelector("#tempButtonAddCard").addEventListener('click', addCard);
    setBackground();
    getRandomCardBack();
    makeCardsFan("you", 1);

    /*
    document.getElementById("spark").addEventListener("click", burnFuse);
    document.getElementById("fuse").addEventListener("click", burnFuse);
    */
}



function firstTurn() {
    updateEnemyMana(0, 0);
    updateEnemyCards(5);
    updateMyMana(0, 0);
    updateCards(3,'you', 1);
    setTimeout(yourTurn(), 10000);
}
function yourTurn() {
    console.log("You're turn");
    updateMyMana(1, 1);
    updateCards(4,'you', 1);
}

function burnFuse(e) {
    document.getElementById("fuse").classList.add("burn");
}

function updateEnemyHero() {
    updateHero("enemy");
}

function updateMyHero() {
    updateHero("you");
}

function updateHero(parent) {
    let heroName = "";

    // here will come a fetch to get the hero name
    heroName = "mage";

    showHero(parent, heroName);
}

function showHero(parent, heroName) {
    let hero = document.querySelector(`#gameBoard .${parent} .hero`);

    hero.style.background = `no-repeat url("assets/media/${heroName}.png") center center`;
    hero.style.backgroundSize = "contain";
}

function updateEnemyCards(amountOfCards) {
    updateCards(amountOfCards, "enemy", -1);
    setCards("enemy", cardBackUrl);
}

function updateMyCards(amountOfCards) {
    updateCards(amountOfCards, "you", 1);
    setupDraggingOfCards();
}

function getMyCardDetails() {
    // This is the function where you will get all card images (in order), names, details, ... (JSON format)
}

function updateCards(amountOfCards, parent, gradDirectionIndex) {
    let cards = document.querySelector(`#gameBoard .${parent} .cards ul`);
    cards.innerHTML = "";

    let addOn = "";
    if(parent === "you") {
        addOn = 'draggable="true"';
    }

    for(let i=0; i<amountOfCards; i++) {
        cards.innerHTML += `<li ${addOn}>Card ` + (i+1) + "</li>";
    }

    makeCardsFan(parent, gradDirectionIndex);
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
	cardBackUrl = "assets/media/cardBackDefault.png";
	
	// WORKED, but API has broken (?)
	/*
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
	*/
}

function addCard() {
    cardCounter++;

    if(cardCounter <= 10) {
        document.querySelector('#gameBoard .you .cards ul').innerHTML += "<li>Card " + cardCounter + "</li>";
        makeCardsFan("you", 1);
    }

    setupDraggingOfCards();
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
            cards[i].style = "";

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
            cards[i].style = "";

            cards[i].style.position = "absolute";
            cards[i].style.bottom = "-2vh";

            let buf = (cardWidth/window.innerHeight*100)/(amountOfCards%2+1);               // 50% - cardWith when even, 50% - cardWith/2 when uneven (center aligning)
            buf -= (cardWidth/window.innerHeight*100)*(parseInt(amountOfCards/2)-i);        // align card according to its position

            cards[i].style.left ="calc(50% - " + buf + "vh)";
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

function setupDraggingOfCards() {
    let dragged;
    let copyOfDragged;

    let cards = document.querySelectorAll("#gameBoard .you .cards ul li");

    document.draggable = false;

    for(let i=0; i<cards.length; i++) {
        cards[i].addEventListener("dragstart", handleDragStart, false);
        cards[i].addEventListener("drag", handleDrag, false);
        cards[i].addEventListener("dragend", handleDragEnd, false);
        cards[i].addEventListener("dragover", handleDragOver, false);
        cards[i].addEventListener("dblclick", handleDoubleClickAsDrop, false);
    }

    document.querySelector("#gameBoard .you .playingField .dropZone").addEventListener("dragover", handleDragOver, false);
    document.querySelector("#gameBoard .you .playingField .dropZone").addEventListener("drop", handleDrop, false);

}

function handleDragStart(e) {
    e.stopImmediatePropagation();
    dragSrcEl = e.target;

    e.dataTransfer.effectAllowed = 'move';
    e.dataTransfer.setData('text/html', this.innerHTML);

    e.target.classList.add("dragging");
    e.target.parentNode.style.opacity = "1";
    e.dataTransfer.setDragImage(dragSrcEl, 85, 135);
}

function handleDrag(e) {
    e.stopImmediatePropagation();
    e.target.classList.add("hide");
}

function handleDragEnd(e) {
    e.target.classList.remove("hide");
    e.target.classList.remove("dragging");
}

function handleDragOver(e) {
    e.preventDefault();
}

function handleDrop(e) {
    e.stopPropagation();

    if ( e.target.className === "dropZone" ) {
        dropInDropZone(dragSrcEl, e.target);
        makeCardsFan("you", 1);
    }
}

function handleDoubleClickAsDrop(e) {
    dropInDropZone(e.target, document.querySelector("#gameBoard .you .playingField .dropZone"));
}

function dropInDropZone(dragSrcElement, dropZoneElement) {
    dragSrcElement.draggable = false;
    dragSrcElement.style = "";
    dragSrcElement.parentNode.removeChild(dragSrcElement);
    dropZoneElement.appendChild(dragSrcElement);
}