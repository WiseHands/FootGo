function submissionData(event) {

    if(tourId) {
        let params = '?tourId=' + tourId;
        let apiUrl = '/api/season/' + seasonId + '/leaguelist/' + leagueId + '/tour/details' + params;
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
                alert('Помилка при створенні гри');
            }
            return  response.json();
        }).then(function(data) {
            console.log(data);
        })
    }
}