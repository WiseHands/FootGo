function submissionData(event) {
    const sponsorName = document.getElementById('sponsorName');
    const sponsorUrl = document.getElementById('sponsorUrl');
    const imageUrl = document.getElementById('imageUrl');
    const imageUrlDark = document.getElementById('imageUrlDark');

    if (sponsorName.value == "" && sponsorName.value.length === 0 || sponsorUrl.value == "" && sponsorUrl.value.length === 0) {
        document.getElementById('requiredFieldsError').style.display = "block";
        sponsorName.classList.add("required-fields");
        sponsorUrl.classList.add("required-fields");
        return false;
    }
    if(sponsorName.value && sponsorUrl.value) {

        let params = '?sponsorName=' + sponsorName.value + '&sponsorUrl=' + sponsorUrl.value + '&logoImageUrl=' + imageUrl.value + '&logoImageUrlDark=' + imageUrlDark.value;
        let apiUrl = '/api/season/' + seasonId + '/sponsors/new' + params;
        fetch(apiUrl, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            }
        }).then(function(response) {
            console.log(response);
            if(response.ok) {
                location.pathname = '/admin/tournament/' + tournamentId + '/season/' + seasonId + '/sponsors';
            }
            else {
                alert('Помилка при створенні спонсора');
            }
            return  response.json();
        }).then(function(data) {
            console.log(data);
        })
    }
}
