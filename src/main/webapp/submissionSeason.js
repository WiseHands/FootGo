function submissionData(event) {
    let seasonName = document.getElementById('seasonName').value;

    const checkEmptySeasonName = document.getElementById("seasonName");
    if (checkEmptySeasonName.value == "" && checkEmptySeasonName.value.length == 0) {
        document.getElementById('requiredFieldsError').style.display = "block";
        checkEmptySeasonName.classList.add("required-fields");
        return false;
    }
    if(seasonName) {

        let params = '?seasonName=' + seasonName;
        let apiUrl = '/api/seasons/new' + params;
        fetch(apiUrl, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            }
        }).then(function(response) {
            console.log(response);
            if(response.ok) {
                location.pathname = '/admin';
            }
            else {
                alert('Помилка при створенні езону');
            }
            return  response.json();
        }).then(function(data) {
            console.log(data);
        })
    }
}
