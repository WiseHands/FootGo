const path = location.toString();
let url = new URL(path).pathname;
let regex = /\d+/g;
let seasonId = url.match(regex);

function submissionData(event) {
    let leagueName = document.getElementById('leagueName').value;
    if(leagueName) {
        console.log(leagueName);

        //let radios = document.querySelectorAll('input[type=checkbox]:checked');
        function getCheckedValues() {
            return Array.from(document.querySelectorAll('input[type="checkbox"]'))
            .filter((checkbox) => checkbox.checked)
            .map((checkbox) => checkbox.value);
        }
        //console.log(getCheckedValues());

/*        radios.forEach((radio) => {
            console.log(radio.getAttribute('value'));
        });*/


        let leagueDataJson = {"name" : leagueName, "teamList" : getCheckedValues()};
        console.log(leagueDataJson);

        let apiUrl = '/api/season/' + seasonId + '/league';
        fetch(apiUrl, {
            method: 'POST',
            body: leagueDataJson,
            headers: {
              'Content-Type': 'application/json',
            }
        }).then(function(response) {
            console.log(response);
            if(response.ok) {
/*                document.getElementById('cardsModal').style.display = "none";
                location.pathname = location.pathname;*/
            }
            if(response.status === 403) {
/*                document.getElementById('cardsModal').style.display = "none";
                alert('Максимальна кількість карток для гравця');*/
            }
            return  response.json();
        }).then(function(data) {
            console.log(data);
        })
    }

/*       const checkEmpty = document.getElementById("leagueName");
       if (checkEmpty.value == "" && checkEmpty.value.length == 0) {
           //document.getElementById('showInputLeagueNameError').style.display = "block";
           //document.getElementById('cardMinute').style.cssText = "border-color: #fc2c2c; border-style: solid; border-width: thin";
           return false;
       }*/

}