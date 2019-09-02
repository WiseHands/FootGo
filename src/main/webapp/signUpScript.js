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

    var jsonObject = new Object();
    jsonObject.teamName = teamName;
    jsonObject.captainName = captainName;
    jsonObject.captainPhone = captainPhone;
    jsonObject.captainEmail = captainEmail;
    var jsonTeamInString = JSON.stringify(jsonObject);

    if (teamName && captainName && captainPhone && captainEmail){
        var url = '/team/signuprequest';
        sendPostRequestSignUp(jsonTeamInString, url);
        document.getElementById('hideifsuccess_0').style.display = 'none';
        document.getElementsByClassName('reg-input-button')[0].style.display = 'none';
    }

    console.log("press button" + teamName
        + " " + captainName
        + " " + captainPhone
        + " " + captainEmail
        + " " + jsonTeamInString
        + " " + "/team/signuprequest");
}

// http post request
function sendPostRequestSignUp(data, url) {
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: data
    })
        .then(function (response) {
            console.log('Request succeeded with JSON response', data);
            if(response.ok) {
				document.getElementById('success').style.display = 'block';
            } else {
            	document.getElementById('error').style.display = 'block';
            }
        })
        .catch(function (error) {
            console.log('Request failed', error);
        });

}