package proyecto.greenroute.service;

import java.util.List;

import proyecto.greenroute.domain.Punto;

public interface PuntoService {

	public Punto guardar(Punto punto);

	public List<Punto> listarPunto();

	public Punto getPunto(Long id);

	public boolean eliminarPunto(Long id);
}
