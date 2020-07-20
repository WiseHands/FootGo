function saveStats() {
    let strikesTeamA = Number(document.getElementById('strikesTeamA').value);
    let hitsTheTargetTeamA = Number(document.getElementById('hitsTheTargetTeamA').value);
    let strikesPastTheGateTeamA = Number(document.getElementById('strikesPastTheGateTeamA').value);
    let freeKicksTeamA = Number(document.getElementById('freeKicksTeamA').value);
    let foulsTeamA = Number(document.getElementById('foulsTeamA').value);
    let cornerKicksTeamA = Number(document.getElementById('cornerKicksTeamA').value);

    let strikesTeamB = document.getElementById('strikesTeamB').value;
    let hitsTheTargetTeamB = document.getElementById('hitsTheTargetTeamB').value;
    let strikesPastTheGateTeamB = document.getElementById('strikesPastTheGateTeamB').value;
    let freeKicksTeamB = document.getElementById('freeKicksTeamB').value;
    let foulsTeamB = document.getElementById('foulsTeamB').value;
    let cornerKicksTeamB = document.getElementById('cornerKicksTeamB').value;

    let statsTeamDataJsonObj = {
        "teamA": {
            "strikes": strikesTeamA,
            "hitsTheTarget": hitsTheTargetTeamA,
            "strikesPastTheGate": strikesPastTheGateTeamA,
            "freeKicks": freeKicksTeamA,
            "fouls": foulsTeamA,
            "cornerKicks": cornerKicksTeamA
        },
        "teamB": {
            "strikes": strikesTeamB,
            "hitsTheTarget": hitsTheTargetTeamB,
            "strikesPastTheGate": strikesPastTheGateTeamB,
            "freeKicks": freeKicksTeamB,
            "fouls": foulsTeamB,
            "cornerKicks": cornerKicksTeamB
        }
    };

    let jsonStatsTeamInString = JSON.stringify(statsTeamDataJsonObj);

    console.log("LOG JSON_STR " + jsonStatsTeamInString);

    let apiUrl = '/api/game/' + gameId + '/setgamestats';

    fetch(apiUrl, {
      method: 'PUT',
      body: jsonStatsTeamInString,
      headers: {
        'Content-Type': 'application/json',
      }
    })
    .then(function(response){
        if(response.ok) {
            location.pathname = location.pathname;
        	document.getElementById('statsSuccess').style.display = 'block';
        } else {
        	document.getElementById('statsError').style.display = 'block';
        }
        console.log(response)
    })
    .then(function(data) {
    })
}
