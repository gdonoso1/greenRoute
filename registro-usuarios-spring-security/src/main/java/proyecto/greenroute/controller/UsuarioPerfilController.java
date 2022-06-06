package proyecto.greenroute.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import proyecto.greenroute.domain.Usuario;
import proyecto.greenroute.service.RutaService;
import proyecto.greenroute.service.UsuarioService;

@RestController
@RequestMapping("/users/perfil")
public class UsuarioPerfilController {

	@Autowired
	private UsuarioService usuarioServicio;

	@Autowired
	private RutaService rutaService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping()
	public ModelAndView perfilUsuario(@RequestParam("username") String username) throws ServletException, IOException {
		ModelAndView modelview = new ModelAndView("perfil");

		modelview.getModelMap().addAttribute("usuario", usuarioServicio.getUsuario(username));

		return modelview;

	}

	@PostMapping()
	public ModelAndView perfilUsuarioEdit(@RequestParam("username") String username)
			throws ServletException, IOException {
		ModelAndView modelview = new ModelAndView("perfilEdit");

		modelview.getModelMap().addAttribute("usuario", usuarioServicio.getUsuario(username));

		return modelview;

	}

	@PostMapping("/editado")
	public ModelAndView perfilUsuarioEditado(@RequestParam("idUser") Long idUser,
			@RequestParam("passwordEditado") Long passwordEditado, @RequestParam("username") String username,
			@RequestParam("email") String email, @RequestParam("password") String password)
			throws ServletException, IOException {
		Usuario usuario = usuarioServicio.getUsuario(idUser);
		usuario.setUsername(username);
		if (passwordEditado.equals("2")) {
			usuario.setPassword(passwordEncoder.encode(password));
		}
		usuario.setEmail(email);
		usuarioServicio.guardar(usuario);

		return new ModelAndView("redirect:/users/perfil?username=" + username);

	}

	@GetMapping("/favoritas")
	public ModelAndView perfilRutaHecha(@RequestParam("username") String username)
			throws ServletException, IOException {
		ModelAndView modelview = new ModelAndView("rutaFavorita");

		modelview.getModelMap().addAttribute("usuario", usuarioServicio.getUsuario(username));

		return modelview;

	}

	@PostMapping("/eliminarRuta")
	public ModelAndView eliminarRuta(@RequestParam("id") Long id, @RequestParam("username") String username)
			throws ServletException, IOException {
		rutaService.eliminarRuta(id);

		return new ModelAndView("redirect:/users/perfil?username=" + username);
	}
}
