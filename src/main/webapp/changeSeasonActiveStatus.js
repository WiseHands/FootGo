document.addEventListener('DOMContentLoaded', function() {

    let checkboxes = document.querySelectorAll("#setActive");

    let apiGetActiveSeasonUrl = '/api/tournament/get_active_season?tournamentId=' + tournamentId;
    fetch(apiGetActiveSeasonUrl, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        }
    }).then(function(response){
        console.log(response)
        return response.json();
    }).then(function(data) {
        if(data) {
            for(let i = 0 ; i < checkboxes.length ; i++){
                if(data == checkboxes[i].value && !checkboxes[i].checked){
                    checkboxes[i].checked = true;
                }
            }
        } else {
        }
        console.log(data);
    });

    //checkboxes.forEach(checkbox => checkbox.addEventListener("change", setActiveSeason(checkbox)));

    for(let i = 0 ; i < checkboxes.length ; i++){
        checkboxes[i].addEventListener("change", checkUncheck, false);
    }

    function checkUncheck(){
        for(let i = 0 ; i < checkboxes.length ; i++){
            if(this.value !== checkboxes[i].value && checkboxes[i].checked){
                checkboxes[i].checked = false;
            }
        }

        let seasonId = this.value;
        let apiSetSeasonUrl = '/api/tournament/set_active_season/?tournamentId=' + tournamentId + '&seasonId=' + seasonId;
        fetch(apiSetSeasonUrl, {
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
    }

/*    function setActiveSeason(checkbox) {
        return function() {
        //checkboxes.forEach(checkbox => if(this.value !== checkbox.value && checkbox.checked) {checkbox.checked = false});
            if(this.checked) {
                console.log('checked');
                console.log(checkbox);
            } else {
                console.log('unchecked');
            }
            let seasonId = checkbox.value;

            let apiUrl = '/api/tournament/set_active_season/?tournamentId=' + tournamentId + '&seasonId=' + seasonId;
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
        }
    };*/

})