"use strict";
document.addEventListener("DOMContentLoaded", init);

function init() {
    setBackground();
    overLapCards("you");

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

function overLapCards(parentClass) {
    let parent = document.getElementsByClassName(parentClass);
    let cards = document.querySelectorAll(`.${parentClass} .cards li`);
    let totalFanWidth = 35;     // value in vh
    let cardWidth = 11;         // value in vh
    let diff = cardWidth * cards.length - totalFanWidth;

    if(diff < 0)
        diff = 0;

    let offset = diff / (cards.length-1);

    console.log(offset);

    let minIndex = 0;
    let halfAmount =  Math.floor(cards.length/2);
    let firstHalfIndex = halfAmount-1;
    let amountOfCards = cards.length;
    let secondHalfIndex = amountOfCards-(halfAmount);

    for(let i = minIndex; i < halfAmount; i++) {
        cards[i].style.transform = "translateX(" + (offset*(firstHalfIndex-i) + offset/2) + "vh)";
    }

    for(let i = secondHalfIndex; i < amountOfCards; i++) {
        cards[i].style.transform = "translateX(-" + (offset*(i-halfAmount) + offset/2) + "vh)";
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