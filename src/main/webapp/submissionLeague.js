/*const path = location.toString();
let url = new URL(path).pathname;
let regex = /\d+/g;
let seasonId = url.match(regex);*/

function submissionData(event) {
    let leagueName = document.getElementById('leagueName').value;
    let leagueNameEn = document.getElementById('leagueNameEn').value;

    const checkEmptyLeagueName = document.getElementById("leagueName");
    const checkEmptyLeagueNameEn = document.getElementById("leagueNameEn");
    if (checkEmptyLeagueName.value == "" && checkEmptyLeagueName.value.length == 0 || checkEmptyLeagueNameEn.value == "" && checkEmptyLeagueNameEn.value.length == 0) {
        document.getElementById('showInputAddLeagueNameError').style.display = "block";
        checkEmpty.classList.add("required-fields");
        return false;
    }
    if (leagueName && leagueNameEn) {
        function getCheckedValues() {
            return Array.from(document.querySelectorAll('input[type="checkbox"]'))
            .filter((checkbox) => checkbox.checked)
            .map((checkbox) => checkbox.value);
        }

        let leagueDataJsonObj = {"name" : leagueName, "nameEn" : leagueNameEn, "teamList" : getCheckedValues()};
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
