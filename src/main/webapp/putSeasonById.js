document.addEventListener('DOMContentLoaded', function() {

    const seasonEditModal = document.getElementById("seasonEditModal");
    const seasonEdit = document.getElementById("seasonEdit");

    seasonEdit.onclick =  function() {
        seasonEditModal.style.display = "block";
    }

    const closeButton = document.getElementById("closeButton");

    // When the user clicks on <span> (x), close the modal
    closeButton.onclick = function() {
    seasonEditModal.style.display = "none";
    }
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == seasonEditModal) {
            seasonEditModal.style.display = "none";
        }
    }

});

function submissionData(event) {
    const seasonName = document.getElementById('seasonName').value;
    const seasonNameEn = document.getElementById('seasonNameEn').value;
    const checkEmptySeasonName = document.getElementById("seasonName");
    const checkEmptySeasonNameEn = document.getElementById("seasonNameEn");

    if (checkEmptySeasonName.value == "" && checkEmptySeasonName.value.length == 0 || checkEmptySeasonNameEn.value == "" && checkEmptySeasontNameEn.value.length == 0) {
        document.getElementById('requiredFieldsError').style.display = "block";
        checkEmptySeasonName.classList.add("required-fields");
        checkEmptySeasonNameEn.classList.add("required-fields");
        return false;
    }
    if(seasonName && seasonNameEn) {

        let params = '?&seasonName=' + seasonName + '&seasonNameEn=' + seasonNameEn;
        let apiUrl = '/season/' + seasonId + '/edit' + params;
        fetch(apiUrl, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
            }
        }).then(function(response) {
            console.log(response);
            if(response.ok) {
                location.pathname = location.pathname;
            }
            else {
                alert('Помилка при збереженні сезону');
            }
            return  response.json();
        }).then(function(data) {
            console.log(data);
        })
    }
}
