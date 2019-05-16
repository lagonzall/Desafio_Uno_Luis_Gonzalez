package com.cl.proyect.dategenerate.remote;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.cl.proyect.dategenerate.dto.DateGenerateDTO;
import com.cl.proyect.dategenerate.dto.DateGenerateResponseDTO;

@Remote
public interface DateGenerateBeanRemote {

	public ArrayList<DateGenerateResponseDTO> setDateGenerte(DateGenerateDTO dateGenerateDTO) throws Exception;

}
