function putDescTextById() {
    let desctext = document.getElementById('descriptionText').value;

    let params = '?descText=' + desctext;
    let apiUrl = '/api/game/' + id + '/setdesctext' + params;

    fetch(apiUrl, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      }
    })
    .then(function(response){
        if(response.ok) {
        	document.getElementById('textSuccess').style.display = 'block';
        } else {
        	document.getElementById('textError').style.display = 'block';
        }
        console.log(response)
    })
    .then(function(data) {
    })
}
