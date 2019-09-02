function putPlayerById(data) {
        let name = document.getElementById('name').value;
        let phone = document.getElementById('phone').value;
        let email = document.getElementById('email').value;

    let params = '?name=' + name + '&phone=' + phone + "&email=" + email;

    let apiUrl = '/player/' + id + params;
    fetch(apiUrl, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        body: data
      }
    }).then(function(response){
        console.log(response)
        return  response.json();
    }).then(function(data) {

        console.log(data);
    })

}