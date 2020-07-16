function saveStats() {
    let strikesTeamOne = document.getElementById('strikesTeamOne').value;
    let hitsTheTargetTeamOne = document.getElementById('hitsTheTargetTeamOne').value;
    let strikesPastTheGateTeamOne = document.getElementById('strikesPastTheGateTeamOne').value;
    let freeKicksTeamOne = document.getElementById('freeKicksTeamOne').value;
    let foulsTeamOne = document.getElementById('foulsTeamOne').value;
    let cornerKicksTeamOne = document.getElementById('cornerKicksTeamOne').value;

    let strikesTeamTwo = document.getElementById('strikesTeamTwo').value;
    let hitsTheTargetTeamTwo = document.getElementById('hitsTheTargetTeamTwo').value;
    let strikesPastTheGateTeamTwo = document.getElementById('strikesPastTheGateTeamTwo').value;
    let freeKicksTeamTwo = document.getElementById('freeKicksTeamTwo').value;
    let foulsTeamTwo = document.getElementById('foulsTeamTwo').value;
    let cornerKicksTeamTwo = document.getElementById('cornerKicksTeamTwo').value;

    //let teamOne = '?teamOne=' + strikesTeamOne + hitsTheTargetTeamOne + strikesPastTheGateTeamOne + freeKicksTeamOne + foulsTeamOne + cornerKicksTeamOne;
    //let teamTwo = '&teamTwo=' + strikesTeamTwo + hitsTheTargetTeamTwo + strikesPastTheGateTeamTwo + freeKicksTeamTwo + foulsTeamTwo + cornerKicksTeamTwo;

    let statsTeamOneDataJsonObj = {"strikesTeamOne" : strikesTeamOne, "hitsTheTargetTeamOne" : hitsTheTargetTeamOne,
    "strikesPastTheGateTeamOne" : strikesPastTheGateTeamOne, "freeKicksTeamOne" : freeKicksTeamOne,
    "foulsTeamOne" : foulsTeamOne, "cornerKicksTeamOne" : cornerKicksTeamOne};

    let statsTeamTwoDataJsonObj = {"strikesTeamTwo" : strikesTeamTwo,
        "hitsTheTargetTeamTwo" : hitsTheTargetTeamTwo, "strikesPastTheGateTeamTwo" : strikesPastTheGateTeamTwo,
        "freeKicksTeamTwo" : freeKicksTeamTwo, "foulsTeamTwo" : foulsTeamTwo, "cornerKicksTeamTwo" : cornerKicksTeamTwo};

    let jsonStatsTeamOneInString = JSON.stringify(statsTeamOneDataJsonObj);
    let jsonStatsTeamTwoInString = JSON.stringify(statsTeamTwoDataJsonObj);

    console.log("LOG " + jsonStatsTeamOneInString + jsonStatsTeamTwoInString);

    let apiUrl = '/api/game/' + gameId + '/setgamestats';

    fetch(apiUrl, {
      method: 'PUT',
      body: jsonStatsTeamOneInString + jsonStatsTeamTwoInString,
      headers: {
        'Content-Type': 'application/json',
      }
    })
    .then(function(response){
        if(response.ok) {
        	document.getElementById('statsSuccess').style.display = 'block';
        } else {
        	document.getElementById('statsError').style.display = 'block';
        }
        console.log(response)
    })
    .then(function(data) {
    })
}
