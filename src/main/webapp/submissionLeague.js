/*const path = location.toString();
let url = new URL(path).pathname;
let regex = /\d+/g;
let seasonId = url.match(regex);*/

function submissionData(event) {

    let leagueNameInput = document.getElementById("leagueName");
    let leagueNameEnInput = document.getElementById("leagueNameEn");
    let leagueName = leagueNameInput.value;
    let leagueNameEn = leagueNameEnInput.value;

    if (!leagueName){
        leagueNameInput.setAttribute('invalid-input', true);
    } else {
        leagueNameInput.removeAttribute('invalid-input');
    }

    if (!leagueNameEn){
        leagueNameEnInput.setAttribute('invalid-input', true);
    } else {
        leagueNameEnInput.removeAttribute('invalid-input');
    }

    let leagueNameValue = document.getElementById('leagueName').value;
    let leagueNameEnValue = document.getElementById('leagueNameEn').value;

    const checkEmptyLeagueName = document.getElementById("leagueName");
    const checkEmptyLeagueNameEn = document.getElementById("leagueNameEn");
    if (checkEmptyLeagueName.value == "" && checkEmptyLeagueName.value.length == 0 || checkEmptyLeagueNameEn.value == "" && checkEmptyLeagueNameEn.value.length == 0) {
        document.getElementById('showInputAddLeagueNameError').style.display = "block";

        return false;
    } else {
        document.getElementById('showInputAddLeagueNameError').style.display = "none";
    }
    if (leagueNameValue && leagueNameEnValue) {
        function getCheckedValues() {
            return Array.from(document.querySelectorAll('input[type="checkbox"]'))
            .filter((checkbox) => checkbox.checked)
            .map((checkbox) => checkbox.value);
        }

        let leagueDataJsonObj = {"name" : leagueNameValue, "nameEn" : leagueNameEnValue, "teamList" : getCheckedValues()};
        let jsonLeagueInString = JSON.stringify(leagueDataJsonObj);

        let apiUrl = '/api/season/' + seasonId + '/league';
        fetch(apiUrl, {
            method: 'POST',
            body: jsonLeagueInString,
            headers: {
              'Content-Type': 'application/json',
            }
        }).then(function(response) {
            console.log(response);
            if(response.ok) {
                location.pathname = '/admin/tournament/' + tournamentId + '/season/' + seasonId + '/leaguelist';
            }
            else {
                alert('Помилка при створенні ліги');
            }
            return  response.json();
        }).then(function(data) {
            console.log(data);
        })
    }
}
