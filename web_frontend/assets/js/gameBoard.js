"use strict";
document.addEventListener("DOMContentLoaded", init);

function init() {
    setBackground();
    overLapCards("you", 5);
    overLapCards("enemy", -5);

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

function overLapCards(parentClass, grad) {
    let parent = document.getElementsByClassName(parentClass);
    let cards = document.querySelectorAll(`.${parentClass} .cards li`);
    let totalWidth = 126;       // value in vh
    let totalFanWidth = 35;     // value in vh
    let cardWidth = 11;         // value in vh
    let extraLeft = (totalWidth-totalFanWidth)/2;
    let diff = cardWidth * cards.length - totalFanWidth;


    let offset = cardWidth - diff / (cards.length-1);

    let minIndex = 0;
    let amountOfCards = cards.length;

    let gradAddOnPerCard = grad;   // value in grad
    let currentGrad = -gradAddOnPerCard * Math.floor(amountOfCards/2);

    if(diff > 0) {
        for(let i = minIndex; i < amountOfCards; i++) {
            cards[i].style.left = (offset*i + extraLeft) + "vh";
            cards[i].style.transform = "rotate(" + currentGrad + "grad)";
            console.log("Card adjusted: " + i);

            currentGrad += gradAddOnPerCard;
        }
    }
    else{
        for(let i = minIndex; i < amountOfCards; i++) {
            cards[i].style.position = "relative";
        }
    }

    console.log(offset);

    // 177.77vh 100vh


}

function setBackground() {
    let rand = getRandomInt(2) + 1;

    document.getElementById("gameBoard").style.background = "no-repeat black url(assets/media/board_0" + rand + ".jpg) center center";
    document.getElementById("gameBoard").style.backgroundSize = "177.77vh 100vh";
}

function getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max));
}