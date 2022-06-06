package proyecto.greenroute.service;

import java.util.List;

import proyecto.greenroute.domain.Comentario;

public interface ComentarioService {

	public Comentario guardar(Comentario comentario);

	public List<Comentario> listarComentario();

	public Comentario getComentario(Long id);

	public boolean eliminarComentario(Long id);
}
