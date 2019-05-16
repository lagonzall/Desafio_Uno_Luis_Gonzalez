package com.cl.proyect.dategenerate.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import javax.interceptor.Interceptors;

import com.cl.proyect.dategenerate.dto.DateGenerateDTO;
import com.cl.proyect.dategenerate.dto.DateGenerateResponseDTO;
import com.cl.proyect.dategenerate.interceptor.Audit;
import com.cl.proyect.dategenerate.interceptor.Logging;
import com.cl.proyect.dategenerate.interceptor.LoggingInterceptor;
import com.cl.proyect.dategenerate.utils.EJBConfigurationFile;

@Logging
@Audit
@Interceptors(LoggingInterceptor.class)
public class DateGenerateBO {

	private String formatoDia = "dd";
	private Integer dia;

	public ArrayList<DateGenerateResponseDTO> setDateGenerte(DateGenerateDTO dateGenerateDTO) throws Exception {

		ArrayList<DateGenerateResponseDTO> dategenerateDTO = new ArrayList<>();
		DateGenerateResponseDTO dateGenerateResponseDTO = new DateGenerateResponseDTO();

		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat(formatoDia);

			ThreadLocalRandom r = ThreadLocalRandom.current();

			for (int i = 0; i < 100; i++) {

				Date rnd = new Date(r.nextLong(dateGenerateDTO.getFechaCreacion().getTime(),
						dateGenerateDTO.getFechaFin().getTime()));

				dateGenerateResponseDTO.getFechasFaltantes().add(rnd);

			}

			DateGenerateResponseDTO dateGeneratedto = new DateGenerateResponseDTO();

			for (int i = 0; i < dateGenerateResponseDTO.getFechasFaltantes().size(); i++) {

				ArrayList<Date> rnd = dateGenerateResponseDTO.getFechasFaltantes();

				dia = Integer.parseInt(dateFormat.format(rnd.get(i)));

				if (dia == 1) {
					dateGeneratedto.getFechasFaltantes().add(dateGenerateResponseDTO.getFechasFaltantes().get(i));

				}

			}

			dateGeneratedto.setId(dateGenerateDTO.getId());
			dateGeneratedto.setFechaCreacion(dateGenerateDTO.getFechaCreacion());
			dateGeneratedto.setFechaFin(dateGenerateDTO.getFechaFin());

			dategenerateDTO.add(dateGeneratedto);

		} catch (Exception e) {

			dateGenerateResponseDTO.setCode(EJBConfigurationFile.instance().getGenericErrorCode());
			dateGenerateResponseDTO.setMessage(EJBConfigurationFile.instance().getGenericError());
			dategenerateDTO.add(dateGenerateResponseDTO);

		}
		return dategenerateDTO;

	}

}
