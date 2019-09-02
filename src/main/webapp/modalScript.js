let state = {};

// Get the modal
var modal = document.getElementById("myModal");



// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

var plusHomeTeamGoal = document.getElementById("plusHomeTeamGoal");
plusHomeTeamGoal.onclick = function() {
	state.homeTeamGoal = true;
    modal.style.display = "block";
}
var plusGuestTeamGoal = document.getElementById("plusGuestTeamGoal");
plusGuestTeamGoal.onclick = function() {
	state.homeTeamGoal = true;
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

//modal2
var goalModal = document.getElementById("goalModal");

var gbtn = document.getElementById("goalBtn");

gbtn.onclick = function() {
    goalModal.style.display = "block";
}

var span2 = document.getElementsByClassName("close")[1];

span2.onclick = function() {
    goalModal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == goalModal) {
        goalModal.style.display = "none";
    }
}