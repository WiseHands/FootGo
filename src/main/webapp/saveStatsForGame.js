function saveStats() {
    let strikesTeamA = document.getElementById('strikesTeamA').value;
    let hitsTheTargetTeamA = document.getElementById('hitsTheTargetTeamA').value;
    let strikesPastTheGateTeamA = document.getElementById('strikesPastTheGateTeamA').value;
    let freeKicksTeamA = document.getElementById('freeKicksTeamA').value;
    let foulsTeamA = document.getElementById('foulsTeamA').value;
    let cornerKicksTeamA = document.getElementById('cornerKicksTeamA').value;

    let strikesTeamB = document.getElementById('strikesTeamB').value;
    let hitsTheTargetTeamB = document.getElementById('hitsTheTargetTeamB').value;
    let strikesPastTheGateTeamB = document.getElementById('strikesPastTheGateTeamB').value;
    let freeKicksTeamB = document.getElementById('freeKicksTeamB').value;
    let foulsTeamB = document.getElementById('foulsTeamB').value;
    let cornerKicksTeamB = document.getElementById('cornerKicksTeamB').value;

    //let teamA = '?strikesTeamA=' + strikesTeamA + '&hitsTheTargetTeamA=' + hitsTheTargetTeamA + '&strikesPastTheGateTeamA=' + strikesPastTheGateTeamA + '&freeKicksTeamA=' + freeKicksTeamA + '&foulsTeamA=' + foulsTeamA + '&cornerKicksTeamA=' + cornerKicksTeamA;

    let statsTeamADataJsonObj = { "strikesTeamA" : strikesTeamA, "hitsTheTargetTeamA" : hitsTheTargetTeamA, "strikesPastTheGateTeamA" : strikesPastTheGateTeamA, "freeKicksTeamA" : freeKicksTeamA, "foulsTeamA" : foulsTeamA, "cornerKicksTeamA" : cornerKicksTeamA };
    //let statsTeamBDataJsonObj = {"strikesTeamB" : strikesTeamB, "hitsTheTargetTeamB" : hitsTheTargetTeamB, "strikesPastTheGateTeamB" : strikesPastTheGateTeamB, "freeKicksTeamB" : freeKicksTeamB, "foulsTeamB" : foulsTeamB, "cornerKicksTeamB" : cornerKicksTeamB};

    let jsonStatsTeamAInString = JSON.stringify(statsTeamADataJsonObj);
    //let jsonStatsTeamBInString = JSON.stringify(statsTeamBDataJsonObj);

    console.log("LOG " + jsonStatsTeamAInString);

    let apiUrl = '/api/game/' + gameId + '/setgamestats';

    fetch(apiUrl, {
      method: 'PUT',
      body: jsonStatsTeamAInString,
      headers: {
        'Content-Type': 'application/json',
      }
    })
    .then(function(response){
        if(response.ok) {
            //location.pathname = location.pathname;
        	document.getElementById('statsSuccess').style.display = 'block';
        } else {
        	document.getElementById('statsError').style.display = 'block';
        }
        console.log(response)
    })
    .then(function(data) {
    })
}
