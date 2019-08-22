const path = location.toString();
let url = new URL(path);
let searchParams = new URLSearchParams(url.search);

const id = searchParams.get('uuid');

let apiUrl = '/submission/' + id;
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
    document.querySelector('input[name="captainName"]').value = data.captainName;
    document.querySelector('input[type=tel]').value = data.captainPhone;
    document.querySelector('input[type=email]').value = data.captainEmail;
    console.log(data);
});