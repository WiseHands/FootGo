const path = location.toString();
let url = new URL(path);
let searchParams = new URLSearchParams(url.search);

const id = searchParams.get('uuid');

let apiUrl = '/team/' + id;
fetch(apiUrl, {
  method: 'GET',
  headers: {
    'Content-Type': 'application/json',
  }
}).then(function(response){
    console.log(response)
    return  response.json();
}).then(function(data) {
    document.querySelector('input[type=text]').value = data.teamName;
    document.querySelector('input[name="captainName"]').value = data.captain.captainName;
    document.querySelector('input[type=tel]').value = data.captain.captainPhone;
    document.querySelector('input[type=email]').value = data.captain.captainEmail;
    document.getElementById('bonus-points').value = data.additionalPoints;
    console.log(data);
});
function addNewPlayer() {
    location.href = '/admin/team/player/add?teamUuid=' + id;
}