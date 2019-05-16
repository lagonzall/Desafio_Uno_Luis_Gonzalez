package com.cl.proyect.dategenerate.interceptor;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.jboss.logging.Logger;
import com.google.gson.Gson;

/**
 * LoggingInterceptor binds to {@link @Logging} annotation, so methods or beans
 * which @Logging annotation is applied to, will be intercepted.
 *
 * @author ievgen.shulga
 */
@Interceptor
@Logging
public class LoggingInterceptor {

	private static final Logger LOGGER = Logger.getLogger(LoggingInterceptor.class);
	Gson gson = new Gson();

	static LoggingInterceptor loggingInterceptor = null;

	public static LoggingInterceptor getInstance() {
		if (loggingInterceptor == null) {
			loggingInterceptor = new LoggingInterceptor();
		}
		return loggingInterceptor;
	}

	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {

		LOGGER.info("************************INICIO*********************************");

		LOGGER.info("Clase:" + ic.getTarget().getClass().getSimpleName());

		LOGGER.info("Metodo:" + ic.getMethod().getName());

		LOGGER.info("Tipo de Dato:" + ic.getParameters().getClass().getName());

		Object respuesta = new Object();

		if (ic.getParameters().length > 0) {

			LOGGER.info("Parametro Entrada:" + gson.toJson(ic.getParameters()[0]));

		}

		try {

			respuesta = ic.proceed();

			LOGGER.info("respuesta:" + gson.toJson(respuesta));

			LOGGER.info("***********************FIN**********************************");

			return respuesta;

		} catch (Throwable throwable) {

			String StackTraceError = "";

			StringWriter errors = new StringWriter();

			throwable.printStackTrace(new PrintWriter(errors));

			StackTraceError = errors.toString();

			LOGGER.info("************************ERROR*******************************");

			LOGGER.error("Causa: ", throwable);

			LOGGER.error("Detalle del error: " + StackTraceError);

		}

		return respuesta;

	}

	@AroundConstruct
	public Object aroundConstruct(InvocationContext ic) throws Exception {

		LOGGER.info("************************INICIO*********************************");

		Object respuesta = new Object();

		if (ic.getParameters().length > 0) {

			LOGGER.info("Parametro Entrada:" + gson.toJson(ic.getParameters()[0]));

		}

		try {

			respuesta = ic.proceed();

			return respuesta;

		} catch (Throwable throwable) {

			String StackTraceError = "";

			StringWriter errors = new StringWriter();

			throwable.printStackTrace(new PrintWriter(errors));

			StackTraceError = errors.toString();

			LOGGER.info("************************ERROR*******************************");

			LOGGER.error("Causa: ", throwable);

			LOGGER.error("Detalle del error: " + StackTraceError);

			LOGGER.info("************************ERROR*******************************");

		}

		return respuesta;

	}

}
