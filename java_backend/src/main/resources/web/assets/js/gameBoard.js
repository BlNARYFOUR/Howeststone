"use strict";

document.addEventListener("DOMContentLoaded", init);

let cardCounter = 0;
let cardBackUrl = "";
let myCards = null;
let amountOfEnemyCards = null;
let timeLeftObj = null;

let MOCKTIME = 50;

function init() {
    setupMovingOfCards();
    //document.querySelector("#tempButtonAddCard").addEventListener('click', addCard);
    setBackground();
    getRandomCardBack();
    //makeCardsFan("you", 1);
    document.querySelector(".you .heroPower span").addEventListener('click', useHeroPower);
    setupGameBoard();
}

function setupGameBoard() {
    document.getElementById('endTurn').addEventListener('click', endMyTurn);
    document.getElementById('showBattleLog').addEventListener('click', showOrHideBattleLog);
}

function showOrHideBattleLog(e) {
    e.preventDefault();
    document.querySelector("#gameBoard #battleLog").classList.toggle("hidden");
}

function loadBattleLog() {
    /*
    fetch('http://localhost:4242/threebeesandme/get/gameboard/battlelog', {
        method: 'GET'
    })
    .then(function(res) {
        if(res.ok === true)
            return res.json();
    })
    .then(function(text) {
        let result = text;
        showBattleLog(result)
    })
    .catch(function(err) {
        console.log("Error: Could not load the heroes :'(");
    });
    */

    showBattleLog(["Step attacked Brem",
        "Brem blocked Step",
        "Brem countered with Bert",
        "Brand stopped Bert",
        "Brand got burned",
        "Bert got torched",
        "Step died of exhaustion",
        "Brem got VICTORY"]);
}

function useHeroPower() {
    console.log('kaaaapow!');
    fetch('http://localhost:4242/threebeesandme/get/useheropower', {
        method: 'GET',
        mode: 'no-cors'
    })
        .then(function (res) {
            if (res.ok === true)
                return res.json();
        })
        .then(function (text) {
            let result = text;
            console.log("Heropower is used");
        })
        .catch(function (err) {
            console.log("Error: Could not use heropower");
        });
}

function showBattleLog(logArr) {
    let htmlBattleLogObj = document.querySelector("#gameBoard #battleLog");

    for (let i = 0; i < logArr.length; i++) {
        htmlBattleLogObj.innerHTML += `<li>${logArr[i]}</li>`;
    }
}

function startTimeLeftCheck() {
    // TODO: timeLeftObj = setInterval(timeLeft, 1000);

    MOCKTIME = 50;
    timeLeftObj = setInterval(MOCKTIMELEFT, 1000);
}

function MOCKTIMELEFT() {
    MOCKTIME -= 1;

    if (MOCKTIME <= 20) {
        burnFuse();
    }

    if (MOCKTIME <= 0) {
        endMyTurn();
    }
}

function stopTimeLeftCheck() {
    clearInterval(timeLeftObj);
}

function timeLeft() {
    fetch('http://localhost:4242/threebeesandme/get/timeleft', {
        method: 'GET'
    })
        .then(function (res) {
            if (res.ok === true)
                return res.json();
        })
        .then(function (text) {
            let result = text;
            console.log("Time left was retrieved from the server");
            if (result <= 20) {
                burnFuse();
            }
            else if (result <= 0) {
                endMyTurn();
            }
        })
        .catch(function (err) {
            console.log("Error: Could not get time left");
        });
}

function endMyTurn(e) {
    fetch('/threebeesandme/post/gameboard/endturn',{
        method: 'POST'
    })
        .then(function(res) {
            if(res.ok === true)
                return res.json();
        })
        .then(function(text) {
            let result = text;
            console.log("turn end has been send to server");
            stopTimeLeftCheck();
            stopBurnFuse();
        })
        .catch(function(err) {
            console.log("Error: Could not end turn");
        });
    console.log("turn end has been send to server");
}

function gotoCardsReplaced() {
    let beginCards = document.querySelectorAll('#replaceCardScreen ul li');
    let CardsInHand = [];
    let CardsToDeck = [];

    for (let i = 0; i < beginCards.length; i++) {
        if (beginCards[i].classList.length === 1) {

            CardsInHand.push(
                beginCards[i].classList[0].split("_")[1]);
        } else {
            CardsToDeck.push(
                beginCards[i].classList[0].split("_")[1]);
        }
    }
    let beginCardsJSON = {"CardsInHand": CardsInHand, "CardsToDeck": CardsToDeck};
    replaceCards(beginCardsJSON);
    document.getElementById('replaceCardScreen').className = "hidden";
    deactivateReplaceCards();
}

function replaceCards(countReplaceCards) {
    fetch('/threebeesandme/post/gameboard/replacecards', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(countReplaceCards)
    })
        .then(function (res) {
            if (res.ok === true)
                return res.json();
            else
                return "ERROR";
        })
        .then(function (text) {
            console.log(activePlayer);
            if(activePlayer === "enemy"){
                enemyTurn();
            }else {
                startMyTurn();
            }
        })
        .catch(function (err) {
            console.log("Error: Could not get the cards in hand");
        });
}

function getHTMLForReplaceCardScreen(cards) {
    let html = '<h1>';
    html += activePlayer + " begin";
    if (activePlayer === "enemy") {
        html += "s";
    }
    html += ' the game</h1><h2>Keep or replace cards</h2><ul>';
    for (let i = 0; i < cards["cards"].length; i++) {
        html += '<li class="card_' + cards["cards"][i]["cardID"] + '"></li>';
    }
    html += '</ul><span class="buttonHolder"><a href="#" class="insideButton" id="gotoCardsReplaced">Continue</a></span>';
    document.querySelector("#replaceCardScreen").innerHTML = html;

}

function getBackgroundImagesForCardScreen(cards) {
    for (let i = 0; i <cards["cards"].length; i++){
        let cardClass = document.querySelectorAll("#replaceCardScreen ul li.card_" + cards["cards"][i]["cardID"]);

        for(let j=0; j<cardClass.length; j++) {
            cardClass[j].style.background = 'no-repeat url("' + cards["cards"][i]["img"] + '") center -5vh';
            cardClass[j].style.backgroundSize = "115%";
        }
    }
}

function getClickeventsForCardScreen() {
    document.getElementById('gotoCardsReplaced').addEventListener('click', gotoCardsReplaced);
    let replaceCards = document.querySelectorAll('#replaceCardScreen ul li');
    for (let i = 0; i < replaceCards.length; i++) {
        replaceCards[i].addEventListener('click', toggleReplaceCard);
    }
}

function activateReplaceCards(cards) {
    getHTMLForReplaceCardScreen(cards);
    getBackgroundImagesForCardScreen(cards);
    getClickeventsForCardScreen();
}

function getReplaceCards() {
    fetch('/threebeesandme/get/gameboard/begincards', {
        method: 'GET',
    })
        .then(function (res) {
            if (res.ok === true)
                return res.json();
            else
                return "ERROR";
        })
        .then(function (text) {
            activateReplaceCards(text);
        })
        .catch(function (err) {
            console.log(err +"Error: cannot get starting cards");
        });
}

function deactivateReplaceCards() {
    document.getElementById('gotoCardsReplaced').removeEventListener('click', gotoCardsReplaced);
    let replaceCards = document.querySelectorAll('#replaceCardScreen ul li');
    for (let i = 0; i < replaceCards.length; i++) {
        replaceCards[i].removeEventListener('click', toggleReplaceCard);
    }
}

function toggleReplaceCard(e) {
    e.target.classList.toggle('selected');
}

function gameBoardSetup() {
    setBackground();
    updateEnemyHeroBeforeGame();
    updateEnemyMana(0, 0);
    updateMyHeroBeforeGame();
    updateMyMana(0, 0);
    playGame();
}

let heroAttack;
function enemyTurn() {
    // TODO fetch
    // get amount of card in hand
    // get max mana
    // get playing field
    // get heroes health
    // weapon yes no

    startMyTurn();
}
function startMyTurn() {
    // TODO fetch
    console.log("You're turn");
    updateEnemyStuff();
    updateMyStuff();
    startTimeLeftCheck();
    heroAttack = true;
}

function updateMyStuff() {
    updateMyCards();
    updateMyMana(1, 1);
    updateMyHeroInGame();
}

function updateEnemyStuff() {
    updateEnemyCards(5);
    updateEnemyMana();
    updateEnemyHeroInGame();
}

function burnFuse() {
    document.getElementById("fuse").classList.add("burn");
}

function stopBurnFuse() {
    document.getElementById("fuse").classList.remove("burn");
}

function updateEnemyHeroBeforeGame() {
    updateHeroBeforeGame("enemy");
}

function updateMyHeroBeforeGame() {
    updateHeroBeforeGame("you");
}

function updateHeroBeforeGame(parent) {
    let url = '/threebeesandme/get/hero?parent=' + parent;
    fetch(url, {
        method: 'get'
    })
    .then(function (res) {
        if (res.ok === true)
            return res.json();
    })
    .then(function (text) {
        let result = text;
        showHero(parent, result);
    })
    .catch(function (err) {
        console.log("Error: Could not get hero");
    });
}

function updateHeroInGame(parent) {
    let url = '/threebeesandme/get/gameboard/herohealth?parent=' + parent;
    fetch(url, {
        method: 'get'
    })
    .then(function (res) {
        if (res.ok === true)
            return res.json();
    })
    .then(function (text) {
        let result = text;
        console.log(`${parent} hero-health has been updated`);
        showHeroHealth(parent, result);
    })
    .catch(function (err) {
        console.log("Error: Could not get hero");
    });
}

function updateMyHeroInGame() {
    updateHeroInGame("you");
}

function updateEnemyHeroInGame() {
    updateHeroInGame("enemy");
}

function showHeroHealth(parent, currentHealth) {
    document.querySelector(`#gameBoard .${parent} .hero .health`).innerText = currentHealth;
}

function showHero(parent, heroName) {
    document.querySelector("#vsScreen ." + parent).innerHTML = `<img src="assets/media/${heroName}.png" alt="${parent}Hero" title="${parent}Hero">`;

    let hero = document.querySelector(`#gameBoard .${parent} .hero`);
    hero.innerHTML = '<span class="health">30</span>';

    hero.style.background = `no-repeat url("assets/media/${heroName}.png") center center`;
    hero.style.backgroundSize = "contain";
}

function updateEnemyCards() {
    let amountOfCards = 0;

    // TODO fetch for enemycardsinhand and other info
    fetch('/threebeesandme/get/gameboard/enemycardsinhand', {
            method: 'GET'
        })
        .then(function(res) {
            if(res.ok === true)
                return res.json();
        })
            .then(function(text) {
                let result = text;
                console.log("enemy cards in hand updated");

                amountOfEnemyCards = result;
                //TODO Mattijs says so
                updateCards(amountOfEnemyCards, "enemy", -1);
                setEnemyCardBacks(amountOfEnemyCards);
                giveClassNameEqualTocardID();
            })
            .catch(function(err) {
                console.log("Error 404: Could not connect to the server");
            });

    //amountOfCards = 7;      // MOCK DATA

    //updateCards(amountOfCards, "enemy", -1);
    //setEnemyCardBacks("enemy", cardBackUrl);
}

function updateMyCards() {
    fetch('/threebeesandme/get/gameboard/mycardsinhand', {
        method: 'GET'
    })
    .then(function(res) {
        if(res.ok === true)
            return res.json();
    })
        .then(function(text) {
            let result = text;
            console.log("my cards in hand updated");

            myCards = result;
            updateCards(myCards.length, "you", 1);
            setMyCards(myCards);
            giveClassNameEqualTocardID();
            setupMovingOfCards();
        })
        .catch(function(err) {
            console.log("Error 404: Could not connect to the server");
        });
}

function giveClassNameEqualTocardID() {
    let cardHtmlObjects = document.querySelectorAll("#gameBoard .you .cards li");
    for (let i = 0; i < myCards.length; i++) {
        cardHtmlObjects[i].classList.add(myCards[i]["cardID"]);
    }
}

function updateCards(amountOfCards, parent, gradDirectionIndex) {
    let cards = document.querySelector(`#gameBoard .${parent} .cards ul`);
    cards.innerHTML = "";
    for (let i = 0; i < amountOfCards; i++) {
        cards.innerHTML += `<li></li>`;
    }
    makeCardsFan(parent, gradDirectionIndex);
}

function updateEnemyMana(activeMana, totalMana) {
    fetch('/threebeesandme/get/gameboard/enemymana', {
        method: 'GET'
    })
        .then(function (res) {
            if (res.ok === true)
                return res.json();
            else
                return "ERROR";
        })
        .then(function (text) {
            console.log("Enemy mana updated");
            document.querySelector("#gameBoard .enemy .manaTotal").innerText = text[0] + "/" + text[1];
        })
        .catch(function (err) {
            console.log("Error: Could not update my mana");
        });
}

function updateMyMana() {
    fetch('/threebeesandme/get/gameboard/mymana', {
        method: 'GET'
    })
        .then(function (res) {
            if (res.ok === true)
                return res.json();
            else
                return "ERROR";
        })
        .then(function (text) {
            console.log("My mana updated");
            visualizeMyMana(text[0], text[1]);
        })
        .catch(function (err) {
            console.log("Error: Could not update my mana");
        });
}

function visualizeMyMana(activeMana, totalMana) {
    let manaList = document.querySelector("#gameBoard .you .manaHolder ul");
    document.querySelector("#gameBoard .you .manaTotal").innerText = activeMana + "/" + totalMana;

    manaList.innerHTML = "";

    for (let i = 0; i < activeMana; i++) {
        manaList.innerHTML += '<li class="mana"></li>\r\n';
    }

    for (let i = activeMana; i < totalMana; i++) {
        manaList.innerHTML += '<li class="usedMana"></li>\r\n';
    }
}

function returnRemainingCrystal() {
    return document.querySelectorAll("#gameBoard .you .manaHolder ul .mana").length;
}

function setEnemyCardBacks(parent, cardUrl) {
    let cardBacks = document.querySelectorAll(`#gameBoard .${parent} .cards li`);

    for (let i = 0; i < cardBacks.length; i++) {
        cardBacks[i].style.background = "no-repeat url(\"" + cardUrl + "\") center 74%";
        cardBacks[i].style.backgroundSize = "125%";
        cardBacks[i].style.color = "transparent";
        cardBacks[i].style.border = "none";
    }
}

function setMyCards(cards) {
    let cardHtmlObjects = document.querySelectorAll("#gameBoard .you .cards li");

    for (let i = 0; i < cards.length; i++) {
        let addOn = "";

        cardHtmlObjects[i].innerText = "";

        if (cards[i]["rarity"] === "Legendary") {
            cardHtmlObjects[i].innerHTML += '<span class="legendary"></span>';
        }

        cardHtmlObjects[i].style.background = 'no-repeat url("' + cards[i]["img"] + '") center center';
        cardHtmlObjects[i].style.backgroundSize = "110%";
        cardHtmlObjects[i].style.border = "none";
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

    if (cardCounter <= 10) {
        document.querySelector('#gameBoard .you .cards ul').innerHTML += "<li>Card " + cardCounter + "</li>";
        makeCardsFan("you", 1);
    }

    setupMovingOfCards();
}

function makeCardsFan(parentClass, gradDirectionIndex) {                                // gradDirectionIndex: Normally -1 or 1
    let parent = document.getElementsByClassName(parentClass);                          // so 'enemy' or 'you'
    let cards = document.querySelectorAll(`#gameBoard .${parentClass} .cards li`);      // select all the list items
    let totalWidth = document.querySelector(`#gameBoard .main`).offsetWidth;            // value in px ( of #gameboard .main )
    let cardWidth;

    try {
        cardWidth = cards[0].offsetWidth;                                               // value in px
    }
    catch (e) {
        console.log(`No cards of ${parentClass} to fan...`);
    }

    let totalFanWidth = 0.3 * totalWidth;                                               // value in px
    let extraLeft = (totalWidth - totalFanWidth) / 2;                                     // amount of padding that needs to be added to centre the fan
    let diff = cardWidth * cards.length - totalFanWidth;                                // total amount of width from cards that needs to be divided
                                                                                        // over cards with overlap
    let overlap = cardWidth - diff / (cards.length - 1);                                  // overlap per card

    let minIndex = 0;
    let amountOfCards = cards.length;

    let gradAddOnPerCard = amountOfCards * gradDirectionIndex;                            // value in grad
    let currentGrad = -gradAddOnPerCard * Math.floor(amountOfCards / 2);
    let transformOriginHeight;

    if (amountOfCards % 2 === 0) {                                                         // make the rotation evenly spread
        currentGrad += gradAddOnPerCard / 2
    }

    if (gradDirectionIndex < 0) {
        transformOriginHeight = "top";
    }
    else {
        transformOriginHeight = "bottom";
    }

    if (diff > 0) {
        for (let i = minIndex; i < amountOfCards; i++) {
            cards[i].style = "";

            if (gradDirectionIndex * currentGrad < 0) {
                cards[i].style.transformOrigin = "right " + transformOriginHeight;
            }
            else {
                cards[i].style.transformOrigin = "left " + transformOriginHeight;
            }

            cards[i].style.position = "absolute";
            cards[i].style.bottom = "-0.5vh";
            cards[i].style.left = (((overlap * i + extraLeft) / window.innerHeight) * 100) + "vh";
            cards[i].style.transform = "rotate(" + currentGrad + "grad)";
            cards[i].classList.remove("notFanned");
            currentGrad += gradAddOnPerCard;
        }
        console.log(`cards of ${parentClass} fanned...`);
    }
    else {
        for (let i = minIndex; i < amountOfCards; i++) {
            cards[i].style = "";

            cards[i].style.position = "absolute";
            cards[i].style.bottom = "-2vh";

            let buf = (cardWidth / window.innerHeight * 100) / (amountOfCards % 2 + 1);               // 50% - cardWith when even, 50% - cardWith/2 when uneven (center aligning)
            buf -= (cardWidth / window.innerHeight * 100) * (parseInt(amountOfCards / 2) - i);        // align card according to its position

            cards[i].style.left = "calc(50% - " + buf + "vh)";
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

function setupMovingOfCards() {
    let cards = document.querySelectorAll("#gameBoard .you .cards ul li");
    for (let i = 0; i < cards.length; i++) {
        cards[i].addEventListener("mousedown", layCardOnFieldStart);
        cards[i].addEventListener("touchstart", layCardOnFieldStart);
    }
}

let dragSrcElement;
let dragOffsetX;
let dragOffsetY;
let itemThatIsBeingMoved;
let moved;

function layCardOnFieldStart(e) {
    // TODO fetch
    dragOffsetX = e.offsetX;
    dragOffsetY = e.offsetY;
    itemThatIsBeingMoved = e.target;
    moved = false;
    dragSrcElement = e.target.cloneNode(true);
    itemThatIsBeingMoved.classList.add('hidden');
    document.body.appendChild(dragSrcElement);
    dragSrcElement.removeAttribute('style');

    movingOfDragElement(e);
    dragSrcElement.style.zIndex = '1';
    dragSrcElement.style.width = '14.854838709677vh';
    dragSrcElement.style.height = '22.5vh';
    dragSrcElement.style.position = 'absolute';
    dragSrcElement.style.background = e.target.style.background;


    document.removeEventListener("mousedown", layCardOnFieldStart);
    document.removeEventListener("touchstart", layCardOnFieldStart);
    document.addEventListener("touchmove", movingOfDragElement, false);
    document.addEventListener("mousemove", movingOfDragElement, false);
    document.addEventListener("mouseup", layCardOnFieldEnd, false);
    document.addEventListener("touchend", layCardOnFieldEnd, false);
}

function movingOfDragElement(e) {
    if (typeof e.clientX === "number") {
        let XCoordinate = e.clientX - dragOffsetX;
        let YCoordinate = e.clientY - dragOffsetY;
        dragSrcElement.style.left = XCoordinate + 'px';
        dragSrcElement.style.top = YCoordinate + 'px';
    }
    else {
        // Mattijs: the web browser cannot calculate the offset so I use this as default
        let XCoordinate = e.touches[0].clientX - 40;
        let YCoordinate = e.touches[0].clientY - 80;
        dragSrcElement.style.left = XCoordinate + 'px';
        dragSrcElement.style.top = YCoordinate + 'px';
    }
}

function itIsOkToPlayCard() {
    let cardPlayed = cloneDragElement();
    if (type === 'Minion') {
        let dropZone = document.querySelector('#gameBoard .you .playingField .dropZone');
        dropZone.appendChild(cardPlayed);
        addMinionToPlayingField(cardPlayed);
    } else {
        let dropZone = document.querySelector('#gameBoard .you .weapon ul');
        dropZone.innerHTML = '';
        dropZone.appendChild(cardPlayed);
        addWeaponToPlayingField(cardPlayed);
    }
}

function CheckingIfCardIsOnPlayingField() {
    let dropZone = document.querySelector('#gameBoard .you .playingField .dropZone');
    let rectDrag = dragSrcElement.getBoundingClientRect();
    let rectDropZone = dropZone.getBoundingClientRect();
    if ((rectDrag.right < rectDropZone.right) && (rectDrag.left > rectDropZone.left) && (rectDrag.bottom < (rectDropZone.bottom + 100)) && (rectDrag.top > rectDropZone.top - 100)) {
        itIsOkToPlayCard();
    }
}

function cloneDragElement() {
    let cardOnPlayingField = dragSrcElement.cloneNode(true);
    cardOnPlayingField.removeAttribute('style');
    cardOnPlayingField.style.background = dragSrcElement.style.background;
    cardOnPlayingField.style.backgroundSize = "176%";
    cardOnPlayingField.style.backgroundPositionY = "23%";
    return cardOnPlayingField;
}

function activateHeroAttack(heroAttackValue) {
    document.querySelector('#gameBoard .you .hero').innerHTML += `<span class="heroAttack">${heroAttackValue}</span>`;
    document.querySelector('#gameBoard .you .hero').addEventListener("mousedown", heroAttackStart);
    document.querySelector('#gameBoard .you .hero').addEventListener("touchstart", heroAttackStart);
}

function deactivateHeroAttack() {
    let heroAttackElement = document.querySelector('#gameBoard .you .hero .heroAttack');
    heroAttackElement.parentElement.removeChild(heroAttackElement);
    document.querySelector('#gameBoard .you .hero').removeEventListener("mousedown", heroAttackStart);
    document.querySelector('#gameBoard .you .hero').removeEventListener("touchstart", heroAttackStart);
}

function addWeaponToPlayingField(cardPlayed) {
    moved = true;
    sendPlayedCard(itemThatIsBeingMoved);
    let cardAttack = returnAttackOfMyCard(itemThatIsBeingMoved);
    let cardDurability = returnDurabilityOfMyCard(itemThatIsBeingMoved);
    cardPlayed.innerHTML += `<span class="attack">${cardAttack}</span><span class="durability">${cardDurability}</span>`;
    activateHeroAttack(cardAttack);
}

function addMinionToPlayingField(cardPlayed) {
    // mockData
    moved = true;
    sendPlayedCard(itemThatIsBeingMoved);
    let cardAttack = returnAttackOfMyCard(itemThatIsBeingMoved);
    let cardHealth = returnHealthOfMyCard(itemThatIsBeingMoved);
    cardPlayed.innerHTML += `<span class="health">${cardHealth}</span><span class="attack">${cardAttack}</span>`;
    cardPlayed.addEventListener("mousedown", loadAttackStart);
    cardPlayed.addEventListener("touchstart", loadAttackStart);
}

function PlayingFieldEnd(dropZoneLi) {
    let dropZone = document.querySelector('#gameBoard .you .playingField .dropZone');
    let rectDrag = dragSrcElement.getBoundingClientRect();
    let top = dropZone.getBoundingClientRect().top - 100;
    let bottom = dropZone.getBoundingClientRect().bottom + 100;
    for (let i = 0; i < dropZoneLi.length - 1; i++) {
        let left = dropZoneLi[i].getBoundingClientRect().left;
        let right = dropZoneLi[i + 1].getBoundingClientRect().right;
        if ((rectDrag.right < right) && (rectDrag.left > left) && (rectDrag.bottom < bottom) && (rectDrag.top > top)) {
            let cardOnPlayingField = cloneDragElement();
            dropZone.insertBefore(cardOnPlayingField, dropZoneLi[i + 1]);
            addMinionToPlayingField(cardOnPlayingField);
            break;
        }
        right = left;
        left = dropZone.getBoundingClientRect().left;
        if ((rectDrag.right < right) && (rectDrag.left > left) && (rectDrag.bottom < bottom) && (rectDrag.top > top)) {
            let cardOnPlayingField = cloneDragElement();
            dropZone.insertBefore(cardOnPlayingField, dropZoneLi[0]);
            addMinionToPlayingField(cardOnPlayingField);
            break;
        }
        left = dropZoneLi[dropZoneLi.length - 1].getBoundingClientRect().right;
        right = dropZone.getBoundingClientRect().right;
        if ((rectDrag.right < right) && (rectDrag.left > left) && (rectDrag.bottom < bottom) && (rectDrag.top > top)) {
            let cardOnPlayingField = cloneDragElement();
            dropZone.appendChild(cardOnPlayingField);
            addMinionToPlayingField(cardOnPlayingField);
            break;
        }
    }
    if (dropZoneLi.length === 1) {
        let left = dropZone.getBoundingClientRect().left;
        let right = dropZoneLi[0].getBoundingClientRect().left;
        if ((rectDrag.right < right) && (rectDrag.left > left) && (rectDrag.bottom < bottom) && (rectDrag.top > top)) {
            let cardOnPlayingField = cloneDragElement();
            dropZone.insertBefore(cardOnPlayingField, dropZoneLi[0]);
            addMinionToPlayingField(cardOnPlayingField);
        } else {
            let left = dropZoneLi[0].getBoundingClientRect().left;
            let right = dropZone.getBoundingClientRect().right;
            if ((rectDrag.right < right) && (rectDrag.left > left) && (rectDrag.bottom < bottom) && (rectDrag.top > top)) {
                let cardOnPlayingField = cloneDragElement();
                dropZone.appendChild(cardOnPlayingField);
                addMinionToPlayingField(cardOnPlayingField);
            }
        }
    }

}

let cost;
let remainingCrystals;
let type;

function layCardOnFieldEnd(e) {
    // TODO fetch play the card
    // TODO succeeded or not
    type = returnTypeOfMyCard(e.target);
    cost = returnCostOfMyCard(e.target);
    remainingCrystals = returnRemainingCrystal();
    if (cost > remainingCrystals) {
        console.error('not enough mana');
    } else {
        switch (type) {
            case 'Minion':
                let dropZoneLi = document.querySelectorAll('#gameBoard .you .playingField .dropZone li');
                switch (dropZoneLi.length) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        PlayingFieldEnd(dropZoneLi);
                        break;
                    case 7:
                        break;
                    default:
                        CheckingIfCardIsOnPlayingField();
                        break;
                }
                break;
            case 'Weapon':
                CheckingIfCardIsOnPlayingField();
                break;
            case 'Spell':
                console.log('NYI'); //TODO fetch
        }
    }
    try {
        dragSrcElement.parentElement.removeChild(dragSrcElement);
        itemThatIsBeingMoved.classList.remove('hidden');
        if (moved === true) {
            itemThatIsBeingMoved.parentElement.removeChild(itemThatIsBeingMoved);
            updateMyCards();
        }
    } catch (err) {
        console.log('nothing can be removed' + err);
    }
    document.removeEventListener("touchmove", movingOfDragElement, false);
    document.removeEventListener("touchend", layCardOnFieldEnd, false);
    document.removeEventListener("mousemove", movingOfDragElement, false);
    document.removeEventListener("mouseup", layCardOnFieldEnd, false);
    // TODO fetch
}

function returnTypeOfMyCard(liWithClass) {
    let cardID = liWithClass.getAttribute('class');
    for (let i = 0; i < myCards.length; i++) {
        if (cardID.indexOf(myCards[i]["cardID"]) !== -1) {
            return myCards[i]["type"];
        }
    }
    return null;
}

function returnDurabilityOfMyCard(liWithClass) {
    let cardID = liWithClass.getAttribute('class');
    for (let i = 0; i < myCards.length; i++) {
        if (cardID.indexOf(myCards[i]["cardID"]) !== -1) {
            return myCards[i]["durability"];
        }
    }
    return null;
}

function returnHealthOfMyCard(liWithClass) {
    let cardID = liWithClass.getAttribute('class');
    for (let i = 0; i < myCards.length; i++) {
        if (cardID.indexOf(myCards[i]["cardID"]) !== -1) {
            return myCards[i]["health"];
        }
    }
    return null;
}

function returnAttackOfMyCard(liWithClass) {
    let cardID = liWithClass.getAttribute('class');
    for (let i = 0; i < myCards.length; i++) {
        if (cardID.indexOf(myCards[i]["cardID"]) !== -1) {
            return myCards[i]["attack"];
        }
    }
    return null;
}

function returnCostOfMyCard(liWithClass) {
    let cardID = liWithClass.getAttribute('class');
    for (let i = 0; i < myCards.length; i++) {
        if (cardID.indexOf(myCards[i]["cardID"]) !== -1) {
            return myCards[i]["manaCost"];
        }
    }
    return null;
}

function sendPlayedCard(liWithClass) {
    let cardID = liWithClass.getAttribute('class');

    fetch('/threebeesandme/post/gameboard/playcard', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(cardID.split(' ')[0])
    })
        .then(function (res) {
            if (res.ok === true)
                return res.json();
            else
                return "ERROR";
        })
        .then(function (text) {
            if(text === "SUCCES") {
                console.log("Card played");
                updateMyStuff();
                updateEnemyStuff();
            }
            else {
                console.log("You cannot play this card!");
            }
        })
        .catch(function (err) {
            console.log("Error: Could not play the card");
        });
}

/* give minion class nonAttack
 check if charge is present
check if battleCry is present
let type = returnTypeOfMyCards(dragSrcElement);
switch (type){
    case 'Minion':
        dropZoneElement.appendChild(dragSrcElement);
        break;
    case 'Weapon':
        document.querySelector('.weapon').appendChild(dragSrcElement);
        break;
}
dragSrcElement.addEventListener('click', visualiseAttack); */


function visualiseAttack(e) {
    // check if a card has class nonAttack
    // check if their is one or more minions with taunt
    // not attack on other minions
    // begin drag&drop without removing li
    // dragend check on what element the target is dropped and does the *real* attack function
    console.log('cannot attack');
    attack();
}

function attackStart() {
    let target = e.target;
    if (e.path[0].tagName === 'SPAN') {
        target = e.target.parentElement;
    }
    dragOffsetX = e.offsetX;
    dragOffsetY = e.offsetY;
    itemThatIsBeingMoved = target;
    moved = false;
    dragSrcElement = target.cloneNode(false);
    document.querySelector('#gameBoard').appendChild(dragSrcElement);
    dragSrcElement.removeAttribute('style');

    movingOfDragElement(e);
    dragSrcElement.style.zIndex = '1';
    dragSrcElement.style.position = 'absolute';
    dragSrcElement.style.width = '9.9032258064516129032258064516131vh';
    dragSrcElement.style.height = '15vh';
    dragSrcElement.style.borderRadius = '50%';

    dragSrcElement.style.background = target.style.background;

    document.removeEventListener("mousedown", layCardOnFieldStart);
    document.removeEventListener("touchstart", layCardOnFieldStart);
    document.addEventListener("touchmove", movingOfDragElement, false);
    document.addEventListener("mousemove", movingOfDragElement, false);
    document.addEventListener("mouseup", attackEnd, false);
    document.addEventListener("touchend", attackEnd, false);
}

function loadAttackStart(e) {
    fetch('http://localhost:4242/threebeesandme/get/gameboard/attackpermission', {
        method: 'get'
    })
        .then(function (res) {
            if (res.ok === true)
                return res.json();
        })
        .then(function (text) {
            let result = text;
            attackStart(result)
            console.log("asking for attack permission has been send to server");
        })
        .catch(function (err) {
            console.log("Error: Could not send permission for attack");
        });
}

function heroAttackStart(e) {
    // TODO There is no need for two functions

    fetch('http://localhost:4242/threebeesandme/post/gameboard/heroattackStart', {
        method: 'GET'
    })
        .then(function (res) {
            if (res.ok === true)
                return res.json();
        })
        .then(function (text) {
            let result = text;
            console.log("asking for attack permission has been send to server");
        })
        .catch(function (err) {
            console.log("Error: Could not send permission for attack");
        });


    if (heroAttack === true) {
        let target = e.target;
        if (e.path[0].tagName === 'SPAN') {
            target = e.target.parentElement;
        }
        dragOffsetX = e.offsetX;
        dragOffsetY = e.offsetY;
        itemThatIsBeingMoved = target;
        moved = false;
        dragSrcElement = target.cloneNode(false);
        document.querySelector('#gameBoard').appendChild(dragSrcElement);
        dragSrcElement.removeAttribute('style');

        movingOfDragElement(e);
        dragSrcElement.style.zIndex = '1';
        dragSrcElement.style.position = 'absolute';
        dragSrcElement.style.width = '14.854838709677vh';
        dragSrcElement.style.height = '22.5vh';
        dragSrcElement.style.background = target.style.background;

        document.removeEventListener("mousedown", layCardOnFieldStart);
        document.removeEventListener("touchstart", layCardOnFieldStart);
        document.addEventListener("touchmove", movingOfDragElement, false);
        document.addEventListener("mousemove", movingOfDragElement, false);
        document.addEventListener("mouseup", heroAttackEnd, false);
        document.addEventListener("touchend", heroAttackEnd, false);
    }
}

function attackEnd() {
    let enemies = document.querySelectorAll('.enemy .playingField ul li');
    let rectDrag = dragSrcElement.getBoundingClientRect();
    let hero = document.querySelector('#gameBoard .enemy .hero').getBoundingClientRect();
    if ((rectDrag.right < hero.right + 29) && (rectDrag.left > hero.left - 35) && (rectDrag.bottom < hero.bottom + 35) && (rectDrag.top > rectDrag.top - 9)) {
        console.log('attack');
        console.log(document.querySelector('#gameBoard .enemy .hero'));
    }
    for (let i = 0; i < enemies.length; i++) {
        let enemy = enemies[i].getBoundingClientRect();
        if ((rectDrag.right < enemy.right + 13) && (rectDrag.left > enemy.left - 13) && (rectDrag.bottom < enemy.bottom + 26) && (rectDrag.top > enemy.top - 18)) {
            console.log('attack');
            console.log(enemies[i]);
        }
    }
    dragSrcElement.parentElement.removeChild(dragSrcElement);
    document.removeEventListener("touchmove", movingOfDragElement, false);
    document.removeEventListener("mousemove", movingOfDragElement, false);
    document.removeEventListener("mouseup", attackEnd, false);
    document.removeEventListener("touchend", attackEnd, false);
    // TODO fetch
}

function heroAttackEnd() {
    let enemies = document.querySelectorAll('.enemy .playingField ul li');
    let rectDrag = dragSrcElement.getBoundingClientRect();
    let hero = document.querySelector('#gameBoard .enemy .hero').getBoundingClientRect();
    if ((rectDrag.right < hero.right + 29) && (rectDrag.left > hero.left - 35) && (rectDrag.bottom < hero.bottom + 35) && (rectDrag.top > rectDrag.top - 9)) {
        console.log('attack');
        console.log(document.querySelector('#gameBoard .enemy .hero'));
        heroAttack = false;
    }
    for (let i = 0; i < enemies.length; i++) {
        let enemy = enemies[i].getBoundingClientRect();
        if ((rectDrag.right < enemy.right + 30) && (rectDrag.left > enemy.left - 30) && (rectDrag.bottom < enemy.bottom + 50) && (rectDrag.top > enemy.top - 50)) {
            console.log('attack');
            console.log(enemies[i]);
            heroAttack = false;
        }
    }
    try {
        dragSrcElement.parentElement.removeChild(dragSrcElement);
        if (heroAttack === false) {
            deactivateHeroAttack();
            let oldDurability = document.querySelector('#gameBoard .you .weapon ul li .durability').innerHTML;
            document.querySelector('#gameBoard .you .weapon ul li .durability').innerHTML = oldDurability - 1;
        }
    } catch (err) {
        console.log('nothing can be removed' + err);
    }
    document.removeEventListener("touchmove", movingOfDragElement, false);
    document.removeEventListener("mousemove", movingOfDragElement, false);
    document.removeEventListener("mouseup", attackEnd, false);
    document.removeEventListener("touchend", attackEnd, false);
    // TODO fetch
}

function attack() {
    // get source and destination
    // armor Hero
    // sHealth sAttack dHealth dAttack
    // check if card has stealth
    // remove source stealth
    // check if card has divine shield
    // remove divine shield
    // dHealth - sAttack = dHealth
    // enable enraged
    // check if dHealth is under 0 or (source has poisonous and destination is minion)
    // destroyed()
    // sHealth - dAttack = sHealth
    // enable enraged
    // check if sHealth is under 0 or (destination has poisonous and source is minion)
    // destroyed()
    // check if card has windfury and windfury hasn't been triggered
    // give minion class nonAttack
}

function destroyed() {
    // check if card has deathrattle
    // win || lose
    // discard card
}

