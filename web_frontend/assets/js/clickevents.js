"use strict";

document.addEventListener('DOMContentLoaded', init);

function init() {
    document.getElementById('gotoPlay').addEventListener('click', gotoPlay);
    document.getElementById('gotoHome').addEventListener('click', gotoHome);
}

function gotoHome() {
    document.getElementById('mainMenu').className = "";
    document.getElementById('heroPicker').className = "hidden";
}


function gotoPlay() {
    document.getElementById('mainMenu').className = "hidden";
    document.getElementById('heroPicker').className = "";
}
