"use strict";

document.addEventListener("DOMContentLoaded", init);

function init(e) {
    //setTimeout(startLoadingScreen, 5000);
}

function startLoadingScreen() {
    document.querySelector("#loadingScreen").classList.remove("hidden");
    setTimeout(fade, 10);   // Without this, the fade would not activate
    function fade() {
        document.querySelector("#loadingScreen figure").classList.add("fade");
    }
}

function stopLoadingScreen() {
    document.querySelector("#loadingScreen").classList.add("hidden");
    setTimeout(fade, 10);   // Without this, the fade would not activate
    function fade() {
        document.querySelector("#loadingScreen figure").classList.remove("fade");
    }
}