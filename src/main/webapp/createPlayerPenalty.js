let penaltyModalClose = document.querySelector("#penaltyModal .close");

penaltyModalClose.onclick = function() {
    penaltyModal.style.display = "none";
}

let radioPlusPenaltyGoal = document.getElementById("plusPenaltyGoal");
radioPlusPenaltyGoal.onclick = function() {
    document.getElementById("savePenaltyButton").disabled = false;
}

let radioMinusPenaltyGoal = document.getElementById("minusPenaltyGoal");
radioMinusPenaltyGoal.onclick = function() {
    document.getElementById("savePenaltyButton").disabled = false;
}

let penalty = {};
let penaltyRadios = document.querySelectorAll('input[type=radio][name="Penalty"]');

function penaltyChangeHandler(event) {
   if ( this.value === 'true' ) {
     penalty = this.value;
     console.log(penalty);
   } else if ( this.value === 'false' ) {
     penalty = this.value;
     console.log(penalty);
   }
}

Array.prototype.forEach.call(penaltyRadios, function(penaltyRadio) {
   penaltyRadio.addEventListener('change', penaltyChangeHandler);
});

function addPenaltyGoalToPlayer(event) {
   let playerSelect = document.getElementById("penaltyPlayerSelect");
   console.log('addPenaltyGoalToPlayer', playerSelect.value, plusPenaltyGoal.checked, minusPenaltyGoal.checked);
   let params = '?player=' + playerSelect.value + '&penaltyGoal=' + penalty + '&homeTeamPenaltyGoal=' + state.homeTeamPenaltyGoal;
   let apiUrl = '/api/game/' + gameId + '/penalty/' + params;
   fetch(apiUrl, {
       method: 'POST',
       headers: {
         'Content-Type': 'application/json',
       }
   }).then(function(response) {
       console.log(response);
       if(response.ok) {
           document.getElementById('penaltyModal').style.display = "none";
           location.pathname = location.pathname;
       }
       if(response.status === 403) {
           document.getElementById('penaltyModal').style.display = "none";
           alert('Максимальна кількість забитих голів для гравця');
       }
       return  response.json();
   }).then(function(data) {
       console.log(data);
   })
}

function deletePenaltyGoal(gameId, penaltyId, homeTeamPenaltyGoal) {
    console.log('deletePenalty', penaltyId)
    let apiUrl = '/api/game/' + gameId + '/penalty/' + penaltyId + '?isHomeTeamPenaltyGoal=' + homeTeamPenaltyGoal;
    fetch(apiUrl, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        }
    }).then(function(response) {
        console.log(response);
        if(response.ok) {
            location.pathname = location.pathname;
        } else {
            alert('Error');
        }
        return  response.json();
    }).then(function(data) {
    })
}