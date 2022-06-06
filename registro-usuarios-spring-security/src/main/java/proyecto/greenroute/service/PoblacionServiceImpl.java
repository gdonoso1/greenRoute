package proyecto.greenroute.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.greenroute.domain.Poblacion;
import proyecto.greenroute.domain.Ruta;
import proyecto.greenroute.repository.PoblacionRepository;

@Service
public class PoblacionServiceImpl implements PoblacionService {

	@Autowired
	private PoblacionRepository poblacionRepository;

	@Override
	public List<Poblacion> listarPoblacion() {
		return poblacionRepository.findAll();
	}

	@Override
	public Poblacion getPoblacion(Long id) {
		return poblacionRepository.getById(id);
	}

	@Override
	public Poblacion guardar(Poblacion poblacion) {
		return poblacionRepository.save(poblacion);
	}

	@Override
	public boolean eliminarPoblacion(Long id) {
		try {
			poblacionRepository.deleteById(id);
			return true;
		} catch (Exception err) {
			return false;
		}
	}

//	@Override
//	public List<Poblacion> getDistinctPoblacionByPueblo() {
//		// TODO Auto-generated method stub
//		return poblacionRepository.getDistinctPoblacionByPueblo();
//	}

//	@Override
//	public ArrayList<String> findCAutonoma() {
//		return poblacionRepository.findCAutonoma();
//	}
//
//	@Override
//	public ArrayList<String> findProvincia(String c_autonoma) {
//		return poblacionRepository.findProvincia(c_autonoma);
//	}
//
//	@Override
//	public ArrayList<String> findPoblacion(String provincia) {
//		return poblacionRepository.findPoblacion(provincia);
//	}
	
	

}
