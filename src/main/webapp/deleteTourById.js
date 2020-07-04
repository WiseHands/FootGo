function deleteTourById(event) {

    if(tourId) {
        let params = '?tourId=' + tourId;
        let apiUrl = '/api/season/' + seasonId + '/leaguelist/' + leagueId + '/tour/details' + params;
        fetch(apiUrl, {
            method: 'DELETE',
            headers: {
              'Content-Type': 'application/json',
            }
        }).then(function(response) {
            console.log(response);
            if(response.ok) {
                location.pathname = '/admin/tournament/' + tournamentId + '/season/' + seasonId + '/leaguelist/' + leagueId + '/tour';
            }
            else {
                alert('Помилка при видаленні туру');
            }
            return  response.json();
        }).then(function(data) {
            console.log(data);
        })
    }
}