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
});