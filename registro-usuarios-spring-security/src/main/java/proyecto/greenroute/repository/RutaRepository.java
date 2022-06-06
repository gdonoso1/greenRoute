package proyecto.greenroute.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.greenroute.domain.Ruta;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {

	public List<Ruta> findByNombreContaining(String nombre);

	public List<Ruta> findByPoblacionContaining(String nombre);

	public List<Ruta> findAllByOrderByCreacionDesc();

}
