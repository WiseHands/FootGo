function putDescTextById() {
    let desctext = document.getElementById('descriptionText').value;
    let desctext_res = encodeURI(desctext);

    let params = '?descText=' + desctext_res;
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
