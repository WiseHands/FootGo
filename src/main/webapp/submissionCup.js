const path = location.toString();
let url = new URL(path).pathname;
let regex = /\d+/g;
let seasonId = url.match(regex);

function submissionData(event) {
    let cupName = document.getElementById('cupName').value;

    const checkEmpty = document.getElementById("cupName");
    if (checkEmpty.value == "" && checkEmpty.value.length == 0) {
        document.getElementById('showInputAddCupNameError').style.display = "block";
        checkEmpty.classList.add("required-fields");
        return false;
    }
    if (cupName) {
        function getCheckedValues() {
            return Array.from(document.querySelectorAll('input[type="checkbox"]'))
            .filter((checkbox) => checkbox.checked)
            .map((checkbox) => checkbox.value);
        }

        let cupDataJsonObj = {"name" : cupName, "teamList" : getCheckedValues()};
        let jsonCupInString = JSON.stringify(cupDataJsonObj);

        let apiUrl = '/api/season/' + seasonId + '/cup';
        fetch(apiUrl, {
            method: 'POST',
            body: jsonCupInString,
            headers: {
              'Content-Type': 'application/json',
            }
        }).then(function(response) {
            console.log(response);
            if(response.ok) {
                location.pathname = '/admin/season/' + seasonId + '/cuplist';
            }
            else {
                alert('Помилка при створенні кубка');
            }
            return  response.json();
        }).then(function(data) {
            console.log(data);
        })
    }
}
