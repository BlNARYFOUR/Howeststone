"use strict";

document.addEventListener('DOMContentLoaded', init);

function main() {
    /* niet juist*/

}

function gotoCardsReplaced() {
    document.getElementById('replaceCardScreen').className = "hidden";
    firstTurn();
}

function init() {
    // register
    // login
    document.getElementById('gotoHeroSelector').addEventListener('click', gotoHeroSelector);
    document.getElementById('gotoDeckChooser').addEventListener('click', gotoDeckChooser);
    document.getElementById('gotoOptions').addEventListener('click', gotoOptions);
// gotoLogout
    document.getElementById('gotoDeckSelector').addEventListener('click', gotoDeckSelector);
    let mainMenugoers = document.querySelectorAll('.gotoMainMenu');
    for(let i =0; i< mainMenugoers.length; i++){
        mainMenugoers[i].addEventListener('click', gotoMainMenu);
    }
    document.getElementById('gotoRechooseDeck').addEventListener('click', gotoRechooseDeck); /////
    document.getElementById('gotoDeckBuilder').addEventListener('click', gotoDeckBuilder);
    document.getElementById('playGame').addEventListener('click', playGame);
    /* document.getElementById('gotoGameOptions').addEventListener('click', gotoGameOptions);*/
    document.getElementById('gotoCredits').addEventListener('click', gotoCredits);
    document.getElementById('gotoLeaveOptions').addEventListener('click', gotoLeaveOptions);
    document.getElementById('addDeck').addEventListener('click', addDeck);
    document.getElementById('gotoHeroChooser').addEventListener('click', gotoHeroChooser);
    document.getElementById('classMage').addEventListener('click', selectClassMage);
    document.getElementById('classMageDeckChooser').addEventListener('click', selectClassMageInDeckChooser);
    document.querySelector('#hero a:first-child').addEventListener('click', selectClassMageInDeckChooser);
    document.getElementById('classPaladinDeckChooser').addEventListener('click', selectClassPaladinInDeckChooser);
    document.querySelector('#hero a:last-child').addEventListener('click', selectClassPaladinInDeckChooser);
    document.getElementById('classPaladin').addEventListener('click', selectClassPaladin);
    document.getElementById('toggleFullScreen').addEventListener('click', toggleFullScreen);
    document.getElementById('gotoCardsReplaced').addEventListener('click', gotoCardsReplaced);
    document.getElementById('replacedCard1').addEventListener('click', replaceCard1);
    document.getElementById('replacedCard2').addEventListener('click', replaceCard2);
    document.getElementById('replacedCard3').addEventListener('click', replaceCard3);

    /*
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
    document.getElementById('tutorial11button').addEventListener('click', gotoLeaveTutorial);*/
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
    console.log(this.parentElement.parentElement.parentElement);
    document.getElementById('mainMenu').className = "";
    this.parentElement.parentElement.parentElement.className = "hidden";
}
function gotoDeckChooser() {
    document.getElementById('deckChooser').className = "";
    document.getElementById('mainMenu').className = "hidden";
}

function gotoGameOptions() {
    document.getElementById('gameBoard').className = "hidden";
    document.getElementById('options').className = "";
}

function gotoRechooseDeck() {
    document.getElementById('deckChooser').className = "";
    document.getElementById('deckbuilder').className = "hidden";
}

function playGame() {
    setBackground();
    document.getElementById('vsScreen').className = "";
    document.getElementById('gameBoard').className = "";
	makeCardsFan("you", 1);
    document.getElementById('deckSelector').className = "hidden";
    setTimeout(function playrealGame() {
        document.getElementById('vsScreen').className = "hidden";
        document.getElementById('replaceCardScreen').className = "";
    }, 3000);
}

function replaceCard1() {
    document.getElementById('replacedCard1').className = "";
}

function replaceCard2() {
    document.getElementById('replacedCard2').className = "";
}

function replaceCard3() {
    document.getElementById('replacedCard3').className = "";
}

function gotoCardsReplaced() {
    document.getElementById('replaceCardScreen').className = "hidden";
}


function gotoHome() {
    document.getElementById('mainMenu').className = "";
    document.getElementById('heroSelector').className = "hidden";
}

function gotoHeroSelector() {
    setTimeout(goto, 4800);     // This should be shown while loading ;)
    startLoadingScreen();

    function goto() {
        stopLoadingScreen();
        document.getElementById('mainMenu').className = "hidden";
        document.getElementById('heroSelector').className = "";
    }
}

function gotoDeckSelector() {
    document.getElementById('deckSelector').className = "";
    document.getElementById('heroSelector').className = "hidden";
}

function gotoHeroChooser() {
    document.getElementById('deckSelector').className = "hidden";
    document.getElementById('heroSelector').className = "";
}

function selectClassMage() {
    let selectedHeroName = document.getElementsByClassName("selectedHeroName");
    for (let i = 0; i < selectedHeroName.length; i++) {
        selectedHeroName[i].innerHTML = 'Mage Annie';
    }
    selectedHero.style.backgroundImage = "url('images/portraitMage.png')";
    selectedDeck.style.backgroundImage = "url('images/portraitMage.png')";
    playerHero.innerHTML = "<img src='images/portraitMage.png' alt='playerHero' title='playerhero'>";
}

function selectClassPaladin() {
    let selectedHeroName = document.getElementsByClassName("selectedHeroName");
    for (let i = 0; i < selectedHeroName.length; i++) {
        selectedHeroName[i].innerHTML = 'Paladin Azir';
    }
    selectedHero.style.backgroundImage = "url('images/portraitPaladin.png')";
    selectedDeck.style.backgroundImage = "url('images/portraitPaladin.png')";
    playerHero.innerHTML = "<img src='images/portraitPaladin.png' alt='playerHero' title='playerhero'>"
}

function selectClassMageInDeckChooser() {
    document.querySelector('#hero a:first-child').style.backgroundColor = 'grey';
    document.querySelector('#hero a:last-child').style.backgroundColor = 'white';
    document.getElementById("selectedHeroNameInDeckChooser").innerHTML = 'Mage Annie';
    selectedHeroInDeckChooser.style.backgroundImage = "url('images/portraitMage.png')";
}

function selectClassPaladinInDeckChooser() {
    document.querySelector('#hero a:first-child').style.backgroundColor = 'white';
    document.querySelector('#hero a:last-child').style.backgroundColor = 'grey';
    document.getElementById("selectedHeroNameInDeckChooser").innerHTML = 'Paladin Azir';
    selectedHeroInDeckChooser.style.backgroundImage = "url('images/portraitPaladin.png')";
}


function gotoDeckBuilder() {
    if ((document.getElementById('selectedHeroInDeckChooser').getAttribute('style') === null) || (document.getElementById('selectedHeroInDeckChooser').getAttribute('style').indexOf('aladin') === -1)){
        selectClassMageInDeckChooser();
    }else {
        selectClassPaladinInDeckChooser();
    }
    document.getElementById('deckChooser').className = "hidden";
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
    document.getElementById('options').className = "";
    document.getElementById('creditsScreen').className = "hidden";
}

function addDeck() {
    document.getElementById('deckSelector').className = "hidden";
    document.getElementById('deckbuilder').className = "";
    document.getElementById('gotoRechooseDeck').removeEventListener('click', gotoRechooseDeck);
    document.getElementById('gotoRechooseDeck').setAttribute("id", "gotoChooseDeck");
    document.getElementById('gotoChooseDeck').addEventListener('click', gotoChooseDeck);
}

function gotoChooseDeck() {
    document.getElementById('gotoChooseDeck').removeEventListener('click', gotoChooseDeck);
    document.getElementById('gotoChooseDeck').setAttribute("id", "gotoRechooseDeck");
    document.getElementById('deckSelector').className = "";
    document.getElementById('deckbuilder').className = "hidden";
    document.getElementById('gotoRechooseDeck').addEventListener('click', gotoRechooseDeck);
}
