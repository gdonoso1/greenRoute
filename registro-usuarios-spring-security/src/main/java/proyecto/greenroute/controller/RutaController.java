package proyecto.greenroute.controller;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import proyecto.greenroute.domain.Comentario;
import proyecto.greenroute.domain.Punto;
import proyecto.greenroute.domain.Ruta;
import proyecto.greenroute.domain.Usuario;
import proyecto.greenroute.service.ComentarioService;
import proyecto.greenroute.service.RutaService;
import proyecto.greenroute.service.UsuarioService;

@RestController
@RequestMapping("/rutas")
public class RutaController {

	@Autowired
	private RutaService rutaService;

	@Autowired
	private UsuarioService userService;

	@Autowired
	private ComentarioService comentarioService;

	@GetMapping
	public Model getAllRutas(Model modelo) {
		modelo.addAttribute("rutas", rutaService.listarRutas());
		return modelo;
	}

	@PostMapping
	public Model getAllRutasNombre(@RequestParam("search") String search, @RequestParam("valor") String valor,
			Model modelo) {
		if (valor.equals("1")) {
			modelo.addAttribute("rutas", rutaService.listarRutas());
		} else if (valor.equals("2")) {
			modelo.addAttribute("rutas", rutaService.findByNombreContaining(search));
		} else if (valor.equals("3")) {
			modelo.addAttribute("rutas", rutaService.findByPoblacionContaining(search));
		}

		return modelo;
	}

	@GetMapping(path = "/view")
	public ModelAndView getRuta(@RequestParam("id") Long id) {
		ModelAndView modelview = new ModelAndView("rutaView");
		Ruta ruta = rutaService.getRuta(id);
		Collection<Punto> punto = ruta.getPunto();
		String puntos = "";
		for (Punto puntoRes : punto) {
			puntos += puntoRes.toString();
		}

		modelview.getModelMap().addAttribute("ruta", ruta);
		modelview.getModelMap().addAttribute("puntos", puntos);//
		// [[3.24,4.22],[...],...] // {
		return modelview;
	}

	@GetMapping(path = "/marcarFavorita")
	public ModelAndView rutaFavorita(@RequestParam("idUser") Long idUser, @RequestParam("idRuta") Long idRuta) {
		Ruta ruta = rutaService.getRuta(idRuta);
		Usuario usuario = userService.getUsuario(idUser);
		if (usuario.getFavorita(idRuta)) {
			usuario.quitarFavorita(ruta);
		} else {
			usuario.addFavorita(ruta);
		}

		userService.guardar(usuario);
		return new ModelAndView("redirect:/rutas/view?id=" + idRuta);

	}

//	@GetMapping(path = "/marcarPendiente")
//	public ModelAndView rutaPendiente(@RequestParam("idUser") Long idUser, @RequestParam("idRuta") Long idRuta) {
//		Ruta ruta = rutaService.getRuta(idRuta);
//		Usuario usuario = userService.getUsuario(idUser);
//		if (usuario.getPendiente(idRuta)) {
//			usuario.quitarpendientes(ruta);
//		} else {
//			usuario.addHecha(ruta);
//		}
//
//		userService.guardar(usuario);
//		return new ModelAndView("redirect:/rutas/view?id=" + idRuta);
//
//	}

	@PostMapping(path = "/comentario")
	public ModelAndView comentarioMap(@RequestParam("contenido") String contenido, @RequestParam("idRuta") Long idRuta,
			@RequestParam("username") String username) {
		Comentario comentario = new Comentario(contenido, rutaService.getRuta(idRuta),
				userService.getUsuario(username));
		comentario.setFechaCreacion(new Date());
		comentarioService.guardar(comentario);
		return new ModelAndView("redirect:/rutas/view?id=" + idRuta);
	}

}
