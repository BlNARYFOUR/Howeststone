"use strict";
document.addEventListener("DOMContentLoaded", init);

function init() {
    document.querySelector('#playMainMenuMusic').addEventListener('click',playPauseMusic)

}

function playPauseMusic() {
    let audio = document.getElementById('mainMenuMusic');
    if (audio.paused) {
        audio.play();
    }else{
        audio.pause();
        audio.currentTime = 0
    }
}