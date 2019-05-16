package com.cl.proyect.dategenerate.dto;

import java.util.ArrayList;
import java.util.List;

public class ListDateGenerateDTO extends ResponseDTO {

	private static final long serialVersionUID = 1L;

	private List<DateGenerateResponseDTO> dateGenerateDTOList = new ArrayList<>();

	public List<DateGenerateResponseDTO> getDateGenerateDTOList() {
		return dateGenerateDTOList;
	}

	public void setDateGenerateDTOList(List<DateGenerateResponseDTO> dateGenerateDTOList) {
		this.dateGenerateDTOList = dateGenerateDTOList;
	}

	
}

