document.getElementById('nav-icon').addEventListener("click", function(){
    document.getElementById("nav-icon").classList.toggle('open');
});

// sign up form for team registration
function submitform(ev) {
    event.preventDefault();

    document.signUpForm.teamName.value.required;
    var teamNameInput = document.signUpForm.teamName;
    var teamName = teamNameInput.value;
    if (!teamName){
        teamNameInput.setAttribute('invalid-input', true);
    } else {
        teamNameInput.removeAttribute('invalid-input');
    }
    var captainNameInput = document.signUpForm.captainName;
    var captainName = captainNameInput.value;
    if (!captainName){
        captainNameInput.setAttribute('invalid-input', true);
    } else {
        captainNameInput.removeAttribute('invalid-input');
    }
    var captainPhoneInput = document.signUpForm.captainPhone;
    var captainPhone = captainPhoneInput.value;
    if (!captainPhone){
        captainPhoneInput.setAttribute('invalid-input', true);
    } else {
        captainPhoneInput.removeAttribute('invalid-input');
    }
    var captainEmailInput = document.signUpForm.captainEmail;
    var captainEmail = captainEmailInput.value;
    if (!captainEmail){
        captainEmailInput.setAttribute('invalid-input', true);
    } else {
        captainEmailInput.removeAttribute('invalid-input');
    }


    var playerList = new Array();
    playerList.push(document.signUpForm.player1.value);
    playerList.push(document.signUpForm.player2.value);
    playerList.push(document.signUpForm.player3.value);
    playerList.push(document.signUpForm.player4.value);
    playerList.push(document.signUpForm.player5.value);
    playerList.push(document.signUpForm.player6.value);
    playerList.push(document.signUpForm.player7.value);
    playerList.push(document.signUpForm.player8.value);
    playerList.push(document.signUpForm.player9.value);
    playerList.push(document.signUpForm.player10.value);
    playerList.push(document.signUpForm.player11.value);
    playerList.push(document.signUpForm.player12.value);
    var players = playerList.toString();

    var jsonObject = new Object();
    jsonObject.teamName = teamName;
    jsonObject.captainName = captainName;
    jsonObject.captainPhone = captainPhone;
    jsonObject.captainEmail = captainEmail;
    jsonObject.playerList = players;
    var jsonTeamInString = JSON.stringify(jsonObject);

    if (teamName && captainName && captainPhone && captainEmail){
        var url = '/team/signuprequest';
        sendPostRequestSignUp(jsonTeamInString, url);
    }

    console.log("press button" + teamName
        + " " + captainName
        + " " + captainPhone
        + " " + captainEmail
        + " " + playerList
        + " " + jsonTeamInString
        + " " + "/team/signuprequest");
}

// sign up form for admin
var adminSignUp = document.getElementById('adminSignUp');
adminSignUp.addEventListener('click', function () {
    // Some code...
    console.log('press button');
    var emailInput = document.adminSignUpForm.email;
    var email = emailInput.value;
    if (!email){
        emailInput.setAttribute('invalid-input', true);
    } else {
        emailInput.removeAttribute('invalid-input');
    }

    var passwordInput = document.adminSignUpForm.password;
    var password = passwordInput.value;
    if (!password){
        passwordInput.setAttribute('invalid-input', true);
    } else {
        passwordInput.removeAttribute('invalid-input');
    }
    console.log("submitForm");

    var jsonObject = new Object();
    jsonObject.email = email;
    jsonObject.password = password;
    var json = JSON.stringify(jsonObject);

    if (email && password){
        var url = '/admin/login';
        sendPostRequestSignUp(json, url);
    }

});

// http post request
function sendPostRequestSignUp(data, url) {
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: data
    })
        .then(function (data) {
            console.log('Request succeeded with JSON response', data);
        })
        .catch(function (error) {
            console.log('Request failed', error);
        });

}