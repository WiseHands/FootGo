function submissionData(event) {
    let seasonName = document.getElementById('seasonName').value;
    let seasonNameEn = document.getElementById('seasonNameEn').value;

    const checkEmptySeasonName = document.getElementById("seasonName");
    const checkEmptySeasonNameEn = document.getElementById("seasonNameEn");
    if (checkEmptySeasonName.value == "" && checkEmptySeasonName.value.length == 0 || checkEmptySeasonNameEn.value == "" && checkEmptySeasonNameEn.value.length == 0) {
        document.getElementById('requiredFieldsError').style.display = "block";
        checkEmptySeasonName.classList.add("required-fields");
        checkEmptySeasonNameEn.classList.add("required-fields");
        return false;
    }
    if(seasonName && seasonNameEn) {
        let params = '?seasonName=' + seasonName + '&seasonNameEn=' + seasonNameEn;
        let apiUrl = '/api/tournament/' + tournamentId + '/seasons/new' + params;
        fetch(apiUrl, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            }
        }).then(function(response) {
            console.log(response);
            if(response.ok) {
                location.pathname = '/admin/tournament/' + tournamentId + '/seasons';
            }
            else {
                alert('Помилка при створенні сезону');
            }
            return  response.json();
        }).then(function(data) {
            console.log(data);
        })
    }
}
