const path = location.toString();
let url = new URL(path);
let searchParams = new URLSearchParams(url.search);

const id = searchParams.get('uuid');

function putSponsorDataById(data) {
    const sponsorName = document.getElementById('name').value;
    const sponsorUrl = document.getElementById('url').value;
    const logoImageUrl = document.getElementById('imageUrl').value;
    const logoImageUrlDark = document.getElementById('imageUrlDark').value;

    if (sponsorName == "" && sponsorName.length === 0) {
        document.getElementById('requiredFieldsError').style.display = "block";
        document.getElementById('name').classList.add("required-fields");
    }

    if (sponsorUrl == "" && sponsorUrl.length === 0) {
        document.getElementById('requiredFieldsError').style.display = "block";
        document.getElementById('url').classList.add("required-fields");
    }

    if (sponsorName == "" && sponsorName.length === 0 || sponsorUrl == "" && sponsorUrl.length === 0) {
        return false;
    }

    let params = '?sponsorName=' + sponsorName + '&sponsorUrl=' + sponsorUrl + '&logoImageUrl=' + logoImageUrl + '&logoImageUrlDark=' + logoImageUrlDark;

    let apiUrl = '/sponsor/' + id + params;
    fetch(apiUrl, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        body: data
      }
    }).then(function(response){
        console.log(response);
        if(response.ok) {
        	document.getElementById('success').style.display = 'block';
        	let url = location.href.split("?")[0];
        	window.history.pushState('object', document.title, url);
        	location.pathname =  '/admin/tournament/' + tournamentId + '/season/' + seasonId + '/sponsors';
        } else {
            document.getElementById('error').style.display = 'block';
        }
        return  response.json();
    }).then(function(data) {
        console.log(data);
    })
}

document.addEventListener('DOMContentLoaded', function() {
    let checkbox = document.querySelector("input[name=request-over]");

    checkbox.addEventListener( 'change', function(checked) {
        if(this.checked) {
            console.log('checked');
        } else {
            console.log('unchecked');
        }

        let apiUrl = '/sponsor/' + id + '/setActive' + '?isActive=' + this.checked;
        fetch(apiUrl, {
            method: 'PUT',
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
})