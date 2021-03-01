function deleteSponsorFromSeasonById(sponsorId) {
    let params = '?sponsorId=' + sponsorId;
    let apiUrl = '/api/season/' + seasonId + '/sponsors/' + params;
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
            alert('Помилка при видаленні');
        }
    }).then(function(data) {
    }).catch(function(error) {
          console.log('Request failed', error);})
}