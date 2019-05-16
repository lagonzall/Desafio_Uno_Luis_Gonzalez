package com.cl.proyect.dategenerate.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Objeto de transporte de datos según la entidad: Action.
 */
public class DateGenerateDTO extends ResponseDTO {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Date fechaCreacion;

	private Date fechaFin;

	private ArrayList<Date> fechas = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ArrayList<Date> getFechas() {
		return fechas;
	}

	public void setFechas(ArrayList<Date> fechas) {
		this.fechas = fechas;
	}

}
