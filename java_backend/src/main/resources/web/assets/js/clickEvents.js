"use strict";

document.addEventListener('DOMContentLoaded', init);

function main() {
    /* niet juist*/

}

// TODO
function gotoCardsReplaced() {
    if (countReplaceCards !== 0){
        replaceCards(countReplaceCards);
    }
    document.getElementById('replaceCardScreen').className = "hidden";
    deactivateReplaceCards();
    setTimeout(yourTurn, 1000);

}

function init() {
    // register
    // login
    document.getElementById('gotoHeroSelector').addEventListener('click', gotoHeroSelector);
    document.getElementById('gotoDeckChooser').addEventListener('click', gotoDeckChooser);
    document.getElementById('gotoOptions').addEventListener('click', gotoOptions);
// gotoLogout
    let mainMenugoers = document.querySelectorAll('.gotoMainMenu');
    for(let i =0; i< mainMenugoers.length; i++){
        mainMenugoers[i].addEventListener('click', gotoMainMenu);
    }
    document.getElementById('gotoRechooseDeck').addEventListener('click', gotoRechooseDeck);
    /* TODO: document.getElementById('gotoGameOptions').addEventListener('click', gotoGameOptions);*/
    document.getElementById('gotoCredits').addEventListener('click', gotoCredits);
    document.getElementById('gotoLeaveOptions').addEventListener('click', gotoLeaveOptions);
    document.getElementById('gotoHeroChooser').addEventListener('click', gotoHeroChooser);
    document.getElementById('toggleFullScreen').addEventListener('click', toggleFullScreen);
    document.getElementById('gotoCardsReplaced').addEventListener('click', gotoCardsReplaced);

}

function toggleFullScreen() {
    let fullscreen = document.mozFullScreenElement || document.webkitFullscreenElement || null;
    if (navigator.userAgent.indexOf('Firefox') !== -1){
        if (fullscreen === null) {
            document.querySelector('html').mozRequestFullScreen();
        } else {
            document.mozCancelFullScreen();
        }
    } else{
        if (fullscreen === null) {
            document.querySelector('html').webkitRequestFullscreen();
        } else {
            document.webkitExitFullscreen();
        }
    }
}

function gotoMainMenu(e) {
    //console.log(this.parentElement.parentElement.parentElement);
    document.getElementById('mainMenu').className = "";
    this.parentElement.parentElement.parentElement.className = "hidden";
}

function gotoDeckChooser() {
    document.getElementById('heroChooser').className = "";
    document.getElementById('mainMenu').className = "hidden";
}

// TODO
function gotoGameOptions() {
    document.getElementById('gameBoard').className = "hidden";
    document.getElementById('options').className = "";
}

function gotoRechooseDeck() {
    document.getElementById('heroChooser').className = "";
    document.getElementById('deckbuilder').className = "hidden";
}

function gotoHome() {
    document.getElementById('mainMenu').className = "";
    document.getElementById('heroSelector').className = "hidden";
}

function gotoHeroSelector() {
    setTimeout(goto, 2400);     // This should be shown while loading ;)
    startLoadingScreen();

    function goto() {
        stopLoadingScreen();
        document.getElementById('mainMenu').className = "hidden";
        document.getElementById('heroSelector').className = "";
    }
}

function gotoHeroChooser() {
    document.getElementById('deckSelector').className = "hidden";
    document.getElementById('heroSelector').className = "";
}

function gotoOptions() {
    document.getElementById('mainMenu').className = "hidden";
    document.getElementById('options').className = "";
}

function gotoCredits() {
    document.getElementById('options').className = "hidden";
    document.getElementById('creditsScreen').className = "";
}

function gotoLeaveOptions() {
    document.getElementById('options').className = "";
    document.getElementById('creditsScreen').className = "hidden";
}

function gotoChooseDeck() {
    document.getElementById('gotoChooseDeck').removeEventListener('click', gotoChooseDeck);
    document.getElementById('gotoChooseDeck').setAttribute("id", "gotoRechooseDeck");
    document.getElementById('deckSelector').className = "";
    document.getElementById('deckbuilder').className = "hidden";
    document.getElementById('gotoRechooseDeck').addEventListener('click', gotoRechooseDeck);
}
