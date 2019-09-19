function deleteEntryById(data) {
    let apiUrl = '/submission/' + id;
    fetch(apiUrl, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        body: data
      }
    }).then(function(response){
        console.log(response);
        if(response.ok) {
            document.getElementById('hideifsuccess').style.display = 'none';
        	document.getElementById('success').style.display = 'block';
        	document.getElementById('success-button').style.display = 'block';
        } else {
            document.getElementById('hideifsuccess').style.display = 'none';
        	document.getElementById('error').style.display = 'block';
        	document.getElementById('success-button').style.display = 'block';
        }
    }).then(function(data) {
        console.log(data);
    }).catch(function(error) {
          console.log('Request failed', error);})
}