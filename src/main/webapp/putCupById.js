document.addEventListener('DOMContentLoaded', function() {

    const cupEditModal = document.getElementById("cupEditModal");
    const cupEdit = document.getElementById("cupEdit");

    cupEdit.onclick =  function() {
        cupEditModal.style.display = "block";
    }

    const closeButton = document.getElementById("closeButton");

    // When the user clicks on <span> (x), close the modal
    closeButton.onclick = function() {
    cupEditModal.style.display = "none";
    }
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == cupEditModal) {
            cupEditModal.style.display = "none";
        }
    }

});

function submissionData(event) {
    const cupName = document.getElementById('cupName').value;
    const cupNameEn = document.getElementById('cupNameEn').value;
    const checkEmptyCupName = document.getElementById("cupName");
    const checkEmptyCupNameEn = document.getElementById("cupNameEn");

    if (checkEmptyCupName.value == "" && checkEmptyCupName.value.length == 0 || checkEmptyCupNameEn.value == "" && checkEmptyCupNameEn.value.length == 0) {
        document.getElementById('requiredFieldsError').style.display = "block";
        checkEmptyCupName.classList.add("required-fields");
        checkEmptyCupNameEn.classList.add("required-fields");
        return false;
    }
    if(cupName && cupNameEn) {

        let params = '?&cupName=' + cupName + '&cupNameEn=' + cupNameEn;
        let apiUrl = '/cuplist/' + cupId + '/edit' + params;
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
