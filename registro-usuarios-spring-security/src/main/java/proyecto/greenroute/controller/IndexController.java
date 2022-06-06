package proyecto.greenroute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import proyecto.greenroute.service.RutaService;

@RestController
@RequestMapping("/")
public class IndexController {

	@Autowired
	private RutaService rutaService;

	@GetMapping
	public ModelAndView getAllRutas() {
		;
		ModelAndView modelview = new ModelAndView("index");

		modelview.getModelMap().addAttribute("rutas", rutaService.findAllByOrderByCreacionDesc());

		return modelview;
	}

}
