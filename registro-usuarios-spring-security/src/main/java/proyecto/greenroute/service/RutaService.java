package proyecto.greenroute.service;

import java.util.List;

import proyecto.greenroute.domain.Ruta;

public interface RutaService {

	public List<Ruta> listarRutas();

	public Ruta getRuta(Long id);
	
	public Ruta guardar(Ruta ruta);

	public boolean eliminarRuta(Long id);
	
	public List<Ruta> findByNombreContaining(String nombre);
	
	public List<Ruta> findByPoblacionContaining(String Poblacion);
	
	public List<Ruta> findAllByOrderByCreacionDesc();
}
