"use strict";

document.addEventListener('DOMContentLoaded', init);

function init() {
    document.getElementById('gotoPlay').addEventListener('click', gotoPlay);
    document.getElementById('gotoHome').addEventListener('click', gotoHome);
    document.getElementById('gotoDeck').addEventListener('click', gotoDeck);
    document.getElementById('gotoHero').addEventListener('click', gotoHero);
    document.getElementById('gotoDeckBuilder').addEventListener('click', gotoDeckBuilder);
    document.getElementById('gotoNoDeck').addEventListener('click', gotoNoDeck);
    document.getElementById('addDeck').addEventListener('click', addDeck);
    document.getElementById('gotoOptions').addEventListener('click', gotoOptions);
    document.getElementById('gotoLeaveOptions').addEventListener('click', gotoLeaveOptions);
    document.getElementById('gotoCredits').addEventListener('click', gotoCredits);
    document.getElementById('playGame').addEventListener('click', playGame);
    document.getElementById('toggleFullScreen').addEventListener('click', toggleFullScreen);
    document.getElementById('gotoMainMenu').addEventListener('click', gotoMainMenu);
    document.getElementById('tutorial1button').addEventListener('click', gotoTutorial2);
    document.getElementById('tutorial2button').addEventListener('click', gotoTutorial3);
    document.getElementById('tutorial3button').addEventListener('click', gotoTutorial4);
    document.getElementById('tutorial4button').addEventListener('click', gotoTutorial5);
    document.getElementById('tutorial5button').addEventListener('click', gotoTutorial6);
    document.getElementById('tutorial6button').addEventListener('click', gotoTutorial7);
    document.getElementById('tutorial7button').addEventListener('click', gotoTutorial8);
    document.getElementById('tutorial8button').addEventListener('click', gotoTutorial9);
    document.getElementById('tutorial9button').addEventListener('click', gotoTutorial10);
    document.getElementById('tutorial10button').addEventListener('click', gotoTutorial11);
    document.getElementById('tutorial11button').addEventListener('click', gotoLeaveTutorial);
}

function toggleFullScreen() {
    let elem = document.querySelector('html');

    if (!document.fullscreenElement && !document.mozFullScreenElement && !document.webkitFullscreenElement && !document.msFullscreenElement ) {
        if (elem.requestFullscreen) {
            elem.requestFullscreen();
        } else if (elem.msRequestFullscreen) {
            elem.msRequestFullscreen();
        } else if (elem.mozRequestFullScreen) {
            elem.mozRequestFullScreen();
        } else if (elem.webkitRequestFullscreen) {
            elem.webkitRequestFullscreen();
        }
    } else {
        if (document.exitFullscreen) {
            document.exitFullscreen();
        } else if (document.msExitFullscreen) {
            document.msExitFullscreen();
        } else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        } else if (document.webkitExitFullscreen) {
            document.webkitExitFullscreen();
        }
    }
}
function gotoMainMenu() {
    document.getElementById('options').className = "";
    document.getElementById('creditsScreen').className = "hidden";
}

function playGame() {
    document.getElementById('vsScreen').className = "";
    document.getElementById('deckPicker').className = "hidden";
    setTimeout(function playrealGame() {
        document.getElementById('playField').className = "";
        document.getElementById('vsScreen').className = "hidden";
    }, 3000);

}

function gotoHome() {
    document.getElementById('mainMenu').className = "";
    document.getElementById('heroPicker').className = "hidden";
}

function gotoPlay() {
    setTimeout(goto, 4800);     // This should be shown while loading ;)
    startLoadingScreen();

    function goto() {
        stopLoadingScreen();
        document.getElementById('mainMenu').className = "hidden";
        document.getElementById('heroPicker').className = "";
    }
}

function gotoDeck() {
    document.getElementById('deckPicker').className = "";
    document.getElementById('heroPicker').className = "hidden";
}

function gotoHero() {
    document.getElementById('deckPicker').className = "hidden";
    document.getElementById('heroPicker').className = "";
}

function gotoDeckBuilder() {
    // ontbrekende htmlpagina
    document.getElementById('mainMenu').className = "hidden";
    document.getElementById('deckbuilder').className = "";
}

function gotoTutorial2() {
    document.getElementById('tutorial1').className = "hidden";
    document.getElementById('tutorial2').className = "";
}
function gotoTutorial3() {
    document.getElementById('tutorial2').className = "hidden";
    document.getElementById('tutorial3').className = "";
}
function gotoTutorial4() {
    document.getElementById('tutorial3').className = "hidden";
    document.getElementById('tutorial4').className = "";
}
function gotoTutorial5() {
    document.getElementById('tutorial4').className = "hidden";
    document.getElementById('tutorial5').className = "";
}
function gotoTutorial6() {
    document.getElementById('tutorial5').className = "hidden";
    document.getElementById('tutorial6').className = "";
}
function gotoTutorial7() {
    document.getElementById('tutorial6').className = "hidden";
    document.getElementById('tutorial7').className = "";
}
function gotoTutorial8() {
    document.getElementById('tutorial7').className = "hidden";
    document.getElementById('tutorial8').className = "";
}
function gotoTutorial9() {
    document.getElementById('tutorial8').className = "hidden";
    document.getElementById('tutorial9').className = "";
}
function gotoTutorial10() {
    document.getElementById('tutorial9').className = "hidden";
    document.getElementById('tutorial10').className = "";
}
function gotoTutorial11() {
    document.getElementById('tutorial10').className = "hidden";
    document.getElementById('tutorial11').className = "";
}
function gotoLeaveTutorial() {
    document.getElementById('tutorial11').className = "hidden";
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
    document.getElementById('mainMenu').className = "";
    document.getElementById('options').className = "hidden";
}

function gotoNoDeck() {
    //
    document.getElementById('mainMenu').className = "";
    document.getElementById('deckbuilder').className = "hidden";
}

function addDeck() {
    document.getElementById('deckPicker').className = "hidden";
    document.getElementById('deckbuilder').className = "";
    document.getElementById('gotoNoDeck').removeEventListener('click', gotoNoDeck);
    document.getElementById('gotoNoDeck').setAttribute("id", "gotoChooseDeck");
    document.getElementById('gotoChooseDeck').addEventListener('click', gotoChooseDeck);
}

function gotoChooseDeck() {
    document.getElementById('gotoChooseDeck').removeEventListener('click', gotoChooseDeck);
    document.getElementById('gotoChooseDeck').setAttribute("id", "gotoNoDeck");
    document.getElementById('deckPicker').className = "";
    document.getElementById('deckbuilder').className = "hidden";
    document.getElementById('gotoNoDeck').addEventListener('click', gotoNoDeck);
}
