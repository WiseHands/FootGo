/*const path = location.toString();
let url = new URL(path);

let searchParams = new URLSearchParams(url.search);

const id = searchParams.get('uuid');*/

function submissionData(event) {
    let leagueName = document.getElementById('leagueName').value;
    if(leagueName) {
        console.log(leagueName);
     }

    let radios = document.querySelectorAll('input[type=checkbox]:checked');
    radios.forEach((radio) => {
        console.log(radio.getAttribute('value'));
    });
}