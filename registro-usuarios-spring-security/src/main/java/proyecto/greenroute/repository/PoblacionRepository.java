package proyecto.greenroute.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.greenroute.domain.Poblacion;

@Repository
public interface PoblacionRepository extends JpaRepository<Poblacion, Long> {

//	public ArrayList<String> findCAutonoma();
//
//	public ArrayList<String> findProvincia(String c_autonoma);
//
//	public ArrayList<String> findPoblacion(String provincia);
	
//	public List<Poblacion> getDistinctPoblacionByPueblo();

}
