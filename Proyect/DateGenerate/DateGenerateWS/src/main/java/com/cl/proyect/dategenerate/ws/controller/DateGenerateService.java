package com.cl.proyect.dategenerate.ws.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.cl.proyect.dategenerate.dto.DateGenerateDTO;
import com.cl.proyect.dategenerate.dto.DateGenerateResponseDTO;
import com.cl.proyect.dategenerate.ws.delegate.DateGenerateDelegate;
import com.cl.proyect.dategenerate.ws.interceptor.Audit;
import com.cl.proyect.dategenerate.ws.interceptor.Logging;
import com.cl.proyect.dategenerate.ws.interceptor.LoggingInterceptor;
import com.cl.proyect.dategenerate.ws.utils.WSConfigurationFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/dateGenerateProyect")
@Logging
@Audit
@Interceptors(LoggingInterceptor.class)
public class DateGenerateService {

	private static Gson gson = new GsonBuilder().registerTypeAdapter(Date.class,
			new GsonBuilder().setDateFormat("yyyy-MM-dd").create().getAdapter(Date.class).nullSafe()).create();

	@POST
	@Path("/DateGenerate/")
	@Produces("application/json")
	@Consumes("application/json")
	public String setDateGenerte(DateGenerateDTO dateGenerateDTO) throws Exception {

		DateGenerateDelegate configurationDelegate = DateGenerateDelegate.getInstance();

		DateGenerateResponseDTO dateGenerateResponseDTO = new DateGenerateResponseDTO();

		ArrayList<DateGenerateResponseDTO> dategenerateDTO = new ArrayList<>();

		try {

			dategenerateDTO = configurationDelegate.setDateGenerte(dateGenerateDTO);

		} catch (Exception e) {

			dateGenerateResponseDTO.setCode(WSConfigurationFile.instance().getGenericServiceErrorCode());
			dateGenerateResponseDTO.setMessage(WSConfigurationFile.instance().getGenericServiceError());

			dategenerateDTO.add(dateGenerateResponseDTO);

		}
		return gson.toJson(dategenerateDTO);

	}

}
