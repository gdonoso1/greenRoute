package proyecto.greenroute.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.greenroute.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

//	public ArrayList<String> getUsuarioByEmail(String email);
//
//	public ArrayList<String> getusuarioByUsername(String username);
	
	public Usuario findByUsername(String username);

}
