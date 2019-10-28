var map = L.map('map', {
   dragging: !L.Browser.mobile,
   touchZoom: true
}).setView([49.823774, 23.953927], 13);
L.tileLayer('https://api.maptiler.com/maps/darkmatter/{z}/{x}/{y}.png?key=gazyc6Cl99WzG1oKAzUT', {
     attribution: '<a href="https://www.maptiler.com/copyright/" target="_blank">&copy; MapTiler</a> <a href="https://www.openstreetmap.org/copyright" target="_blank">&copy; OpenStreetMap contributors</a>',
     maxZoom: 18
}).addTo(map);
var marker = L.marker([49.82307, 23.95303]).addTo(map);
var map_link = "https://www.google.com/maps/place/ФК+«Львів»/@49.8239124,23.9559869,15z/data=!4m5!3m4!1s0x0:0x5be70be9b04dfcfe!8m2!3d49.8232753!4d23.9529397?hl=uk";
marker.bindPopup("<b> ФК «Львів» </b><br> <a target='_blank' href="+ map_link +">Переглянути збільшену карту</a>").openPopup();