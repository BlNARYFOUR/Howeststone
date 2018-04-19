"use strict";

document.addEventListener("DOMContentLoaded", init);

let cardCounter = 0;
let cardBackUrl = "";
let dragSrcEl = null;
let myCards = null;

function init() {
    setupDraggingOfCards();
    //document.querySelector("#tempButtonAddCard").addEventListener('click', addCard);
    setBackground();
    getRandomCardBack();
    //makeCardsFan("you", 1);

    /*
    document.getElementById("spark").addEventListener("click", burnFuse);
    document.getElementById("fuse").addEventListener("click", burnFuse);
    */
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
    setupDraggingOfCards();
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
        updateMyCards();
    }
}

function handleDoubleClickAsDrop(e) {
    dropInDropZone(e.target, document.querySelector("#gameBoard .you .playingField .dropZone"));
	updateMyCards();
}

function dropInDropZone(dragSrcElement, dropZoneElement) {
    dragSrcElement.draggable = false;
    dragSrcElement.removeEventListener("dblclick", handleDoubleClickAsDrop);

    let background = dragSrcElement.style.background;
    dragSrcElement.removeAttribute("style");
    dragSrcElement.style.color = "transparent";
    dragSrcElement.style.border = "none";
    dragSrcElement.style.background = background;
    dragSrcElement.style.backgroundSize = "176%";
    dragSrcElement.style.backgroundPositionY = "23%";

    dragSrcElement.parentNode.removeChild(dragSrcElement);
    dropZoneElement.appendChild(dragSrcElement);
}