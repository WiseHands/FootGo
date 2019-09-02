const path = location.toString();
let url = new URL(path);
let searchParams = new URLSearchParams(url.search);

const id = searchParams.get('uuid');

let apiUrl = '/player/' + id;
fetch(apiUrl, {
  method: 'GET',
  headers: {
    'Content-Type': 'application/json',
  }
}).then(function(response){
    console.log(response)
    return  response.json();
}).then(function(data) {
    document.querySelector('input[name="playerName"]').value = data.playerName;
    document.querySelector('input[name="playerPhone"]').value = data.phone;
    document.querySelector('input[name="playerEmail"]').value = data.email;
    console.log(data);
});