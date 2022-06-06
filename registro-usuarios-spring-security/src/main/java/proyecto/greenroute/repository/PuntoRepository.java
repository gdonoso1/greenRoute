package proyecto.greenroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.greenroute.domain.Punto;

@Repository
public interface PuntoRepository extends JpaRepository<Punto, Long> {

}
