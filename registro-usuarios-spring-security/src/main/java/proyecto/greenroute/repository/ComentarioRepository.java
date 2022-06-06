package proyecto.greenroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.greenroute.domain.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
