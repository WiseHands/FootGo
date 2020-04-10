document.addEventListener('DOMContentLoaded', function() {

    const tournamentEditModal = document.getElementById("tournamentEditModal");
    const descriptionTextEdit = document.getElementById("descriptionTextEdit");

    descriptionTextEdit.onclick =  function() {
        tournamentEditModal.style.display = "block";
    }

    const closeButton = document.getElementById("closeButton");

    // When the user clicks on <span> (x), close the modal
    closeButton.onclick = function() {
    tournamentEditModal.style.display = "none";
    }
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == tournamentEditModal) {
            tournamentEditModal.style.display = "none";
        }
    }

});

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

        let params = '?&tournamentName=' + tournamentName + '&tournamentDescription=' + tournamentDescription;
        let apiUrl = '/tournament/' + tournamentId + '/edit' + params;
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
                alert('Помилка при збереженні турніру');
            }
            return  response.json();
        }).then(function(data) {
            console.log(data);
        })
    }
}
