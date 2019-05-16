package com.cl.proyect.dategenerate.ws.delegate;

import java.util.ArrayList;

import com.cl.proyect.dategenerate.dto.DateGenerateDTO;
import com.cl.proyect.dategenerate.dto.DateGenerateResponseDTO;
import com.cl.proyect.dategenerate.ws.interceptor.Audit;
import com.cl.proyect.dategenerate.ws.interceptor.Logging;
import com.cl.proyect.dategenerate.ws.servicelocator.DateGenerateServiceLocator;

@Logging
@Audit
public class DateGenerateDelegate {

	private static DateGenerateDelegate configurationDelegate;

	public static DateGenerateDelegate getInstance() {
		if (configurationDelegate == null) {
			configurationDelegate = new DateGenerateDelegate();
		}
		return configurationDelegate;
	}

	/**
	 * @return ArrayList<DateGenerateResponseDTO>
	 * @throws Exception
	 */

	public ArrayList<DateGenerateResponseDTO> setDateGenerte(DateGenerateDTO dateGenerateDTO) throws Exception {
		return DateGenerateServiceLocator.doLookup().setDateGenerte(dateGenerateDTO);
	}

}
