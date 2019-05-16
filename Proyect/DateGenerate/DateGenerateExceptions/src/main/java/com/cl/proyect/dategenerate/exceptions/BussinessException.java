package com.cl.proyect.dategenerate.exceptions;

/**
 * @author Luis
 * Clase que crea y maneja Excepciones de Negocio.
 */

public class BussinessException  extends Exception {

	private static final long serialVersionUID = 1L;
	private int code;
	private String customMessage;
	
	public BussinessException(String message) {
		super(message);
	}
	
	public BussinessException(String message, int code, String customMessage) {
		super(message);
		this.code = code;
		this.customMessage = customMessage;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCustomMessage() {
		return customMessage;
	}

	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}
	
}
