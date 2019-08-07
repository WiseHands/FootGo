
document.getElementById('nav-icon').addEventListener("click", function(){
    document.getElementById("nav-icon").classList.toggle('open');
});

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
        sendPostRequestTeamSignUp(jsonTeamInString, url);
    }



    console.log("press button" + teamName
        + " " + captainName
        + " " + captainPhone
        + " " + captainEmail
        + " " + playerList
        + " " + jsonTeamInString
        + " " + "/team/signuprequest");
}

function sendPostRequestTeamSignUp(data, url) {
    fetch(url, {
        method: 'post',
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(function (data) {
            console.log('Request succeeded with JSON response', data);
        })
        .catch(function (error) {
            console.log('Request failed', error);
        });

}