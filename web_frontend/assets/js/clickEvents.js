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
    document.getElementById('gotoRegister').addEventListener('click', gotoRegister);
    document.getElementById('gotoLogin').addEventListener('click', gotoLogin);
    document.getElementById('submitLogin').addEventListener('click', submitLogin);
    document.getElementById('submitRegister').addEventListener('click', submitRegister);
    document.getElementById('gotoLogOut').addEventListener('click', gotoLogOut);

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
function gotoLogOut() {
    document.alert('logged out');
    document.getElementById('mainMenu').className = "hidden";
    document.getElementById('loginAndRegistrationScreen').className = "";
}
function gotoRegister() {
    document.getElementById('login').className = "hidden";
    document.getElementById('register').className = "";
}
function gotoLogin() {
    document.getElementById('login').className = "";
    document.getElementById('register').className = "hidden";
}
function submitLogin() {
    document.getElementById('loginAndRegistrationScreen').className = "hidden";
    alert('not loged in');
    document.getElementById('mainMenu').className = "";
}
function submitRegister() {
    document.getElementById('loginAndRegistrationScreen').className = "hidden";
    alert('not registered');
    document.getElementById('mainMenu').className = "";
}

function playGame() {
    document.getElementById('vsScreen').className = "";
    document.getElementById('deckPicker').className = "hidden";
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
