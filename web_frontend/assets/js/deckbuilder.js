"use strict";

document.addEventListener('DOMContentLoaded', init);

function init() {
    document.querySelector('#firstadd').addEventListener('click', firstadd);

}
    /*getAllCards();
}
*/
function firstadd() {
    /* apart scherm dat vraagt of deck moet opgeslaan worden*/
    document.querySelector('.main').classList.toggle('hidden');
    document.querySelector('.save').classList.toggle('hidden');
}
/*
function getAllCards() {
    console.log('test');
    fetch('/API/getAllCards', {headers: {
            'content-type': 'application/json'}})
        .then(function (response) {
        if (response === ok) return response
    }).then(function (text) {
        console.log(text);
    })
}*/