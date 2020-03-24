const path = location.toString();
let url = new URL(path).pathname;
let regex = /\d+/g;
let seasonId = url.match(regex);

function submissionData(event) {
    let leagueName = document.getElementById('leagueName').value;

    const checkEmpty = document.getElementById("leagueName");
    if (checkEmpty.value == "" && checkEmpty.value.length == 0) {
        document.getElementById('showInputAddLeagueNameError').style.display = "block";
        checkEmpty.classList.add("league-name-error");
        return false;
    }
    if(leagueName) {
        function getCheckedValues() {
            return Array.from(document.querySelectorAll('input[type="checkbox"]'))
            .filter((checkbox) => checkbox.checked)
            .map((checkbox) => checkbox.value);
        }

        let leagueDataJsonObj = {"name" : leagueName, "teamList" : getCheckedValues()};
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
                location.pathname = '/admin/season/' + seasonId + '/leaguelist';
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
