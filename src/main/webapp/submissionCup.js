/*const path = location.toString();
let url = new URL(path).pathname;
let regex = /\d+/g;
let seasonId = url.match(regex);*/

function submissionData(event) {
    let cupName = document.getElementById('cupName').value;
    let cupNameEn = document.getElementById('cupNameEn').value;

    let cupNameInput = document.getElementById('cupName');
    let cupNameEnInput = document.getElementById('cupNameEn');

        if (!cupName){
            cupNameInput.setAttribute('invalid-input', true);
        } else {
            cupNameInput.removeAttribute('invalid-input');
        }

        if (!cupNameEn){
            cupNameEnInput.setAttribute('invalid-input', true);
        } else {
            cupNameEnInput.removeAttribute('invalid-input');
        }

    const checkEmptyCupName = document.getElementById("cupName");
    const checkEmptyCupNameEn = document.getElementById("cupNameEn");
    if (checkEmptyCupName.value == "" && checkEmptyCupName.value.length == 0 || checkEmptyCupNameEn.value == "" && checkEmptyCupNameEn.value.length == 0) {
        document.getElementById('showInputAddCupNameError').style.display = "block";

        return false;
    }
    if (checkEmptyCupName && checkEmptyCupNameEn) {
        function getCheckedValues() {
            return Array.from(document.querySelectorAll('input[type="checkbox"]'))
            .filter((checkbox) => checkbox.checked)
            .map((checkbox) => checkbox.value);
        }

        let cupDataJsonObj = {"name" : cupName, "nameEn" : cupNameEn, "teamList" : getCheckedValues()};
        let jsonCupInString = JSON.stringify(cupDataJsonObj);

        let apiUrl = '/api/season/' + seasonId + '/cup';
        fetch(apiUrl, {
            method: 'POST',
            body: jsonCupInString,
            headers: {
              'Content-Type': 'application/json',
            }
        }).then(function(response) {
            console.log(response);
            if(response.ok) {
                location.pathname = '/admin/tournament/' + tournamentId + '/season/' + seasonId + '/cuplist';
            }
            else {
                alert('Помилка при створенні кубка');
            }
            return  response.json();
        }).then(function(data) {
            console.log(data);
        })
    }
}
