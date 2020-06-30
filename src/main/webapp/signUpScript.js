// sign up form for team registration
function submitform(ev) {

    function validateEmail(email) {
        var se = /^[\w\.\-_]{1,}@[\w\.\-]{6,}/
        return se.test(email);
    }

    function emailIsValid(element, isValid) {
        var elementValue = element.value
            isNotEmpty = (elementValue != '' || elementValue != null)

        if(isValid && isNotEmpty) {
            if(element.classList.contains('invalid')) {
                removeClass(element, 'invalid');
            } else {
                addClass(element, 'valid');
            }
        } else {
           if(element.classList.contains('valid')) {
               removeClass(element, 'valid');
           } else {
               addClass(element, 'invalid');
           }
    }
}

    function addClass(element, eleClass) {
        element.classList.add(eleClass);
    }
    function removeClass(element, eleClass) {
        element.classList.remove(eleClass);
    }

    var emailInput = document.getElementById('captainEmail');

    emailInput.addEventListener('keyup', function(event) {
    console.log("email input value " + this.value);
    console.log("validateEmail " + validateEmail(this.value));
    var validatedEmail = validateEmail(this.value);
    emailIsValid(this, validatedEmail);
    });

    emailIsValid(emailInput, validateEmail(emailInput.value));

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
/*    var captainEmailInput = document.signUpForm.captainEmail;
    var captainEmail = captainEmailInput.value;*/
    var captainPhoneStripped = captainPhone.replace(/\D/g,'');
    var phoneReg = /^[0-9()-.\s]+$/

    if (captainPhone && phoneReg.test(captainPhone) && captainPhoneStripped.length >= 10) {
        captainPhoneInput.removeAttribute('invalid-input');
        var validationOk = true;
    } else {
        captainPhoneInput.setAttribute('invalid-input', true);
        var validationOk = false;
    }

/*    if (!captainEmail){
        captainEmailInput.setAttribute('invalid-input', true);
    } else {
        captainEmailInput.removeAttribute('invalid-input');
    }*/

    var jsonObject = new Object();
    jsonObject.teamName = teamName;
    jsonObject.captainName = captainName;
    jsonObject.captainPhone = captainPhone;
    jsonObject.captainEmail = captainEmail;
    var jsonTeamInString = JSON.stringify(jsonObject);

    if (teamName && captainName && captainPhone && validationOk) {
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