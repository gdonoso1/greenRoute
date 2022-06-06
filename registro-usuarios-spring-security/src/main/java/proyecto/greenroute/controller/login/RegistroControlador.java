package proyecto.greenroute.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RegistroControlador {

//	@Autowired
//	private UsuarioServicio servicio;
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
//	@GetMapping("/")
//	public String verPaginaDeInicio(Model modelo) {
//		modelo.addAttribute("usuarios", servicio.listarUsuarios());
//		return "index";
//	}
}
