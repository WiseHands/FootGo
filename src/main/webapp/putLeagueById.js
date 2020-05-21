document.addEventListener('DOMContentLoaded', function() {

    const leagueEditModal = document.getElementById("leagueEditModal");
    const leagueEdit = document.getElementById("leagueEdit");

    leagueEdit.onclick =  function() {
        leagueEditModal.style.display = "block";
    }

    const closeButton = document.getElementById("closeButton");

    // When the user clicks on <span> (x), close the modal
    closeButton.onclick = function() {
    leagueEditModal.style.display = "none";
    }
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == leagueEditModal) {
            leagueEditModal.style.display = "none";
        }
    }

});

function submissionData(event) {
    const leagueName = document.getElementById('leagueName').value;
    const leagueNameEn = document.getElementById('leagueNameEn').value;
    const checkEmptyLeagueName = document.getElementById("leagueName");
    const checkEmptyLeagueNameEn = document.getElementById("leagueNameEn");

    if (checkEmptyLeagueName.value == "" && checkEmptyLeagueName.value.length == 0 || checkEmptyLeagueNameEn.value == "" && checkEmptyLeagueNameEn.value.length == 0) {
        document.getElementById('requiredFieldsError').style.display = "block";
        checkEmptyLeagueName.classList.add("required-fields");
        checkEmptyLeagueNameEn.classList.add("required-fields");
        return false;
    }
    if(leagueName && leagueNameEn) {

        let params = '?&leagueName=' + leagueName + '&leagueNameEn=' + leagueNameEn;
        let apiUrl = '/leaguelist/' + leagueId + '/edit' + params;
        fetch(apiUrl, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
            }
        }).then(function(response) {
            console.log(response);
            if(response.ok) {
                location.pathname = location.pathname;
            }
            else {
                alert('Помилка при збереженні сезону');
            }
            return  response.json();
        }).then(function(data) {
            console.log(data);
        })
    }
}
