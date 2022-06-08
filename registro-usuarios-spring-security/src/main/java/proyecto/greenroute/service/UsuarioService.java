package proyecto.greenroute.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import proyecto.greenroute.domain.Usuario;

public interface UsuarioService extends UserDetailsService {

	public List<Usuario> listarUsuarios();

//	public ArrayList<String> getUsuarioByEmail(String email);
//	
//	public ArrayList<String> getusuarioByUsername(String username);
//	
	public Usuario getUsuario(String username);
	
	public Usuario getUsuario(Long id);
	
	public Usuario guardar(Usuario usuario);

	public boolean eliminarUsuario(Long id);

	public boolean existsByUsername(String username);
	
	public boolean existsByEmail(String email);
}
