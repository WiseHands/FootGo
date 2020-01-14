function putTeamAndCaptainById(data) {
        let t_name = document.getElementById('name').value;
        let name = document.getElementById('captain_name').value;
        let phone = document.getElementById('captain_phone').value;
        let email = document.getElementById('captain_email').value;
        let ad_points = document.getElementById('bonus-points').value;
        let logoImageUrl = document.getElementById('imageUrl').value;
        let logoImageUrlDark = document.getElementById('imageUrlDark').value;

    let params = '?name=' + t_name + '&captainName=' + name + '&captainPhone=' + phone + "&captainEmail=" + email + "&additionalPoints=" + ad_points + "&logoImageUrl=" + logoImageUrl + "&logoImageUrlDark=" + logoImageUrlDark;

    let apiUrl = '/team/' + id + params;
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
        } else {
            document.getElementById('error').style.display = 'block';
        }
        return  response.json();
    }).then(function(data) {
        console.log(data);
    })

}