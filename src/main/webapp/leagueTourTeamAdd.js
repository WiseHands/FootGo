let state = {};

// Get the modal
let selectTeamModal = document.getElementById("selectTeamModal");

const addHomeTeamButtons = document.querySelectorAll("#addHomeTeam");
const addGuestTeamButtons = document.querySelectorAll("#addGuestTeam");

addHomeTeamButtons.forEach(addHomeTeamButton => addHomeTeamButton.addEventListener("click", displayAddHomeTeam(addHomeTeamButton)));
addGuestTeamButtons.forEach(addGuestTeamButton => addGuestTeamButton.addEventListener("click", displayAddGuestTeam(addGuestTeamButton)));

function displayAddHomeTeam(addHomeTeamButton) {
    return function() {
        gameId = event.target.getAttribute("gameId");
        selectTeamModal.style.display = "block";
        state.homeTeamCheck = true;
    }
};

function displayAddGuestTeam(addGuestTeamButton) {
    return function() {
        gameId = event.target.getAttribute("gameId");
        selectTeamModal.style.display = "block";
        state.homeTeamCheck = false;
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

function addTeamBtnClicked(event) {
   let teamSelect = document.getElementById("teamSelect");
   console.log('addTeamBtnClicked', teamSelect.value);
   let params = '?gameId=' + gameId + '&teamId=' + teamSelect.value + "&homeTeamCheck=" + state.homeTeamCheck;
   let apiUrl = '/api/game/setLeagueTourTeam/' + params;
   fetch(apiUrl, {
       method: 'POST',
       headers: {
         'Content-Type': 'application/json',
       }
   }).then(function(response) {
       console.log(response);
       if(response.ok) {
           document.getElementById('selectTeamModal').style.display = "none";
           location.pathname = location.pathname;
       } else {
           document.getElementById('selectTeamModal').style.display = "none";
           alert('Помилка при збереженні команди');
       }
       return  response.json();
   }).then(function(data) {
       console.log(data);
   })
}
