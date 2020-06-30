// sign up form for team registration

function teamNameValidation() {

    let teamNameInput = document.getElementById('teamName');
    let teamName = document.getElementById('teamName').value;
    let letters = /^[A-Za-zА-Яа-я0-9\s]+$/;

    if (teamName.match(letters)) {
        teamNameInput.classList.add("valid");
        teamNameInput.classList.remove("invalid");

        return true;
    } else {
        teamNameInput.classList.remove("valid");
        teamNameInput.classList.add("invalid");

        return false;
    }
    if (teamName == "") {
        teamNameInput.classList.remove("valid");
        teamNameInput.classList.remove("invalid");
    }
}

function captainNameValidation() {
    let captainNameInput = document.getElementById('captainName');
    let captainName = document.getElementById('captainName').value;
    let letters = /^[A-Za-zА-Яа-я\s]+$/;

    if (captainName.match(letters)) {
        captainNameInput.classList.add("valid");
        captainNameInput.classList.remove("invalid");

        return true;
    } else {
        captainNameInput.classList.remove("valid");
        captainNameInput.classList.add("invalid");

        return false;
    }
    if (captainName == "") {
        captainNameInput.classList.remove("valid");
        captainNameInput.classList.remove("invalid");
    }
}

function emailValidation() {

    let emailInput = document.querySelector('[type="email"]');
    let email = document.querySelector('[type="email"]').value;
    let pattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;

    if (email.match(pattern)) {
        emailInput.classList.add("valid");
        emailInput.classList.remove("invalid");

        return true;
    } else {
        emailInput.classList.remove("valid");
        emailInput.classList.add("invalid");

        return false;
    }
    if (email == "") {
        emailInput.classList.remove("valid");
        emailInput.classList.remove("invalid");
    }

}

function submitform(ev) {
console.log("EMAIL VALIDATION " + emailValidation() + " CAPTAIN NAME VALIDATION " + captainNameValidation() + " TEAM NAME VALIDATION " + teamNameValidation());

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

    var captainEmailInput = document.signUpForm.captainEmail;
    var captainEmail = captainEmailInput.value;

    var captainPhoneStripped = captainPhone.replace(/\D/g,'');
    var phoneReg = /^[0-9()-.\s]+$/

    if (captainPhone && phoneReg.test(captainPhone) && captainPhoneStripped.length >= 10) {
        captainPhoneInput.removeAttribute('invalid-input');
        var phoneValidationOk = true;
    } else {
        captainPhoneInput.setAttribute('invalid-input', true);
        var phoneValidationOk = false;
    }

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

    if (teamName && captainName && captainPhone && phoneValidationOk && emailValidation() && teamNameValidation() && captainNameValidation()) {
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