function putPlayerById(data) {
    let firstName = document.getElementById('firstName').value;
    let lastName = document.getElementById('lastName').value;
    let number = document.getElementById('number').value;
    let phone = document.getElementById('phone').value;
    let email = document.getElementById('email').value;
    let imageUrl = document.getElementById('imageUrl').value;

    let params = '?firstName=' + firstName
                + '&lastName=' + lastName
                + '&number=' + number
                + '&phone=' + phone
                + "&email=" + email
                + "&imageUrl=" + imageUrl;

    let apiUrl = '/player/' + id + params;
    fetch(apiUrl, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          body: data
        }
    }).then(function(response){
        if(response.ok) {
        	document.getElementById('successChange').style.display = 'block';
        } else {
        	document.getElementById('errorChange').style.display = 'block';
        }
        console.log(response)
        return  response.json();
    }).then(function(data) {

        console.log(data);
    })

}