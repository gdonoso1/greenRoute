package proyecto.greenroute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.greenroute.domain.Rol;
import proyecto.greenroute.repository.RolRepository;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	private RolRepository rolRepository;

	@Override
	public List<Rol> listarRol() {
		return rolRepository.findAll();
	}

	@Override
	public Rol getRol(Long id) {
		return rolRepository.getById(id);
	}

	@Override
	public Rol guardar(Rol rol) {
		return rolRepository.save(rol);
	}

	@Override
	public boolean eliminarRol(Long id) {
		try {
			rolRepository.deleteById(id);
			return true;
		} catch (Exception err) {
			return false;
		}
	}

}
