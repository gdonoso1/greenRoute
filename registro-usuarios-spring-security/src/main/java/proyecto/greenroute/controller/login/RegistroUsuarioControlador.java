package proyecto.greenroute.controller.login;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import proyecto.greenroute.domain.Usuario;
import proyecto.greenroute.service.RolService;
import proyecto.greenroute.service.UsuarioService;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

	@Autowired
	private UsuarioService usuarioServicio;

	@Autowired
	private RolService rolService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public RegistroUsuarioControlador(UsuarioService usuarioServicio) {
		super();
		this.usuarioServicio = usuarioServicio;
	}

	@ModelAttribute("usuario")
	public Usuario retornarNuevoUsuario() {
		return new Usuario();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}

	@PostMapping
	public ModelAndView registrarCuentaDeUsuario(@ModelAttribute("usuario") Usuario usuario) {
		ModelAndView modelview = new ModelAndView("redirect:/registro");
		try {
			if (usuarioServicio.existsByEmail(usuario.getEmail())) {
				modelview.getModelMap().addAttribute("error", "Email ya en uso");
				modelview.getModelMap().addAttribute("idUser", usuario.getId());
				return modelview;
			} else {
				usuario.setRol(rolService.getRol(2L));
				usuario.setFechaCreacion(new Date());
				usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
				usuarioServicio.guardar(usuario);

			}
		} catch (Exception e) {
			if (usuarioServicio.existsByUsername(usuario.getUsername())) {
				modelview.getModelMap().addAttribute("error", "Nombre de usuario existente");
				modelview.getModelMap().addAttribute("idUser", usuario.getId());
				return modelview;
			}
		}

		return new ModelAndView("redirect:/registro?exito");

	}
}
