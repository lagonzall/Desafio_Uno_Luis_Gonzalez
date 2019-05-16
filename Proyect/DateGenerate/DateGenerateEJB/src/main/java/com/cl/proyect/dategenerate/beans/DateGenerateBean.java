package com.cl.proyect.dategenerate.beans;

import java.util.ArrayList;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;

import com.cl.proyect.dategenerate.bo.DateGenerateBO;
import com.cl.proyect.dategenerate.dto.DateGenerateDTO;
import com.cl.proyect.dategenerate.dto.DateGenerateResponseDTO;
import com.cl.proyect.dategenerate.interceptor.Audit;
import com.cl.proyect.dategenerate.interceptor.Logging;
import com.cl.proyect.dategenerate.interceptor.LoggingInterceptor;
import com.cl.proyect.dategenerate.remote.DateGenerateBeanRemote;

@Stateless
@Remote(DateGenerateBeanRemote.class)
@Logging
@Audit
@Interceptors(LoggingInterceptor.class)
@Transactional
public class DateGenerateBean {

	public ArrayList<DateGenerateResponseDTO> setDateGenerte(DateGenerateDTO dateGenerateDTO) throws Exception {
		DateGenerateBO configurationBO = new DateGenerateBO();
		return configurationBO.setDateGenerte(dateGenerateDTO);
	}

}
