package com.tbs.service.persistence.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.tbs.service.persistence.PersistibleImpl;
import com.tbs.utils.Utilities;

@Entity
@Table(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)

@NamedQuery(name = "User.findAllByCity", query = "select u from User u, RoleByCity rc, RoleUser ru"
		+ " where u.fechaBorrado is null and rc.ciudad.id=:idCiudad and rc.id=ru.roleCiudad and ru.userId=u.id")
public class User extends PersistibleImpl {

	private static final long serialVersionUID = 6231157993144712993L;

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	protected Long id;

	@Column(unique = true)
	private String username;

	private String name;
	private String apellido;

	private String password;

	@Transient
	@JsonIgnore
	private String passwordVerify;

	private String email;

	private String cellPhone;

	private Date lastAccess;

	private Date lastPasswordChange;

	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean superUser = Boolean.FALSE;

	@ManyToOne
	@JoinColumn(columnDefinition = "integer", name = "profile_id", nullable = false)
	private Profile profile;

	// @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// @JoinTable(name = "ciudad_user", joinColumns = {
	// @JoinColumn(name = "user_id", nullable = false) }, inverseJoinColumns = {
	// @JoinColumn(name = "ciudad_id", nullable = false) })
	// private List<Ciudad> ciudades = new ArrayList<Ciudad>();

	// @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// @JoinTable(name = "role_user", joinColumns = {
	// @JoinColumn(name = "user_id", nullable = false) },
	// inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false)})
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "role_user", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "role_ciudad_id", nullable = false) })
	private List<RoleByCity> roles = new ArrayList<RoleByCity>();

	@Override
	public Long getID() {
		return this.id;
	}

	@Override
	public void setID(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPassword() {
		return password;
	}

	public void setCryptPassword(String password) {
		this.password = Utilities.getMD5(password);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<RoleByCity> getRoles() {
		return roles;
	}

	public void addRoleByCity(RoleByCity role) {
		this.roles.add(role);
	}

	public void setRoles(List<RoleByCity> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public Boolean isSuperUser() {
		return this.superUser;
	}

	public void setSuperUser(Boolean active) {
		this.superUser = active;
	}

	public Date getLastPasswordChange() {
		return lastPasswordChange;
	}

	public void setLastPasswordChange(Date lastPasswordChange) {
		this.lastPasswordChange = lastPasswordChange;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
