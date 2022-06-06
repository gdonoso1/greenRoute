var route = [];

// Crea el mapa
const map = L.map('map').setView([40.17729735860276, -4.525442495942117], 6);
console.log(map)
mbAttr = 'Tiles &copy; Esri &mdash; Source: Esri, i-cubed, USDA, USGS, AEX, GeoEye, Getmapping, Aerogrid, IGN, IGP, UPR-EGP, and the GIS User Community';
mbUrl = 'https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}';

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
function onMapClick(e) {

	console.log(document.getElementById("arrayLatitud").value)
	console.log(document.getElementById("arrayLongitud").value)
	console.log(document.getElementById("latitud").value)
	latitudPuntos.push(e.latlng.lat);
	longitudPuntos.push(e.latlng.lng);

	punto = L.marker([e.latlng['lat'], e.latlng['lng']], {
		draggable: true
	}).addTo(map);

	// Recogemos coordenadas y las guardamos en un array
	var coordenadas = [e.latlng['lat'], e.latlng['lng']];
	route.push(coordenadas);

	for (var i = 0, latlngs = [], len = route.length; i < len; i++) {
		latlngs.push(new L.LatLng(route[i][0], route[i][1]));
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

	punto.addEventListener('click', quitarMarker);
}

function quitarMarker() {
	map.removeLayer(this);
}

function calulaMapa() {
	var latitudSuma = 0;
	var longitudSuma = 0;
	for (var i = 0; i < latitudPuntos.length; i++) {
		latitudSuma = parseFloat(latitudSuma) + parseFloat(latitudPuntos[i]);
		longitudSuma = parseFloat(longitudSuma) + parseFloat(longitudPuntos[i]);
	}

	latitudRuta = parseFloat(latitudSuma) / latitudPuntos.length;
	longitudRuta = longitudSuma / longitudPuntos.length;
}

/*function crearRuta() {
	calulaMapa();
	var ruta = {
		nombre: document.getElementById("nombre").value,
		latitud: latitudRuta,
		longitud: longitudRuta,
		poblacion: document.getElementById("poblacion").value,
		arrayLatitud: latitudPuntos,
		arrayLongitud: longitudPuntos,
		username: $("#usersMap")[0].innerHTML
	};

	return ruta;
}*/

function enviar() {
	calulaMapa();
	document.getElementById("latitud").value = latitudRuta;
	document.getElementById("longitud").value = longitudRuta;
	document.getElementById("arrayLatitud").value = latitudPuntos;
	document.getElementById("arrayLongitud").value = longitudPuntos;
}

/*$("#buttonSaveMapa").click(function (event) {
 
 
 
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



map.on('click', onMapClick);