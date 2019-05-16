package com.cl.proyect.dategenerate.dto;

import java.io.Serializable;

public class ResponseDTO implements Serializable {

	/**
	 * Objeto de transporte de mensajes de respuesta del cual heredan todos los
	 * objetos DTO.
	 */
	private static final long serialVersionUID = 1L;
	private Integer code;
	private String message;
	private Long responseId;
	private String responseString;

	public Long getResponseId() {
		return responseId;
	}

	public void setResponseId(Long responseId) {
		this.responseId = responseId;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
}
