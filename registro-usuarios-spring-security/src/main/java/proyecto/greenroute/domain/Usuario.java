package proyecto.greenroute.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
//@NamedQueries({ @NamedQuery(name = "Usuario.getUsuarioByEmail", query = "SELECT * FROM Usuario WHERE email = ?1"),
//		@NamedQuery(name = "Usuario.getusuarioByUsername", query = "SELECT * FROM Usuario WHERE username = ?1") })

public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;
	private String password;
	private String email;
	private Date fechaCreacion;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Rol rol;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "usuario")
	private Collection<Ruta> rutas;

	@ManyToMany
	@JoinTable(name = "rutas_favoritas", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "ruta_id", referencedColumnName = "id"))
	private Collection<Ruta> rutasFavoritas;

//	@ManyToMany
//	@JoinTable(name = "rutas_pendientes", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rutaPendiente_id", referencedColumnName = "id"))
//	private Collection<Ruta> rutasPendientes;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "usuario")
	private Collection<Comentario> comentarios;

	public Usuario(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Usuario() {
		super();
	}

	public int getCountRutas() {
		return rutas.size();
	}

	public boolean getFavorita(Long id) {
		Iterator<Ruta> it = rutasFavoritas.iterator();
		while (it.hasNext()) {
			Ruta rutaIt = it.next();
			if (rutaIt.getId() == id) {
				return true;
			}
		}
		return false;
	}

	public void addFavorita(Ruta ruta) {
		rutasFavoritas.add(ruta);
	}

	public void quitarFavorita(Ruta ruta) {
		rutasFavoritas.remove(ruta);
	}

//	public boolean getPendiente(Long id) {
//		Iterator<Ruta> it = rutasPendientes.iterator();
//		while (it.hasNext()) {
//			Ruta rutaIt = it.next();
//			if (rutaIt.getId() == id) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public void addPendientes(Ruta ruta) {
//		rutasPendientes.add(ruta);
//	}
//
//	public void quitarpendientes(Ruta ruta) {
//		rutasPendientes.remove(ruta);
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Collection<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(Collection<Ruta> rutas) {
		this.rutas = rutas;
	}

	public Collection<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Collection<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

//	public Collection<Ruta> getRutasPendientes() {
//		return rutasPendientes;
//	}
//
//	public void setRutasPendientes(Collection<Ruta> rutasPendientes) {
//		this.rutasPendientes = rutasPendientes;
//	}

	public Collection<Ruta> getRutasFavoritas() {
		return rutasFavoritas;
	}

	public void setRutasFavoritas(Collection<Ruta> rutasFavoritas) {
		this.rutasFavoritas = rutasFavoritas;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(rol.toString()));
		return roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
