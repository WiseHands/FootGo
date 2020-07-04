function submissionData(event) {

    let apiUrl = '/api/season/' + seasonId + '/leaguelist/' + leagueId + '/tour';
    fetch(apiUrl, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        }
    }).then(function(response) {
        console.log(response);
        if(response.ok) {
            location.pathname = location.pathname;
        }
        else {
            alert('Помилка при створенні туру');
        }
        return  response.json();
    }).then(function(data) {
        console.log(data);
    })
}