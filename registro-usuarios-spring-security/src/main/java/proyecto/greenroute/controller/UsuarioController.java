package proyecto.greenroute.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import proyecto.greenroute.domain.Usuario;
import proyecto.greenroute.service.RolService;
import proyecto.greenroute.service.UsuarioService;

@RestController
@RequestMapping("/users")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioServicio;

	@Autowired
	private RolService rolService;

	@GetMapping
	public Model verPaginaDeInicio(Model modelo) {
		modelo.addAttribute("usuarios", usuarioServicio.listarUsuarios());
		return modelo;
	}

	@GetMapping("/user")
	public ModelAndView adminToUser(@RequestParam("username") String username) throws ServletException, IOException {
		Usuario usuario = usuarioServicio.getUsuario(username);
		usuario.setRol(rolService.getRol(2L));
		usuarioServicio.guardar(usuario);

		return new ModelAndView("redirect:/users");
	}

	@GetMapping("/admin")
	public ModelAndView userToAdmin(@RequestParam("username") String username) throws ServletException, IOException {
		Usuario usuario = usuarioServicio.getUsuario(username);
		usuario.setRol(rolService.getRol(1L));
		usuarioServicio.guardar(usuario);

		return new ModelAndView("redirect:/users");
	}

	@PostMapping("/editar")
	public ModelAndView edit(@RequestParam("username") String username) throws ServletException, IOException {
		ModelAndView modelview = new ModelAndView("editUser");

		modelview.getModelMap().addAttribute("usuario", usuarioServicio.getUsuario(username));

		return modelview;

	}

	@PostMapping("/editado")
	public ModelAndView editUsuario(@RequestParam("usernameOriginal") String usernameOriginal,
			@RequestParam("username") String username, @RequestParam("email") String email)
			throws ServletException, IOException {
		Usuario usuario = usuarioServicio.getUsuario(usernameOriginal);
		usuario.setUsername(username);
		usuario.setEmail(email);
		usuarioServicio.guardar(usuario);

		return new ModelAndView("redirect:/users");
	}

	@PostMapping("/eliminar")
	public ModelAndView eliminarUsuario(@RequestParam("id") Long id) throws ServletException, IOException {
		usuarioServicio.eliminarUsuario(id);

		return new ModelAndView("redirect:/users");
	}
}
