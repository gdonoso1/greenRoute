package proyecto.greenroute.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import proyecto.greenroute.domain.Punto;
import proyecto.greenroute.domain.Ruta;
import proyecto.greenroute.domain.Usuario;
import proyecto.greenroute.service.PoblacionService;
import proyecto.greenroute.service.RutaService;
import proyecto.greenroute.service.UsuarioService;

@RestController
@RequestMapping("/nuevaRuta")
public class RutaNuevaController {

	@Autowired
	private UsuarioService userService;

	@Autowired
	private RutaService rutaService;

	@Autowired
	private PoblacionService poblacionService;

	@GetMapping
	public ModelAndView nuevaRuta() {
		ModelAndView modelview = new ModelAndView("rutasAdd");
//		modelview.getModelMap().addAttribute("pueblos", poblacionService.listarPoblacion());
		return modelview;

	}

//	@GetMapping("/poblaciones")
//	public ModelAndView findCAutonoma(Model model) {
//		ModelAndView modelview = new ModelAndView("c_autonomas");
//		modelview.getModelMap().addAttribute("c_autonomas", poblacionService.findDistinctByPoblacion());
//
//		return modelview;
//	}

	@PostMapping
	public ModelAndView rutaCreada(@RequestParam("arrayLatitud") double arrayLatitud[],
			@RequestParam("arrayLongitud") double arrayLongitud[], @RequestParam("latitud") double latitud,
			@RequestParam("longitud") double longitud, @RequestParam("nombre") String nombre,
			@RequestParam("poblacion") String poblacion, @RequestParam("username") String username,
			@RequestParam("descripcion") String descripcion) throws ServletException, IOException {

		Usuario usuario = userService.getUsuario(username);

		ArrayList<Punto> puntos = new ArrayList<Punto>();
		Ruta ruta = new Ruta(nombre, latitud, longitud, poblacion, puntos, usuario, descripcion);

		for (int i = 0; i < arrayLatitud.length; i++) {
			Punto punto = new Punto(arrayLatitud[i], arrayLongitud[i]);
			punto.setRuta(ruta);
			puntos.add(punto);
		}

		ruta.setCreacion(new Date());
		ruta.setKm(distanciaCoord(puntos));

		rutaService.guardar(ruta);

		return new ModelAndView("redirect:/rutas");
	}

	public static int distanciaCoord(Collection<Punto> puntoKm) {
		double lat1 = 0;
		double lng1 = 0;
		double lat2 = 0;
		double lng2 = 0;
		int distancia = 0;

		Iterator<Punto> it = puntoKm.iterator();
		boolean primerValor = false;
		while (it.hasNext()) {
			Punto puntoIt = it.next();
			if (primerValor) {
				lat2 = puntoIt.getLatitud();
				lng2 = puntoIt.getLongitud();
				// double radioTierra = 3958.75;//en millas
				double radioTierra = 6371;// en kilómetros
				double dLat = Math.toRadians(lat2 - lat1);
				double dLng = Math.toRadians(lng2 - lng1);
				double sindLat = Math.sin(dLat / 2);
				double sindLng = Math.sin(dLng / 2);
				double va1 = Math.pow(sindLat, 2)
						+ Math.pow(sindLng, 2) * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
				double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
				distancia += radioTierra * va2;
//				Segundo valor pasa a estar en el primero 
				lat1 = lat2;
				lng1 = lng2;
			} else {
//				Iniciamos primer valor
				distancia = 33;
				lat1 = puntoIt.getLatitud();
				lng1 = puntoIt.getLongitud();

			}
			primerValor = true;
		}

		return distancia;
	}

}
