"use strict";

document.addEventListener("DOMContentLoaded", init);

let cardCounter = 0;
let cardBackUrl = "";
let myCards = null;

function init() {
    setupMovingOfCards();
    //document.querySelector("#tempButtonAddCard").addEventListener('click', addCard);
    setBackground();
    getRandomCardBack();
    //makeCardsFan("you", 1);

    /*
    document.getElementById("spark").addEventListener("click", burnFuse);
    document.getElementById("fuse").addEventListener("click", burnFuse);
    */
}

function setupGameBoard() {
    document.getElementById('endTurn').addEventListener('click', endTurn);
}

function endTurn(e) {
    /*
    fetch('threebeesandme/howeststone/post/endturn',{
        method: 'POST'
    })
        .then(function(res) {
            if(res.ok === true)
                return res.json();
        })
        .then(function(text) {
            let result = text;
            console.log("turn end has been send to server");
        })
        .catch(function(err) {
            console.log("Error: Could not end turn");
        });
        */
    console.log("turn end has been send to server");
}

function activateReplaceCards() {
    // TODO background images replacen (li + span)
    let replaceCards = document.querySelectorAll('#replaceCardScreen ul li');
    for(let i = 0; i < replaceCards.length; i++){
        replaceCards[i].addEventListener('click', toggleReplaceCard);
    }
}

function deactivateReplaceCards() {
    let replaceCards = document.querySelectorAll('#replaceCardScreen ul li');
    for(let i = 0; i < replaceCards.length; i++){
        replaceCards[i].removeEventListener('click', toggleReplaceCard);
    }
}
let countReplaceCards = 0;
function toggleReplaceCard(e) {
    let replaceCards = document.querySelectorAll('#replaceCardScreen ul li');
    countReplaceCards = replaceCards.length;
    e.target.querySelector('span').classList.toggle('hidden');
    for(let i = 0; i < replaceCards.length; i++){
        if (replaceCards[i].querySelector('span').classList.contains('hidden')){
            countReplaceCards -= 1;
        }
    }
    if (countReplaceCards !== 0){
        document.querySelector('#gotoCardsReplaced').innerHTML = 'Replace';
    } else {
        document.querySelector('#gotoCardsReplaced').innerHTML = 'Continue';
    }
}

function firstTurn() {
    console.log('test');
    updateEnemyHero();
    updateEnemyMana(0, 0);
    updateEnemyCards(5);
    updateMyHero();
    updateMyMana(0, 0);
    updateMyCards(3);
    setTimeout(yourTurn, 1000);
}
function yourTurn() {
    console.log("You're turn");
    updateMyMana(1, 1);
    updateMyCards(4);
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

function updateEnemyCards() {
    let amountOfCards = 0;

    /* Here should come a fetch to get enemy amount of cards */
    amountOfCards = 7;      // MOCK DATA

    updateCards(amountOfCards, "enemy", -1);
    setEnemyCardBacks("enemy", cardBackUrl);
}

function updateMyCards() {
    /* Here should come a fetch to get cardInMyHand data */
    myCards = MOCKMYCARDS();

    updateCards(myCards.length, "you", 1);
    setMyCards(myCards);
    giveClassNameEqualToCardID();
    setupMovingOfCards();
}

function giveClassNameEqualToCardID() {
    let cardHtmlObjects = document.querySelectorAll("#gameBoard .you .cards li");
    for(let i=0; i<myCards.length; i++){
        cardHtmlObjects[i].classList.add(myCards[i].cardId);
    }
}

function MOCKMYCARDS() {
    return [
        {
            "cardId": "CS2_231",
            "dbfId": "179",
            "name": "Wisp",
            "cardSet": "Classic",
            "type": "Minion",
            "faction": "Neutral",
            "rarity": "Common",
            "cost": 0,
            "attack": 1,
            "health": 1,
            "flavor": "If you hit an Eredar Lord with enough Wisps, it will explode. But why?",
            "artist": "Malcolm Davis",
            "collectible": true,
            "playerClass": "Neutral",
            "img": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/CS2_231.png",
            "imgGold": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/animated/CS2_231_premium.gif",
            "locale": "enUS"
        },
        {
            "cardId": "CS2_188",
            "dbfId": "242",
            "name": "Abusive Sergeant",
            "cardSet": "Classic",
            "type": "Minion",
            "faction": "Alliance",
            "rarity": "Common",
            "cost": 1,
            "attack": 1,
            "health": 1,
            "text": "Battlecry: Give a minion +2_Attack this turn.",
            "flavor": "ADD ME TO YOUR DECK, MAGGOT!",
            "artist": "Luca Zontini",
            "collectible": true,
            "playerClass": "Neutral",
            "img": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/CS2_188.png",
            "imgGold": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/animated/CS2_188_premium.gif",
            "locale": "enUS",
            "mechanics": [
                {
                    "name": "Battlecry"
                }
            ]
        },
        {
            "cardId": "EX1_009",
            "dbfId": "1688",
            "name": "Angry Chicken",
            "cardSet": "Classic",
            "type": "Minion",
            "rarity": "Rare",
            "cost": 1,
            "attack": 1,
            "health": 1,
            "text": "Has +5 Attack while damaged.",
            "flavor": "There is no beast more frightening (or ridiculous) than a fully enraged chicken.",
            "artist": "Mike Sass",
            "collectible": true,
            "race": "Beast",
            "playerClass": "Neutral",
            "img": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/EX1_009.png",
            "imgGold": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/animated/EX1_009_premium.gif",
            "locale": "enUS"
        },
        {
            "cardId": "EX1_008",
            "dbfId": "757",
            "name": "Argent Squire",
            "cardSet": "Classic",
            "type": "Minion",
            "faction": "Alliance",
            "rarity": "Common",
            "cost": 1,
            "attack": 1,
            "health": 1,
            "text": "Divine Shield",
            "flavor": "\"I solemnly swear to uphold the Light, purge the world of darkness, and to eat only burritos.\" - The Argent Dawn Oath",
            "artist": "Zoltan & Gabor",
            "collectible": true,
            "playerClass": "Neutral",
            "img": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/EX1_008.png",
            "imgGold": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/animated/EX1_008_premium.gif",
            "locale": "enUS",
            "mechanics": [
                {
                    "name": "Divine Shield"
                }
            ]
        },
        {
            "cardId": "CS2_059",
            "dbfId": "469",
            "name": "Blood Imp",
            "cardSet": "Classic",
            "type": "Minion",
            "faction": "Neutral",
            "rarity": "Common",
            "cost": 1,
            "attack": 0,
            "health": 1,
            "text": "[x] Stealth. At the end of your \\nturn, give another random\\n friendly minion +1 Health.",
            "flavor": "Imps are content to hide and viciously taunt everyone nearby.",
            "artist": "Bernie Kang",
            "collectible": true,
            "race": "Demon",
            "playerClass": "Warlock",
            "img": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/CS2_059.png",
            "imgGold": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/animated/CS2_059_premium.gif",
            "locale": "enUS",
            "mechanics": [
                {
                    "name": "Stealth"
                }
            ]
        },
        {
            "cardId": "EX1_319",
            "dbfId": "1090",
            "name": "Flame Imp",
            "cardSet": "Classic",
            "type": "Minion",
            "faction": "Neutral",
            "rarity": "Common",
            "cost": 1,
            "attack": 3,
            "health": 2,
            "text": "Battlecry: Deal 3 damage to your hero.",
            "flavor": "Imps like being on fire. They just do.",
            "artist": "Alex Horley Orlandelli",
            "collectible": true,
            "race": "Demon",
            "playerClass": "Warlock",
            "img": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/EX1_319.png",
            "imgGold": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/animated/EX1_319_premium.gif",
            "locale": "enUS",
            "mechanics": [
                {
                    "name": "Battlecry"
                }
            ]
        },
        {
            "cardId": "EX1_538t",
            "dbfId": "1715",
            "name": "Hound",
            "cardSet": "Classic",
            "type": "Minion",
            "cost": 1,
            "attack": 1,
            "health": 1,
            "text": "Charge",
            "race": "Beast",
            "playerClass": "Hunter",
            "img": "http://wow.zamimg.com/images/hearthstone/cards/enus/original/EX1_538t.png",
            "imgGold": "http://wow.zamimg.com/images/hearthstone/cards/enus/animated/EX1_538t_premium.gif",
            "locale": "enUS",
            "mechanics": [
                {
                    "name": "Charge"
                }
            ]
        },
        {
            "cardId": "NEW1_017",
            "dbfId": "443",
            "name": "Hungry Crab",
            "cardSet": "Classic",
            "type": "Minion",
            "rarity": "Epic",
            "cost": 1,
            "attack": 1,
            "health": 2,
            "text": "Battlecry: Destroy a Murloc and gain +2/+2.",
            "flavor": "Murloc. It's what's for dinner.",
            "artist": "Jaemin Kim",
            "collectible": true,
            "race": "Beast",
            "playerClass": "Neutral",
            "img": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/NEW1_017.png",
            "imgGold": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/animated/NEW1_017_premium.gif",
            "locale": "enUS",
            "mechanics": [
                {
                    "name": "Battlecry"
                }
            ]
        },
        {
            "cardId": "EX1_029",
            "dbfId": "658",
            "name": "Leper Gnome",
            "cardSet": "Classic",
            "type": "Minion",
            "faction": "Neutral",
            "rarity": "Common",
            "cost": 1,
            "attack": 1,
            "health": 1,
            "text": "Deathrattle: Deal 2 damage to the enemy_hero.",
            "flavor": "He really just wants to be your friend, but the constant rejection is starting to really get to him.",
            "artist": "Glenn Rane",
            "collectible": true,
            "playerClass": "Neutral",
            "img": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/EX1_029.png",
            "imgGold": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/animated/EX1_029_premium.gif",
            "locale": "enUS",
            "mechanics": [
                {
                    "name": "Deathrattle"
                }
            ]
        },
        {
            "cardId": "EX1_012",
            "dbfId": "749",
            "name": "Bloodmage Thalnos",
            "cardSet": "Classic",
            "type": "Minion",
            "faction": "Neutral",
            "rarity": "Legendary",
            "cost": 2,
            "attack": 1,
            "health": 1,
            "text": "Spell Damage +1\\nDeathrattle: Draw a card.",
            "flavor": "He's in charge of the Annual Scarlet Monastery Blood Drive!",
            "artist": "Alex Horley Orlandelli",
            "collectible": true,
            "elite": true,
            "playerClass": "Neutral",
            "img": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/EX1_012.png",
            "imgGold": "http://media.services.zam.com/v1/media/byName/hs/cards/enus/animated/EX1_012_premium.gif",
            "locale": "enUS",
            "mechanics": [
                {
                    "name": "Deathrattle"
                },
                {
                    "name": "Spell Damage"
                }
            ]
        }
    ];
}

function updateCards(amountOfCards, parent, gradDirectionIndex) {
    let cards = document.querySelector(`#gameBoard .${parent} .cards ul`);
    cards.innerHTML = "";
    for(let i=0; i<amountOfCards; i++) {
        cards.innerHTML += `<li></li>`;
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

function setEnemyCardBacks(parent, cardUrl) {
    let cardBacks = document.querySelectorAll(`#gameBoard .${parent} .cards li`);

    for(let i=0; i<cardBacks.length; i++) {
        cardBacks[i].style.background = "no-repeat url(\"" + cardUrl + "\") center 74%";
        cardBacks[i].style.backgroundSize = "125%";
        cardBacks[i].style.color = "transparent";
        cardBacks[i].style.border = "none";
    }
}

function setMyCards(cards) {
    let cardHtmlObjects = document.querySelectorAll("#gameBoard .you .cards li");

    for(let i=0; i<cards.length; i++) {
        let addOn = "";

        cardHtmlObjects[i].innerText = "";

        if(cards[i]["rarity"] === "Legendary") {
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

    if(cardCounter <= 10) {
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

function setupMovingOfCards() {
    let cards = document.querySelectorAll("#gameBoard .you .cards ul li");
    for(let i=0; i<cards.length; i++) {
        cards[i].addEventListener("mousedown", layCardOnFieldStart);
        cards[i].addEventListener("touchstart", layCardOnFieldStart);
    }
}

let drag;
let dragOffsetX;
let dragOffsetY;
let itemThatIsBeingMoved;
let moved;
function layCardOnFieldStart(e) {

    dragOffsetX = e.offsetX;
    dragOffsetY = e.offsetY;
    itemThatIsBeingMoved = e.target;
    moved = false;
    drag = e.target.cloneNode(true);
    itemThatIsBeingMoved.classList.add('hidden');
    document.body.appendChild(drag);
    drag.removeAttribute('style');

    movingOfDragElement(e);
    drag.style.zIndex = '1';
    drag.style.width = '14.854838709677vh';
    drag.style.height= '22.5vh';
    drag.style.position = 'absolute';
    drag.style.background = e.target.style.background;

    document.addEventListener("touchmove", movingOfDragElement, false);
    document.addEventListener("mousemove", movingOfDragElement, false);
    document.addEventListener("mouseup", layCardOnFieldEnd, false);
    document.addEventListener("touchend", layCardOnFieldEnd, false);
}

function movingOfDragElement(e) {
    if (typeof e.clientX === "number"){
        let XCoordinate = e.clientX - dragOffsetX;
        let YCoordinate = e.clientY - dragOffsetY;
        drag.style.left = XCoordinate + 'px';
        drag.style.top = YCoordinate+ 'px';
    }
    else {
        // Mattijs: the web browser cannot calculate the offset so I use this as default
        let XCoordinate = e.touches[0].clientX - 40;
        let YCoordinate = e.touches[0].clientY - 80;
        drag.style.left = XCoordinate + 'px';
        drag.style.top = YCoordinate+ 'px';
    }
}

function emptyPlayingFieldEnd() {
    let dropZone = document.querySelector('#gameBoard .you .playingField .dropZone');
    let rectDrag = drag.getBoundingClientRect();
    let rectDropZone = dropZone.getBoundingClientRect();
    if ((rectDrag.right < rectDropZone.right) && (rectDrag.left > rectDropZone.left) && (rectDrag.bottom < (rectDropZone.bottom + 100)) && (rectDrag.top > rectDropZone.top -100)){
        let cardOnPlayingField = cloneDragElement();
        dropZone.appendChild(cardOnPlayingField);
        inField(cardOnPlayingField);
    }
}

function cloneDragElement() {
    let cardOnPlayingField = drag.cloneNode(true);
    cardOnPlayingField.removeAttribute('style');
    cardOnPlayingField.style.background = drag.style.background;
    cardOnPlayingField.style.backgroundSize = "176%";
    cardOnPlayingField.style.backgroundPositionY = "23%";
    return cardOnPlayingField;
}

function inField(cardOnPlayingField) {
    // mockData
    let health = 2;
    let attack = 1;
    moved = true;
    cardOnPlayingField.innerHTML += `<span class="health">${health}</span><span class="attack">${attack}</span>`;
    cardOnPlayingField.addEventListener("mousedown", attackStart);
    cardOnPlayingField.addEventListener("touchstart", attackStart);
}

function PlayingFieldEnd(dropZoneLi) {
    let dropZone = document.querySelector('#gameBoard .you .playingField .dropZone');
    let rectDrag = drag.getBoundingClientRect();
    let top = dropZone.getBoundingClientRect().top -100;
    let bottom = dropZone.getBoundingClientRect().bottom + 100;
    for (let i = 0; i< dropZoneLi.length -1; i++){
        let left = dropZoneLi[i].getBoundingClientRect().left;
        let right = dropZoneLi[i+1].getBoundingClientRect().right;
        if ((rectDrag.right < right) && (rectDrag.left > left) && (rectDrag.bottom < bottom) && (rectDrag.top > top)){
            let cardOnPlayingField = cloneDragElement();
            dropZone.insertBefore(cardOnPlayingField, dropZoneLi[i+1]);
            inField(cardOnPlayingField);
            break;
        }
        right = left;
        left = dropZone.getBoundingClientRect().left;
        if ((rectDrag.right < right) && (rectDrag.left > left) && (rectDrag.bottom < bottom) && (rectDrag.top > top)){
            let cardOnPlayingField = cloneDragElement();
            dropZone.insertBefore(cardOnPlayingField, dropZoneLi[0]);
            inField(cardOnPlayingField);
            break;
        }
        left = dropZoneLi[i+1].getBoundingClientRect().right;
        right = dropZone.getBoundingClientRect().right;
        if ((rectDrag.right < right) && (rectDrag.left > left) && (rectDrag.bottom < bottom) && (rectDrag.top > top)){
            let cardOnPlayingField = cloneDragElement();
            dropZone.appendChild(cardOnPlayingField);
            inField(cardOnPlayingField);
            break;
        }
    }
    if(dropZoneLi.length === 1){
        let left = dropZone.getBoundingClientRect().left;
        let right = dropZoneLi[0].getBoundingClientRect().left;
        if ((rectDrag.right < right) && (rectDrag.left > left) && (rectDrag.bottom < bottom) && (rectDrag.top > top)){
            let cardOnPlayingField = cloneDragElement();
            dropZone.insertBefore(cardOnPlayingField, dropZoneLi[0]);
            inField(cardOnPlayingField);
        }else {
            let left = dropZoneLi[0].getBoundingClientRect().left;
            let right = dropZone.getBoundingClientRect().right;
            if ((rectDrag.right < right) && (rectDrag.left > left) && (rectDrag.bottom < bottom) && (rectDrag.top > top)){
                let cardOnPlayingField = cloneDragElement();
                dropZone.appendChild(cardOnPlayingField);
                inField(cardOnPlayingField);
            }
        }
    }

}

function layCardOnFieldEnd(e) {
    // TODO fetch play the card
    // TODO succeeded or not
    let dropZoneLi = document.querySelectorAll('#gameBoard .you .playingField .dropZone li');
    switch (dropZoneLi.length){
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
            emptyPlayingFieldEnd();
            break;

    }
    try{
        drag.parentElement.removeChild(drag);
        itemThatIsBeingMoved.classList.remove('hidden');
        if (moved === true){

            itemThatIsBeingMoved.parentElement.removeChild(itemThatIsBeingMoved);
            updateMyCards();

        }
    } catch (err){
        console.log('nothing can be removed' + err);
    }
    document.removeEventListener("touchmove", movingOfDragElement, false);
    document.removeEventListener("touchend", layCardOnFieldEnd, false);
    document.removeEventListener("mousemove", movingOfDragElement, false);
    document.removeEventListener("mouseup", layCardOnFieldEnd, false);
}
function returnTypeOfMyCards(liWithClass) {
    let cardId = liWithClass.getAttribute('class');
    for(let i=0; i<myCards.length; i++){
        if (cardId.indexOf(myCards[i].cardId) !== -1){
            return myCards[i].type;
        }
    }
    return null;
}

/* give minion class nonAttack
 check if charge is present
check if battleCry is present
let type = returnTypeOfMyCards(drag);
switch (type){
    case 'Minion':
        dropZoneElement.appendChild(dragSrcElement);
        break;
    case 'Weapon':
        document.querySelector('.weapon').appendChild(dragSrcElement);
        break;
}
drag.addEventListener('click', visualiseAttack); */



function endturn() {
    // delete all class nonAttack
}

function visualiseAttack(e) {
    // check if a card has class nonAttack
    // check if their is one or more minions with taunt
    // not attack on other minions
    // begin drag&drop without removing li
    // dragend check on what element the target is dropped and does the *real* attack function
    console.log('cannot attack');
    attack();
}

function attackStart(e) {
    let target = e.target;
    if (e.path[0].tagName === 'SPAN'){
        target = e.target.parentElement;
    }
    console.log(target);
    console.log('cannot attack');
    dragOffsetX = e.offsetX;
    dragOffsetY = e.offsetY;
    itemThatIsBeingMoved = target;
    moved = false;
    drag = target.cloneNode(true);
    document.body.appendChild(drag);
    drag.removeAttribute('style');

    movingOfDragElement(e);
    drag.style.zIndex = '1';
    drag.style.width = '9.9032258064516129032258064516131vh';
    drag.style.height= '15vh';
    drag.style.position = 'absolute';
    drag.style.borderRadius = '50%';
    drag.style.background = target.style.background;

    document.addEventListener("touchmove", movingOfDragElement, false);
    document.addEventListener("mousemove", movingOfDragElement, false);
    document.addEventListener("mouseup", attackEnd, false);
    document.addEventListener("touchend", attackEnd, false);
}

function attackEnd() {

    document.removeEventListener("touchmove", movingOfDragElement, false);
    document.removeEventListener("mousemove", movingOfDragElement, false);
    document.removeEventListener("mouseup", attackEnd, false);
    document.removeEventListener("touchend", attackEnd, false);
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