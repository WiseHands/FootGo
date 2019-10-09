document.addEventListener('DOMContentLoaded', function() {
    let fileInput = document.querySelector('input[type=file]');
    let filenameContainer = document.querySelector('#filename');
    let dropzone = document.querySelector('#fileUpload');

    fileInput.addEventListener('change', function() {
      	filenameContainer.innerText = fileInput.value.split('\\').pop();
    });

    fileInput.addEventListener('dragenter', function() {
    	dropzone.classList.add('dragover');
    });

    fileInput.addEventListener('dragleave', function() {
      	dropzone.classList.remove('dragover');
    });

    let img = document.getElementById('image');
    if(img.getAttribute('src') == "" || null) {
        img.src = 'https://www.w3schools.com/css/img_avatar.png';
    }

    fileInput.onchange = function() {
        let fileInput = document.querySelector('#fileUpload');
        const formData = new FormData();

        formData.append('file', fileInput.files[0]);

        const options = {
            method: 'POST',
            body: formData,
        };

        fetch('/uploadFile', options)
            .then(function(response) {
                  return response.json();
        })
            .then(function(data){
                console.log(data);
                document.querySelector('input[name="playerImageUrl"]').value = data.fileViewUri;
                document.getElementById("image").src = data.fileViewUri;
        });
    }
});