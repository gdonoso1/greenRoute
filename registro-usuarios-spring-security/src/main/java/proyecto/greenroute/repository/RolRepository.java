package proyecto.greenroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.greenroute.domain.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

}
