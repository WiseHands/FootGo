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
    document.querySelector('input[name="playerFirstName"]').value = data.firstName;
    document.querySelector('input[name="playerLastName"]').value = data.lastName;
    document.querySelector('input[name="playerNumber"]').value = data.number;
    document.querySelector('input[name="playerPhone"]').value = data.phone;
    document.querySelector('input[name="playerEmail"]').value = data.email;
    document.querySelector('input[name="playerImageUrl"]').value = data.imageUrl;
    document.getElementById("image").src = data.imageUrl;

    let img = document.getElementById('image');
    if(img.getAttribute('src') == "null") {
        img.src = '/img/img_avatar.png';
    }
    
    console.log(data);
});
function returnToTeamsPage() {
    location.href = '/admin/team';
}