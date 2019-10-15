document.addEventListener('DOMContentLoaded', function() {
/*    const path = location.toString();
    let url = new URL(path);
    let searchParams = new URLSearchParams(url.search);

    const id = searchParams.get('uuid');
*/
    let apiUrl = '/league/submissions';
    fetch(apiUrl, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        }
    }).then(function(response){
        console.log(response)
        return response.json();
    }).then(function(data) {
        document.getElementById('request-over').checked = data.isSubmissionOpened;
        console.log(data);
    });

    let checkbox = document.querySelector("input[name=request-over]");
    console.log(checkbox);

    checkbox.addEventListener( 'change', function(checked) {
        if(this.checked) {
            console.log('checked');
        } else {
            console.log('unchecked');
        }

        let apiUrl = '/league/submissions/' + this.checked;
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
    })
})