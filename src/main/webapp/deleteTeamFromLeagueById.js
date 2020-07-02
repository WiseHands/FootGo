function deleteTeamFromLeagueById(teamId) {
    let params = '?teamId=' + teamId;
    let apiUrl = '/api/season/' + seasonId + '/leaguelist/' + leagueId + '/team' + params;
    fetch(apiUrl, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
      }
    }).then(function(response){
        console.log(response);
        if(response.ok) {
            location.pathname = location.pathname;
        } else {
            alert('Помилка при видаленні команди');
        }
    }).then(function(data) {
    }).catch(function(error) {
          console.log('Request failed', error);})
}