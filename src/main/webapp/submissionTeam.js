/*const path = location.toString();
let url = new URL(path).pathname;
let regex = /\d+/g;
let seasonId = url.match(regex);*/

function submissionData(event) {
    let teamName = document.getElementById('teamName').value;
    let captainName = document.getElementById('captainName').value;

    const checkEmptyTeamName = document.getElementById("teamName");
    const checkEmptyCaptainName = document.getElementById("captainName");
    if (checkEmptyTeamName.value == "" && checkEmptyTeamName.value.length == 0 && checkEmptyCaptainName.value == "" && checkEmptyCaptainName.value.length == 0) {
        document.getElementById('requiredFieldsError').style.display = "block";
        checkEmptyTeamName.classList.add("required-fields");
        checkEmptyCaptainName.classList.add("required-fields");
        return false;
    }
    if(teamName && captainName) {

        let params = '?teamName=' + teamName + '&captainName=' + captainName;
        let apiUrl = '/api/season/' + seasonId + '/team/new' + params;
        fetch(apiUrl, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            }
        }).then(function(response) {
            console.log(response);
            if(response.ok) {
                location.pathname = '/admin/tournament/' + tournamentId + '/season/' + seasonId + '/team';
            }
            else {
                alert('Помилка при створенні команди');
            }
            return  response.json();
        }).then(function(data) {
            console.log(data);
        })
    }
}
