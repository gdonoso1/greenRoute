package proyecto.greenroute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.greenroute.domain.Punto;
import proyecto.greenroute.repository.PuntoRepository;

@Service
public class PuntoServiceImpl implements PuntoService {

	@Autowired
	private PuntoRepository puntoRepository;

	@Override
	public List<Punto> listarPunto() {
		return puntoRepository.findAll();
	}

	@Override
	public Punto getPunto(Long id) {
		return puntoRepository.getById(id);
	}

	@Override
	public Punto guardar(Punto punto) {
		return puntoRepository.save(punto);
	}

	@Override
	public boolean eliminarPunto(Long id) {
		try {
			puntoRepository.deleteById(id);
			return true;
		} catch (Exception err) {
			return false;
		}
	}

}
