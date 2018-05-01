"use strict";

document.addEventListener("DOMContentLoaded", onLoad);

function onLoad() {
    let forms = document.querySelectorAll("form");

    for(let i=0; i<forms.length; i++) {
        forms[i].addEventListener("submit", onFormSubmit)
    }
}

function onFormSubmit(e) {
    e.preventDefault();
    let formId = e.target.id;

    tryRequest(formId);
    resetForm(formId);
}

function resetForm(formId) {
    document.querySelector(`#${formId}`).reset();
    let resultSpans = document.querySelectorAll('.result');

    for(let i=0; i<resultSpans.length; i++) {
        resultSpans[i].innerText = null;
    }
}

function tryRequest(formId) {
    let form = document.querySelector(`#${formId}`);

    fetch('assets/php/request.php', {
        method: 'post',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `request=${formId}&${new URLSearchParams(new FormData(form))}`
    })
        .then(function(res) {
            if(res.ok === true)
                return res.json();
        })
        .then(function(text) {
            console.log(text);
            showResult(text);
        })
        .catch(function(err) {
            showResult(err.message);
        });
}

function showResult(result) {
    document.querySelector(".result").innerText = result;
}