function submissionData(event) {
    let sponsorName = document.getElementById('sponsorName');
    let sponsorUrl = document.getElementById('sponsorUrl');
    let imageUrl = document.getElementById('imageUrl');
    let imageUrlDark = document.getElementById('imageUrlDark');
    let descriptionText = editor.getData();

    if (!descriptionText) {
        descriptionText = " ";
    }

/*    sponsorName.addEventListener("change", (event) => {
        const result = document.querySelector('#requiredFieldsError');
        result.textContent = `${event.target.value}`;
    });*/

    if (sponsorName.value == "" && sponsorName.value.length === 0) {
        document.getElementById('requiredFieldsError').style.display = "block";
        sponsorName.classList.add("required-fields");
    }

    if (sponsorUrl.value == "" && sponsorUrl.value.length === 0) {
        document.getElementById('requiredFieldsError').style.display = "block";
        sponsorUrl.classList.add("required-fields");
    }

    let urlInput = document.getElementById("sponsorUrl");
    if (!urlInput.checkValidity()) {
        urlInput.classList.add("required-fields");
        document.getElementById('requiredFieldsURLError').style.display = "block";
        return false;
    } else {
        document.getElementById("urlValidationMessage").innerHTML = urlInput.validationMessage;
    }

    if (sponsorName.value == "" && sponsorName.value.length === 0 || sponsorUrl.value == "" && sponsorUrl.value.length === 0) {
        return false;
    }

    if(sponsorName.value && sponsorUrl.value) {

        let params = '?sponsorName=' + sponsorName.value + '&sponsorUrl=' + sponsorUrl.value + '&logoImageUrl=' + imageUrl.value + '&logoImageUrlDark=' + imageUrlDark.value;
        let apiUrl = '/api/season/' + seasonId + '/sponsors/new' + params;
        fetch(apiUrl, {
            method: 'POST',
            body: descriptionText,
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
