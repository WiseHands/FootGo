function putVideoUrlById() {
    let videourl = document.getElementById('videoLink').value;

    let params = '?videoUrl=' + videourl;
    let apiUrl = '/api/game/' + id + '/setvideourl' + params;

    fetch(apiUrl, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      }
    })
    .then(function(response){
        if(response.ok) {
        	document.getElementById('success').style.display = 'block';
        } else {
        	document.getElementById('error').style.display = 'block';
        }
        console.log(response)
    })
    .then(function(data) {
        console.log(data);
    })
}
