"use strict";
document.addEventListener("DOMContentLoaded", init);
let cardCounter = 0;

function init() {
    document.querySelector("#tempButtonAddCard").addEventListener('click', addCard);
    setBackground();
    makeCardsFan("you", 1);
    makeCardsFan("enemy", -1);

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

function addCard() {
    cardCounter++;

    if(cardCounter <= 10) {
        document.querySelector('#gameBoard .you .cards ul').innerHTML += "<li>Card " + cardCounter + "</li>";
        makeCardsFan("you", 1);
    }
}

function makeCardsFan(parentClass, gradDirectionIndex) {                                // gradDirectionIndex: Normally -1 or 1
    let parent = document.getElementsByClassName(parentClass);                          // so 'enemy' or 'you'
    let cards = document.querySelectorAll(`#gameBoard .${parentClass} .cards li`);     // select all the list items
    let totalWidth = document.querySelector(`#gameBoard .main`).offsetWidth;            // value in px ( of #gameboard .main )
    let cardWidth;

    try {
        cardWidth = cards[0].offsetWidth;                                               // value in px
    }
    catch(e) {
        console.log(`No cards of ${parentClass} to fan...`);
    }

    let totalFanWidth = 0.33 * totalWidth;                                              // value in px
    let extraLeft = (totalWidth-totalFanWidth) / 2;                                     // amount of padding that needs to be added to centre the fan
    let diff = cardWidth * cards.length - totalFanWidth;                                // total amount of width from cards that needs to be divided
                                                                                        // over cards with overlap
    let overlap = cardWidth - diff / (cards.length-1);                                  // overlap per card

    let minIndex = 0;
    let amountOfCards = cards.length;

    let gradAddOnPerCard = amountOfCards*gradDirectionIndex;                            // value in grad
    let currentGrad = -gradAddOnPerCard * Math.floor(amountOfCards/2);
    let transformOriginHeight;

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
            cards[i].style.left = (overlap*i + extraLeft) + "px";
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