package proyecto.greenroute.service;

import java.util.List;

import proyecto.greenroute.domain.Rol;

public interface RolService {

	public Rol guardar(Rol rol);

	public List<Rol> listarRol();

	public Rol getRol(Long id);

	public boolean eliminarRol(Long id);
}
