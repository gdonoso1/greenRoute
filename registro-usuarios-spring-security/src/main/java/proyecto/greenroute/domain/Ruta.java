package proyecto.greenroute.domain;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ruta")
//@NamedQueries({
//		@NamedQuery(name = "Ruta.findNombre", query = "SELECT id, nombre, latitud, longitud, poblacion, descripcion, fechaCreacion, km FROM Ruta WHERE nombre = ?1 ") })
public class Ruta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private double latitud;
	private double longitud;
	private String poblacion;
	@Column(length = 2000)
	private String descripcion;
	private Date creacion;
	private int km;

//	@ManyToOne(cascade = CascadeType.PERSIST)
//	private Poblacion poblacion;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "ruta")
	private Collection<Comentario> comentarios;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ruta")
	private Collection<Punto> punto;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Usuario usuario;

	@ManyToMany(mappedBy = "rutasFavoritas")
	private Collection<Usuario> usuarioFavorita;

//	@ManyToMany(mappedBy = "rutasPendientes")
//	private Collection<Usuario> usuarioPendientes;

	public Ruta() {
		super();
	}

	public Ruta(String nombre, double latitud, double longitud, String poblacion, Collection<Punto> punto,
			Usuario usuario, String descripcion) {
		super();
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
		this.poblacion = poblacion;
		this.punto = punto;
		this.usuario = usuario;
		this.descripcion = descripcion;
	}

	public String getFecha() {
		SimpleDateFormat getYearFormat = new SimpleDateFormat("dd-MM-yyyy");

		return getYearFormat.format(creacion);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public Collection<Punto> getPunto() {
		return punto;
	}

	public void setPunto(Collection<Punto> punto) {
		this.punto = punto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Collection<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Collection<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

//	public Collection<Usuario> getUsuarioPendientes() {
//		return usuarioPendientes;
//	}
//
//	public void setUsuarioPendientes(Collection<Usuario> usuarioPendientes) {
//		this.usuarioPendientes = usuarioPendientes;
//	}

	public Collection<Usuario> getUsuarioFavorita() {
		return usuarioFavorita;
	}

	public void setUsuarioFavorita(Collection<Usuario> usuarioFavorita) {
		this.usuarioFavorita = usuarioFavorita;
	}

	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
