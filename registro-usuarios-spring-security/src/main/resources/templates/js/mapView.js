var nombre = $("#nombre").innerHTML;
var poblacion = $("#poblacion").innerHTML;
var latitud = $("#latitud")[0].innerHTML;
var longitud = $("#longitud")[0].innerHTML;
var puntos = $("#puntos")[0].innerHTML;
var punto = puntos.split("]");
var punto2 = [];
var puntoFinal = [];
var latitudLongitud = [];

for (var i = 0; i < punto.length - 1; i++) {
	punto2[i] = punto[i].replace("[", "");
	latitudLongitud = punto2[i].split(",");
	latitudLongitud[0] = parseFloat(latitudLongitud[0]);
	latitudLongitud[1] = parseFloat(latitudLongitud[1]);
	puntoFinal[i] = latitudLongitud;
}


var route = puntoFinal;

// Crea el mapa
const map = L.map('map').setView([latitud, longitud], 6);

L
	.tileLayer('http://{s}.google.com/vt/lyrs=s&x={x}&y={y}&z={z}', {
		maxZoom: 20,
		subdomains: ['mt0', 'mt1', 'mt2', 'mt3']
	}).addTo(map);

L.control.scale().addTo(map);

// Datos JSON
var latitudPuntos = [];
var longitudPuntos = [];
var latitudRuta = 0;
var longitudRuta = 0;

var punto;

// Crea y elimina makers




// Recogemos coordenadas y las guardamos en un array
for (var i = 0, latlngs = [], len = puntoFinal.length; i < len; i++) {
	latlngs.push(new L.LatLng(puntoFinal[i][0], puntoFinal[i][1]));
}

var path = L.polyline(latlngs);

map.addLayer(L.marker(latlngs[0]));
map.addLayer(L.marker(latlngs[len - 1]));

map.addLayer(path);

path.bindPopup("Hello world");

function snake() {
	path.snakeIn();
}

path.on('snakestart snake snakeend', function(ev) {
	console.log(ev.type);
});

/*function calulaMapa(latitud, longitud) {
 var latitudM = 0;
 var longitudM = 0;
 for (var i = 0; i < latitud.length; i++) {
 latitudM = parseFloat(latitudMapa) + parseFloat(latitud[i]);
 longitudM = parseFloat(longitudMapa) + parseFloat(longitud[i]);
 }
 latitudMapa = latitudM / latitud.length;
 longitudMapa = longitudM / latitud.length;
 }
 
 function crearRuta() {
 calulaMapa(latitud, longitud);
 var ruta = {
 nombre: document.getElementById("nombre").value,
 latitud: latitudMapa,
 longitud: longitudMapa,
 poblacion: document.getElementById("poblacion").value,
 arrayLatitud: latitud,
 arrayLongitud: longitud,
 username: $("#usersMap")[0].innerHTML
 };
 
 return ruta;
 }
 
 function enviar() {
 calulaMapa(latitud, longitud);
 document.getElementById("latitud").value = latitudMapa;
 document.getElementById("longitud").value = longitudMapa;
 document.getElementById("arrayLatitud").value = latitud;
 document.getElementById("arrayLongitud").value = longitud;
 document.getElementById("username").value = $("#usersMap")[0].innerHTML;
 }
 
 $("#buttonSaveMapa").click(function (event) {
 
 
 
 event.preventDefault();
 $.ajax({
 async: true,
 type: "POST",
 dataType: "json",
 contentType: 'application/json',
 url: "http://localhost:8080/nuevaRuta",
 data: JSON.stringify(crearRuta()),
 success: resposta,
 error: problemes
 });
 function resposta(dades) {
 alert("Todo corecto");
 }
 
 function problemes(dades) {
 console.log("---error---")
 console.log(dades)
 alert("Error al registrar l'usuari");
 }
 });*/