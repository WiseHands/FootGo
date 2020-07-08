function deleteGameById(event) {

    if(tourId) {
        let params = '?tourId=' + tourId;
        let apiUrl = '/api/season/' + seasonId + '/leaguelist/' + leagueId + '/game/' + gameId + params;
        fetch(apiUrl, {
            method: 'DELETE',
            headers: {
              'Content-Type': 'application/json',
            }
        }).then(function(response) {
            console.log(response);
            if(response.ok) {
                window.location = '/admin/tournament/' + tournamentId + '/season/' + seasonId + '/leaguelist/' + leagueId + '/tour/details' + '?uuid=' + tourId;
            }
            else {
                alert('Помилка при видаленні гри');
            }
            return  response.json();
        }).then(function(data) {
            console.log(data);
        })
    }
}