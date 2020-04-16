function submissionData(event) {
    const tournamentName = document.getElementById('tournamentName').value;
    const tournamentDescription = document.getElementById("tournamentDescription").value;
    const checkEmptyTournamentName = document.getElementById("tournamentName");

    if (checkEmptyTournamentName.value == "" && checkEmptyTournamentName.value.length == 0) {
        document.getElementById('requiredFieldsError').style.display = "block";
        checkEmptyTournamentName.classList.add("required-fields");
        return false;
    }
    if(tournamentName) {

        let params = '?tournamentName=' + tournamentName + '&tournamentDescription=' + tournamentDescription;
        let apiUrl = '/api/tournament/new' + params;
        fetch(apiUrl, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            }
        }).then(function(response) {
            console.log(response);
            if(response.ok) {
                location.pathname = '/admin';
            }
            else {
                alert('Помилка при створенні турніру');
            }
            return  response.json();
        }).then(function(data) {
            console.log(data);
        })
    }
}