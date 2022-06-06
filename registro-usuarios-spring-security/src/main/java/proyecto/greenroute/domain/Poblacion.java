package proyecto.greenroute.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "poblacion")
//@NamedQueries({ @NamedQuery(name = "Poblacion.findCAutonoma", query = "SELECT DISTINCT(c_autonoma) FROM Poblacion"),
//		@NamedQuery(name = "Poblacion.findProvincia", query = "SELECT DISTINCT(provincia) FROM Poblacion WHERE c_autonoma = ?1"),
//		@NamedQuery(name = "Poblacion.findPoblacion", query = "SELECT DISTINCT(poblacion) FROM Poblacion WHERE provincia = ?1") })
public class Poblacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo_postal;
	private String pueblo;
	private String c_autonoma;
	private String provincia;
	private String latitud;
	private String longitud;

//	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "poblacion")
//	private Collection<Ruta> ruta;

	public Poblacion(String codigo_postal, String poblacion, String c_autonoma, String provincia, String latitud,
			String longitud) {
		super();
		this.codigo_postal = codigo_postal;
		this.pueblo = poblacion;
		this.c_autonoma = c_autonoma;
		this.provincia = provincia;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Poblacion() {
		super();
	}

	public String getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	public String getPueblo() {
		return pueblo;
	}

	public void setPueblo(String pueblo) {
		this.pueblo = pueblo;
	}

	public String getC_autonoma() {
		return c_autonoma;
	}

	public void setC_autonoma(String c_autonoma) {
		this.c_autonoma = c_autonoma;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public float getLatitud() {
		return Float.parseFloat(latitud);
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return Float.parseFloat(longitud);
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

}
