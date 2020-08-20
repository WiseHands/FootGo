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
   let playerSelect = document.getElementById("cardPlayerSelect");
   console.log('addCardToPlayer', playerSelect.value, yellowCard.checked, redCard.checked);
   let params = '?player=' + playerSelect.value + '&cardMinute=' + cardMinute.value + '&cardType=' + card + '&homeTeamCard=' + state.homeTeamGoal;
   let apiUrl = '/api/game/' + gameId + '/card/' + params;
   fetch(apiUrl, {
       method: 'POST',
       headers: {
         'Content-Type': 'application/json',
       }
   }).then(function(response) {
       console.log(response);
       if(response.ok) {
           document.getElementById('cardsModal').style.display = "none";
           location.pathname = location.pathname;
       }
       if(response.status === 403) {
           document.getElementById('cardsModal').style.display = "none";
           alert('Максимальна кількість карток для гравця');
       }
       return  response.json();
   }).then(function(data) {
       console.log(data);
   })
}