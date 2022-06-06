package proyecto.greenroute.service;

import java.util.ArrayList;
import java.util.List;

import proyecto.greenroute.domain.Poblacion;
import proyecto.greenroute.domain.Ruta;

public interface PoblacionService {

	public Poblacion guardar(Poblacion poblacion);

	public List<Poblacion> listarPoblacion();

	public Poblacion getPoblacion(Long id);

	public boolean eliminarPoblacion(Long id);

//	public ArrayList<String> findCAutonoma();
//	
//	public ArrayList<String> findProvincia(String c_autonoma);
//	
//	public ArrayList<String> findPoblacion(String provincia);

//	public List<Poblacion> getDistinctPoblacionByPueblo();
}
