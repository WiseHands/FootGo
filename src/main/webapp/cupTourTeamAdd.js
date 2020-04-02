let state = {};

// Get the modal
let selectTeamModal = document.getElementById("selectTeamModal");

const addHomeTeamButtons = document.querySelectorAll("#addHomeTeam");
const addGuestTeamButtons = document.querySelectorAll("#addGuestTeam");

addHomeTeamButtons.forEach(addHomeTeamButton => addHomeTeamButton.addEventListener("click", displayAddHomeTeam(addHomeTeamButton)));
addGuestTeamButtons.forEach(addGuestTeamButton => addGuestTeamButton.addEventListener("click", displayAddGuestTeam(addGuestTeamButton)));

function displayAddHomeTeam(addHomeTeamButton) {
    return function() {
        selectTeamModal.style.display = "block";
    }
};

function displayAddGuestTeam(addGuestTeamButton) {
    return function() {
        selectTeamModal.style.display = "block";
    }
};

let closeButton = document.getElementById("close-button");

// When the user clicks on <span> (x), close the modal
closeButton.onclick = function() {
    selectTeamModal.style.display = "none";
}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == selectTeamModal) {
        selectTeamModal.style.display = "none";
    }
}

function addGoalBtnClicked(event) {
   const checkEmptyMinute = document.getElementById("goalMinute");
   const checkEmptySeconds = document.getElementById("goalSec");
       let goalMinute = document.getElementById("goalMinute");
       let goalVideoSec = document.getElementById("goalSec");
       let playerSelect = document.getElementById("playerSelect");
       console.log('addGoalBtnClicked', playerSelect.value, goalMinute.value);
       let params = '?playerId=' + playerSelect.value + '&goalMinute=' + goalMinute.value + '&goalVideoSec=' + goalVideoSec.value + "&homeTeamGoal=" + state.homeTeamGoal;
       let apiUrl = '/api/game/' + gameId + '/goal/' + params;
       fetch(apiUrl, {
           method: 'POST',
           headers: {
             'Content-Type': 'application/json',
           }
       }).then(function(response) {
           console.log(response);
           if(response.ok) {
               document.getElementById('myModal').style.display = "none";
               location.pathname = location.pathname;
           } else {
               document.getElementById('myModal').style.display = "none";
               alert('Error');
           }
           return  response.json();
       }).then(function(data) {
           console.log(data);
       })

function saveGoalBtnClickedTeamA(event) {
   const checkEmptyMinute = document.getElementById("goalMinute1");
   const checkEmptySeconds = document.getElementById("addGoalSec1");
   if (checkEmptyMinute.value == "" && checkEmptyMinute.value.length == 0 || checkEmptySeconds.value == "" && checkEmptySeconds.value.length == 0) {
       document.getElementById('showInputEditGoalTeamOneError').style.display = "block";
       document.getElementById('goalMinute1').style.cssText = "border-color: #fc2c2c; border-style: solid; border-width: thin";
       document.getElementById('addGoalSec1').style.cssText = "border-color: #fc2c2c; border-style: solid; border-width: thin";
       return false;
   } else {
       state.homeTeamGoal = true;
       let goalMinute = document.getElementById("goalMinute1");
       let goalVideoSec = document.getElementById("addGoalSec1");
       let playerSelect = document.getElementById("playerSelect1");
       console.log('saveGoalBtnClickedTeamA', playerSelect.value, goalMinute.value);
       let params = '?playerId=' + playerSelect.value + '&goalMinute=' + goalMinute.value + '&goalVideoSec=' + goalVideoSec.value + "&homeTeamGoal=" + state.homeTeamGoal;
       let apiUrl = '/api/goal/' + window.goalId + params;
       fetch(apiUrl, {
           method: 'PUT',
           headers: {
             'Content-Type': 'application/json',
            }
       }).then(function(response) {
           console.log(response);
           if(response.ok) {
               document.getElementById('editDataTeam1').style.display = "none";
               location.pathname = location.pathname;
           } else {
               alert('Error');
           }
           return  response.json();
       }).then(function(data) {
           console.log(data);
       })
   }
}