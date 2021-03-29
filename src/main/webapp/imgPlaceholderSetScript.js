document.addEventListener('DOMContentLoaded', function() {
    let logo = document.getElementsByClassName('light');
    for (var i=0; i < logo.length; i++) {
        if(logo[i].hasAttribute("src") == false || logo[i].getAttribute('src') == "" || null) {
            logo[i].setAttribute('src', '/img/placeholder.png');
        }
    }
    let logoDark = document.getElementsByClassName('dark');
    for (var i=0; i < logoDark.length; i++) {
        if(logoDark[i].hasAttribute("src") == false || logoDark[i].getAttribute('src') == "" || null) {
            logoDark[i].setAttribute('src', '/img/placeholder.png');
        }
    }
    let photo = document.querySelectorAll('#playerPhoto');
    for (var i=0; i < photo.length; i++) {
        if(photo[i].hasAttribute("src") == false || photo[i].getAttribute('src') == "" || null) {
            photo[i].setAttribute('src', '/img/placeholder.png');
        }
    }
    let cardPhoto = document.querySelectorAll('#cardPlayerPhoto');
    for (var i=0; i < cardPhoto.length; i++) {
        if(cardPhoto[i].hasAttribute("src") == false || cardPhoto[i].getAttribute('src') == "" || null) {
            cardPhoto[i].setAttribute('src', '/img/placeholder.png');
        }
    }
});