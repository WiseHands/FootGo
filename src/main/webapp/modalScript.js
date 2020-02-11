let state = {};

// Get the modal
let modal = document.getElementById("myModal");

let switchModal = document.getElementById("switchModal");

// Get the <span> element that closes the modal
let span = document.getElementsByClassName("close")[0];

let plusHomeTeamGoal = document.getElementById("plusHomeTeamGoal");
plusHomeTeamGoal.onclick =  function() {

    switchModal.style.display = "block";
    let goalHomeTeam = document.getElementById("goals");
    goalHomeTeam.onclick = function() {
        switchModal.style.display = "none";
    	state.homeTeamGoal = true;
        modal.style.display = "block";
        let playerSelect = document.getElementById("playerSelect");
        playerSelect.innerHTML = '';
        for(index in window.gameData.firstTeam.players) {
           let player = window.gameData.firstTeam.players[index];
           let opt = document.createElement("option");
           opt.value = player.id;
           opt.innerHTML = player.firstName + ' ' + player.lastName;
           playerSelect.appendChild(opt);
        }
    }
    let cardHomeTeam = document.getElementById("cards");
    cardHomeTeam.onclick = function() {
        switchModal.style.display = "none";
    	state.homeTeamGoal = true;
        cardsModal.style.display = "block";
        let cardPlayerSelect = document.getElementById("cardPlayerSelect");
        cardPlayerSelect.innerHTML = '';
        for(index in window.gameData.firstTeam.players) {
           let player = window.gameData.firstTeam.players[index];
           let opt = document.createElement("option");
           opt.value = player.id;
           opt.innerHTML = player.firstName + ' ' + player.lastName;
           cardPlayerSelect.appendChild(opt);
        }
    }
}

let plusGuestTeamGoal = document.getElementById("plusGuestTeamGoal");
plusGuestTeamGoal.onclick =  function() {
    switchModal.style.display = "block";
    let goalGuestTeam = document.getElementById("goals");
    goalGuestTeam.onclick = function() {
        switchModal.style.display = "none";
    	state.homeTeamGoal = false;
        modal.style.display = "block";
        let playerSelect = document.getElementById("playerSelect");
        playerSelect.innerHTML = '';
        for(index in window.gameData.secondTeam.players) {
           let player = window.gameData.secondTeam.players[index];
           let opt = document.createElement("option");
           opt.value = player.id;
           opt.innerHTML = player.firstName + ' ' + player.lastName;
           playerSelect.appendChild(opt);
        }
    }
    let cardGuestTeam = document.getElementById("cards");
    cardGuestTeam.onclick = function() {
        switchModal.style.display = "none";
     	state.homeTeamGoal = false;
        cardsModal.style.display = "block";
        let cardPlayerSelect = document.getElementById("cardPlayerSelect");
        cardPlayerSelect.innerHTML = '';
        for(index in window.gameData.secondTeam.players) {
           let player = window.gameData.secondTeam.players[index];
           let opt = document.createElement("option");
           opt.value = player.id;
           opt.innerHTML = player.firstName + ' ' + player.lastName;
           cardPlayerSelect.appendChild(opt);
        }
    }
}

let span1 = document.getElementsByClassName("close")[1];

let modalEditDataTeam1 = document.querySelector("#editDataTeam1 .close");

let modalEditDataTeam2 = document.querySelector("#editDataTeam2 .close");

let modalDefeatTeam = document.querySelector("#tDefeatModal .close");

let technicalDefeat = document.getElementById("technicalDefeat");

let modalCards = document.querySelector("#cardsModal .close");

technicalDefeat.onclick = function() {
    tDefeatModal.style.display = "block";
    let teamSelect = document.getElementById("teamSelect");
        teamSelect.innerHTML = '';
        let first_team = window.gameData.firstTeam;
        let second_team = window.gameData.secondTeam;
        let opt = document.createElement("option");
        let opt2 = document.createElement("option");
        opt.value = first_team.id;
        opt2.value = second_team.id;
        opt.innerHTML = first_team.teamName;
        opt2.innerHTML = second_team.teamName;
        teamSelect.appendChild(opt);
        teamSelect.appendChild(opt2);
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}
modalEditDataTeam1.onclick = function() {
    editDataTeam1.style.display = "none";
}
modalEditDataTeam2.onclick = function() {
    editDataTeam2.style.display = "none";
}
span1.onclick = function() {
    switchModal.style.display = "none";
}
modalDefeatTeam.onclick = function() {
    tDefeatModal.style.display = "none";
}
modalCards.onclick = function() {
    cardsModal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

//modal2
let goalModal = document.getElementById("goalModal");

let span2 = document.getElementsByClassName("close");

span2.onclick = function() {
    goalModal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == goalModal) {
        goalModal.style.display = "none";
    }
}

let radioYellow = document.getElementById("yellowCard");
radioYellow.onclick = function() {
    document.getElementById("saveCardButton").disabled = false;
}
let radioRed = document.getElementById("redCard");
radioRed.onclick = function() {
    document.getElementById("saveCardButton").disabled = false;
}
function isNumber(evt) {
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
       if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57)) {
           return false;
       }
    return true;
}
const checkEmpty = document.getElementById("cardMinute");
checkEmpty.addEventListener('input', function () {
  if (checkEmpty.value &&
    checkEmpty.value.length > 0 &&
    checkEmpty.value.trim().length > 0) {
    console.log('value is:    '+checkEmpty.value);}
  else {
    console.log('No value');
  }
});
function addGoalBtnClicked(event) {
   const checkEmptyMinute = document.getElementById("goalMinute");
   const checkEmptySeconds = document.getElementById("goalSec");
   if (checkEmptyMinute.value == "" && checkEmptyMinute.value.length == 0 || checkEmptySeconds.value == "" && checkEmptySeconds.value.length == 0) {
       document.getElementById('showInputAddGoalError').style.display = "block";
       document.getElementById('goalMinute').style.cssText = "border-color: #fc2c2c; border-style: solid; border-width: thin";
       document.getElementById('goalSec').style.cssText = "border-color: #fc2c2c; border-style: solid; border-width: thin";
       return false;
   } else {
       let goalMinute = document.getElementById("goalMinute");
       let goalVideoSec = document.getElementById("goalSec");
       let playerSelect = document.getElementById("playerSelect");
       console.log('addGoalBtnClicked', playerSelect.value, goalMinute.value);
       let params = '?playerId=' + playerSelect.value + '&goalMinute=' + goalMinute.value + '&goalVideoSec=' + goalVideoSec.value + "&homeTeamGoal=" + state.homeTeamGoal;
       let apiUrl = '/api/game/' + id + '/goal/' + params;
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
   }
}
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

function saveGoalBtnClickedTeamB(event) {
   const checkEmptyMinute = document.getElementById("goalMinute2");
   const checkEmptySeconds = document.getElementById("addGoalSec2");
   if (checkEmptyMinute.value == "" && checkEmptyMinute.value.length == 0 || checkEmptySeconds.value == "" && checkEmptySeconds.value.length == 0) {
       document.getElementById('showInputEditGoalTeamTwoError').style.display = "block";
       document.getElementById('goalMinute2').style.cssText = "border-color: #fc2c2c; border-style: solid; border-width: thin";
       document.getElementById('addGoalSec2').style.cssText = "border-color: #fc2c2c; border-style: solid; border-width: thin";
       return false;
   } else {
       state.homeTeamGoal = false;
       let goalMinute = document.getElementById("goalMinute2");
       let goalVideoSec = document.getElementById("addGoalSec2");
       let playerSelect = document.getElementById("playerSelect2");
       console.log('saveGoalBtnClickedTeamB', playerSelect.value, goalMinute.value);
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
               document.getElementById('editDataTeam2').style.display = "none";
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
    let card = {};
    let radios = document.querySelectorAll('input[type=radio][name="Card"]');

    function changeHandler(event) {
       if ( this.value === 'YELLOW' ) {
         card = this.value;
         console.log(card);
       } else if ( this.value === 'RED' ) {
         card = this.value;
         console.log(card);
       }
    }

    Array.prototype.forEach.call(radios, function(radio) {
       radio.addEventListener('change', changeHandler);
    });

function addCardToPlayer(event) {
   const checkEmpty = document.getElementById("cardMinute");
   if (checkEmpty.value == "" && checkEmpty.value.length == 0) {
       document.getElementById('showInputAddCardError').style.display = "block";
       document.getElementById('cardMinute').style.cssText = "border-color: #fc2c2c; border-style: solid; border-width: thin";
       return false;
   } else {
       let cardMinute = document.getElementById("cardMinute");
       let playerSelect = document.getElementById("cardPlayerSelect");
       console.log('addCardToPlayer', playerSelect.value, yellowCard.checked, redCard.checked);
       let params = '?player=' + playerSelect.value + '&cardMinute=' + cardMinute.value + '&cardType=' + card + '&homeTeamCard=' + state.homeTeamGoal;
       let apiUrl = '/api/game/' + id + '/card/' + params;
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
}

let tdbutton = document.getElementById("tdSaveButton");

tdbutton.addEventListener( 'click', function(clicked) {
    let teamSelect = document.getElementById("teamSelect");
    console.log('addTeamTechDefeat', teamSelect.value);
    let apiUrl = '/api/game/' + id + '/technicaldefeat/' + teamSelect.value;
    fetch(apiUrl, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        }
    }).then(function(response) {
        console.log(response);
        if(response.ok) {
            document.getElementById('tDefeatModal').style.display = "none";
            document.location.reload(true);
        } else {
            document.getElementById('tDefeatModal').style.display = "none";
            alert('Error');
        }
        //return  response.json();
    }).then(function(data) {
        console.log(data);
    })
})

function clearTechnicalDefeat(gameId) {
    let apiUrl = '/api/game/' + gameId  + '/technicaldefeat';
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
function editTeam1(gameId, goalId, homeTeamGoal) {
    window.goalId = goalId;
    let playerSelect = document.getElementById("playerSelect1");
    playerSelect.innerHTML = '';
    for(index in window.gameData.firstTeam.players) {
       let player = window.gameData.firstTeam.players[index];
       let opt = document.createElement("option");
       opt.value = player.id;
       opt.innerHTML = player.firstName + ' ' + player.lastName;
       playerSelect.appendChild(opt);
    }
    for(index in window.gameData.teamAGoals) {
       let goal = window.gameData.teamAGoals[index];

       if(goalId === goal.id) {
           let goalTime = goal.time;
           let videoSeconds = goal.videoSeconds
           let playerName = goal.player.id;
           console.log('goalId', goalId + ' goalTime', goalTime + ' videoSeconds', videoSeconds + ' playerName', playerName);

           let setGoalMinute = document.getElementById("goalMinute1");
           setGoalMinute.value = goalTime;

           let setGoalSeconds = document.getElementById("addGoalSec1");
           setGoalSeconds.value = videoSeconds;

           playerSelect.value = playerName;
       }
    }
    editDataTeam1.style.display = "block";
}

function editTeam2(gameId, goalId, homeTeamGoal) {
    window.goalId = goalId;
    let playerSelect = document.getElementById("playerSelect2");
    playerSelect.innerHTML = '';
    for(index in window.gameData.secondTeam.players) {
       let player = window.gameData.secondTeam.players[index];
       let opt = document.createElement("option");
       opt.value = player.id;
       opt.innerHTML = player.firstName + ' ' + player.lastName;
       playerSelect.appendChild(opt);
    }
    for(index in window.gameData.teamBGoals) {
       let goal = window.gameData.teamBGoals[index];

       if(goalId === goal.id) {
           let goalTime = goal.time;
           let videoSeconds = goal.videoSeconds
           let playerName = goal.player.id;
           console.log('goalId', goalId + ' goalTime', goalTime + ' videoSeconds', videoSeconds+ ' playerName', playerName);

           let setGoalMinute = document.getElementById("goalMinute2");
           setGoalMinute.value = goalTime;

           let setGoalSeconds = document.getElementById("addGoalSec2");
           setGoalSeconds.value = videoSeconds;

           playerSelect.value = playerName;
       }
    }
    editDataTeam2.style.display = "block";
}

function deleteGoal(gameId, goalId, homeTeamGoal) {
    console.log('deleteGoal', goalId)
    let apiUrl = '/api/game/' + gameId + '/goal/' + goalId + '?isHomeTeamGoal=' + homeTeamGoal;
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

function deleteCard(gameId, cardId, homeTeamCard) {
    console.log('deleteCard', cardId)
    let apiUrl = '/api/game/' + gameId + '/card/' + cardId + '?isHomeTeamCard=' + homeTeamCard;
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

let checkbox = document.querySelector("input[name=match-over]");

checkbox.addEventListener( 'change', function(checked) {
    if(this.checked) {
        console.log('checked');
    } else {
        console.log('unchecked');
    }

    let apiUrl = '/api/game/' + id + '/completed/' + this.checked;
        fetch(apiUrl, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            }
        }).then(function(response) {
            console.log(response);
            if(response.ok) {
            } else {
                alert('Error');
            }
        })

});