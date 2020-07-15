function deleteGameById(event) {

    if(tourId) {
        let params = '?tourId=' + tourId;
        let apiUrl = '/api/season/' + seasonId + '/cuplist/' + cupId + '/game/' + gameId + params;
        fetch(apiUrl, {
            method: 'DELETE',
            headers: {
              'Content-Type': 'application/json',
            }
        }).then(function(response) {
            console.log(response);
            if(response.ok) {
                window.location = '/admin/tournament/' + tournamentId + '/season/' + seasonId + '/cuplist/' + cupId + '/tour/details' + '?uuid=' + tourId;
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