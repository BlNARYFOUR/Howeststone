"use strict";
document.addEventListener("DOMContentLoaded", init);

function init() {
    document.querySelector('#saveGame').addEventListener('click', saveGame);
    document.querySelector('#playMainMenuMusic').addEventListener('click',playPauseMusic)

}


function saveGame() {
    // TODO fetch
    alert("NYI");
}

function playPauseMusic(e) {
    e.preventDefault();

    document.querySelector("#playMainMenuMusic").classList.toggle("muted");
    let audio = document.getElementById('mainMenuMusic');
    if (audio.paused) {
        audio.play();
    }else{
        audio.pause();
        audio.currentTime = 0
    }
}