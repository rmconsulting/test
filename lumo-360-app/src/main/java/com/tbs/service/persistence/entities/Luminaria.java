package com.tbs.service.persistence.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.tbs.service.persistence.PersistibleImpl;

@SqlResultSetMapping(name = "updateResultControlador", columns = { @ColumnResult(name = "count") })
@NamedNativeQueries({
		@NamedNativeQuery(name = "updateLuminaria", query = "UPDATE luminaria SET controlador_id = NULL WHERE controlador_id = ?", resultSetMapping = "updateResult") })
@Entity
@Table(name = "luminaria")
public class Luminaria extends PersistibleImpl {

	private static final long serialVersionUID = 8824017314688590470L;

	@Id
	@GeneratedValue
	@Column(name = "luminaria_id")
	protected Long id;

	private String nombre;
	private String marca;
	private String modelo;
	private String nroSerie;
	private String nroInventario;
	private Date fechaCompra;
	private Date fechaInstalacion;
	private Date fechaFinGarantia;
	private Long vidaUtilHs;
	private Double potenciaWatts;
	private Double voltageMin;
	private Double voltageMax;
	private Double frecuenciaHz;
	private Double thd;
	private Double factorDePotencia;
	private Double peso;
	private Double altura;

	// private Controlador controlador;

	@ManyToOne
	@JoinColumn(columnDefinition = "integer", name = "controlador_id")
	private Controlador controlador;

	public Luminaria() {

	}

	public Luminaria(String nombre, String marca) {
		setNombre(nombre);
		setMarca(marca);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNroSerie() {
		return nroSerie;
	}

	public void setNroSerie(String nroSerie) {
		this.nroSerie = nroSerie;
	}

	public String getNroInventario() {
		return nroInventario;
	}

	public void setNroInventario(String nroInventario) {
		this.nroInventario = nroInventario;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Date getFechaInstalacion() {
		return fechaInstalacion;
	}

	public void setFechaInstalacion(Date fechaInstalacion) {
		this.fechaInstalacion = fechaInstalacion;
	}

	public Date getFechaFinGarantia() {
		return fechaFinGarantia;
	}

	public void setFechaFinGarantia(Date fechaFinGarantia) {
		this.fechaFinGarantia = fechaFinGarantia;
	}

	public Long getVidaUtilHs() {
		return vidaUtilHs;
	}

	public void setVidaUtilHs(Long vidaUtilHs) {
		this.vidaUtilHs = vidaUtilHs;
	}

	public Double getPotenciaWatts() {
		return potenciaWatts;
	}

	public void setPotenciaWatts(Double potenciaWatts) {
		this.potenciaWatts = potenciaWatts;
	}

	public Double getVoltageMin() {
		return voltageMin;
	}

	public void setVoltageMin(Double voltageMin) {
		this.voltageMin = voltageMin;
	}

	public Double getVoltageMax() {
		return voltageMax;
	}

	public void setVoltageMax(Double voltageMax) {
		this.voltageMax = voltageMax;
	}

	public Double getFrecuenciaHz() {
		return frecuenciaHz;
	}

	public void setFrecuenciaHz(Double frecuenciaHz) {
		this.frecuenciaHz = frecuenciaHz;
	}

	public Double getThd() {
		return thd;
	}

	public void setThd(Double thd) {
		this.thd = thd;
	}

	public Double getFactorDePotencia() {
		return factorDePotencia;
	}

	public void setFactorDePotencia(Double factorDePotencia) {
		this.factorDePotencia = factorDePotencia;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	@Override
	public String toString() {
		return getNombre() + "-";
	}

	@Override
	public Long getID() {
		return this.id;
	}

	@Override
	public void setID(Long id) {
		this.id = id;
	}

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	//

}
