package proyecto.greenroute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.greenroute.domain.Comentario;
import proyecto.greenroute.repository.ComentarioRepository;

@Service
public class ComentarioServiceImpl implements ComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Override
	public List<Comentario> listarComentario() {
		return comentarioRepository.findAll();
	}

	@Override
	public Comentario getComentario(Long id) {
		return comentarioRepository.getById(id);
	}

	@Override
	public Comentario guardar(Comentario poblacion) {
		return comentarioRepository.save(poblacion);
	}

	@Override
	public boolean eliminarComentario(Long id) {
		try {
			comentarioRepository.deleteById(id);
			return true;
		} catch (Exception err) {
			return false;
		}
	}

}
