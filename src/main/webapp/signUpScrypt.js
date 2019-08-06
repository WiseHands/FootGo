
document.getElementById('nav-icon').addEventListener("click", function(){
    document.getElementById("nav-icon").classList.toggle('open');
});

function submitform(ev) {
    event.preventDefault();

    document.signUpForm.teamName.value.required;
    var teamName = document.signUpForm.teamName.value;
    var captainName = document.signUpForm.captainName.value.required;
    var captainPhone = document.signUpForm.captainPhone.value.required;
    var captainEmail = document.signUpForm.captainEmail.value.required;

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

    var url = '/team/signuprequest';
    var data = jsonTeamInString;

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

    console.log("press button" + teamName
        + " " + captainName
        + " " + captainPhone
        + " " + captainEmail
        + " " + playerList
        + " " + jsonTeamInString
        + " " + "/team/signuprequest");
}