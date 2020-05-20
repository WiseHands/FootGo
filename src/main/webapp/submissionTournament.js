function submissionData(event) {
    const tournamentName = document.getElementById('tournamentName').value;
    const tournamentNameEn = document.getElementById('tournamentNameEn').value;
    const tournamentDescription = document.getElementById("tournamentDescription").value;
    const tournamentDescriptionEn = document.getElementById("tournamentDescriptionEn").value;
    const checkEmptyTournamentName = document.getElementById("tournamentName");
    const checkEmptyTournamentNameEn = document.getElementById("tournamentNameEn");

    if (checkEmptyTournamentName.value == "" && checkEmptyTournamentName.value.length == 0 || checkEmptyTournamentNameEn.value == "" && checkEmptyTournamentNameEn.value.length == 0) {
        document.getElementById('requiredFieldsError').style.display = "block";
        checkEmptyTournamentName.classList.add("required-fields");
        checkEmptyTournamentNameEn.classList.add("required-fields");
        return false;
    }
    if(tournamentName && tournamentNameEn) {

        let params = '?tournamentName=' + tournamentName + '&tournamentNameEn=' + tournamentNameEn + '&tournamentDescription=' + tournamentDescription + '&tournamentDescriptionEn=' + tournamentDescriptionEn;
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
