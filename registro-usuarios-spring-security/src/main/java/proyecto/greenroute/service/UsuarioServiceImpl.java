package proyecto.greenroute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import proyecto.greenroute.domain.Usuario;
import proyecto.greenroute.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepositorio;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByUsername(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return usuario;
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}

//	@Override
//	public ArrayList<String> getUsuarioByEmail(String email) {
//		return usuarioRepositorio.getUsuarioByEmail(email);
//	}
//
//	@Override
//	public ArrayList<String> getusuarioByUsername(String username) {
//		return usuarioRepositorio.getusuarioByUsername(username);
//	}

	@Override
	public Usuario getUsuario(String username) {
		// TODO Auto-generated method stub
		return usuarioRepositorio.findByUsername(username);
	}

	@Override
	public Usuario getUsuario(Long id) {
		return usuarioRepositorio.getById(id);
	}

	@Override
	public Usuario guardar(Usuario usuario) {
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public boolean eliminarUsuario(Long id) {
		try {
			usuarioRepositorio.deleteById(id);
			return true;
		} catch (Exception err) {
			return false;
		}
	}

	@Override
	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return usuarioRepositorio.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return usuarioRepositorio.existsByEmail(email);
	}

}
