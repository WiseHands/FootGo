const path = location.toString();
let url = new URL(path);
let searchParams = new URLSearchParams(url.search);

const id = searchParams.get('uuid');

function putSponsorDataById(data) {
        let sponsorName = document.getElementById('name').value;
        let sponsorUrl = document.getElementById('url').value;
        let logoImageUrl = document.getElementById('imageUrl').value;
        let logoImageUrlDark = document.getElementById('imageUrlDark').value;

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
        	location.pathname = location.pathname;
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