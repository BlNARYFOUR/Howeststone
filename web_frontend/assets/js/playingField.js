"use strict";
document.addEventListener("DOMContentLoaded", init);

function init() {
    setBackground();
    document.getElementById("spark").addEventListener("click", burnFuse);
    document.getElementById("fuse").addEventListener("click", burnFuse);
}

function burnFuse(e) {
    document.getElementById("spark").classList.add("burn");
    document.getElementById("fuse").classList.add("burn");
}

function setBackground() {
    let rand = getRandomInt(2) + 1;

    document.getElementById("playField").style.background = "no-repeat black url(assets/media/board_0" + rand + ".jpg) center center";
    document.getElementById("playField").style.backgroundSize = "contain";
}

function getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max));
}