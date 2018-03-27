"use strict";

document.addEventListener("DOMContentLoaded", init);

function init(e) {
    //setTimeout(startLoadingScreen, 5000);
}

function startLoadingScreen() {
    document.querySelector("#loadingScreen").classList.toggle("hidden");
    document.querySelector("#loadingScreen figure").classList.add("fade");
}