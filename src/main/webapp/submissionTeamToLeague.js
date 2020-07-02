function submissionTeamToLeague(event) {

    function getCheckedValues() {
        return Array.from(document.querySelectorAll('input[type="checkbox"]'))
        .filter((checkbox) => checkbox.checked)
        .map((checkbox) => checkbox.value);
    }

    let leagueDataJsonObj = {"teamList" : getCheckedValues()};
    let jsonLeagueInString = JSON.stringify(leagueDataJsonObj);

    let apiUrl = '/api/season/' + seasonId + '/leaguelist/' + leagueId + '/team/add';
    fetch(apiUrl, {
        method: 'POST',
        body: jsonLeagueInString,
        headers: {
          'Content-Type': 'application/json',
        }
    }).then(function(response) {
        console.log(response);
        if(response.ok) {
            location.pathname = '/admin/tournament/' + tournamentId + '/season/' + seasonId + '/leaguelist/' + leagueId + '/team';
        }
        else {
            alert('Помилка при збереженні команди в лізі');
        }
        return  response.json();
    }).then(function(data) {
        console.log(data);
    })
}
