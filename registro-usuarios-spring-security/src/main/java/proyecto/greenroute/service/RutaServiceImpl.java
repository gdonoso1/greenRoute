package proyecto.greenroute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.greenroute.domain.Ruta;
import proyecto.greenroute.repository.RutaRepository;

@Service
public class RutaServiceImpl implements RutaService {

	@Autowired
	private RutaRepository rutaRepository;

	@Override
	public List<Ruta> listarRutas() {
		return rutaRepository.findAll();
	}

	@Override
	public Ruta getRuta(Long id) {
		return rutaRepository.getById(id);
	}
	
	@Override
	public Ruta guardar(Ruta ruta) {
		return rutaRepository.save(ruta);
	}


	@Override
	public boolean eliminarRuta(Long id) {
		try {
			rutaRepository.deleteById(id);
			return true;
		} catch (Exception err) {
			return false;
		}
	}

	@Override
	public List<Ruta> findByNombreContaining(String nombre) {
		return rutaRepository.findByNombreContaining(nombre);
	}
	
	@Override
	public List<Ruta> findByPoblacionContaining(String poblacion) {
		return rutaRepository.findByPoblacionContaining(poblacion);
	}

	@Override
	public List<Ruta> findAllByOrderByCreacionDesc() {
		// TODO Auto-generated method stub
		return rutaRepository.findAllByOrderByCreacionDesc();
	}


}
