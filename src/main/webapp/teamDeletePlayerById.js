function deletePlayerById(data) {
    let apiUrl = '/player/' + id;
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
        	document.getElementById('successDel').style.display = 'block';
        	document.getElementById('success-button').style.display = 'block';
        } else {
            document.getElementById('hideifsuccess').style.display = 'none';
        	document.getElementById('errorDel').style.display = 'block';
        	document.getElementById('success-button').style.display = 'block';
        }
    }).then(function(data) {
        console.log(data);
    }).catch(function(error) {
          console.log('Request failed', error);})
}