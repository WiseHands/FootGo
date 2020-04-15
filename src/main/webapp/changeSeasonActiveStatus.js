document.addEventListener('DOMContentLoaded', function() {

    let apiUrl = '/api/tournament/get_active_season?tournamentId=' + tournamentId;
    fetch(apiUrl, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        }
    }).then(function(response){
        console.log(response)
    }).then(function(data) {
        if(data.activeSeasonId) {
            document.getElementById('setActive').checked = true;
        } else {
            document.getElementById('setActive').checked = false;
        }
        console.log(data);
    });

    let checkbox = document.querySelector("input[name=request-over]");
    console.log(checkbox);

/*    checkbox.addEventListener( 'change', function(checked) {
        if(this.checked) {
            console.log('checked');
        } else {
            console.log('unchecked');
        }

        let apiUrl = '/season/' + seasonId + '/submissions/' + this.checked;
        fetch(apiUrl, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
            }
        }).then(function(response) {
            console.log(response);
            if(response.ok) {
            } else {
                alert('Error');
            }
        })
    })*/
})