
package com.cl.proyect.dategenerate.ws.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.jboss.logging.Logger;

import com.cl.proyect.dategenerate.exceptions.ConfigurationException;
import com.cl.proyect.dategenerate.ws.interceptor.Audit;
import com.cl.proyect.dategenerate.ws.interceptor.Logging;

@Audit
@Logging
public class WSConfigurationFile {

	private static final Logger LOGGER = Logger.getLogger(WSConfigurationFile.class);

	static private WSConfigurationFile instance = null;

	private String jndiDateGenerate;
	private String urlDateGenerateRemote;

	private String genericServiceError;
	private Integer genericServiceErrorCode;
	private String dbError;
	private Integer dbErrorCode;
	private String genericError;
	private Integer genericErrorCode;

	private static Properties props = new Properties();

	public static WSConfigurationFile instance() throws Exception {
		if (instance == null) {
			try {
				instance = new WSConfigurationFile();
			} catch (ConfigurationException ex) {
				LOGGER.error("*********ERROR**********");
				LOGGER.error(ex.getCode());
				LOGGER.error(ex.getCustomMessage());
				LOGGER.error(ex.getMessage());
				throw ex;
			} catch (Exception ex) {
				LOGGER.error("*********ERROR**********");

				LOGGER.error(ex.getMessage());
				throw ex;
			}
		}
		return instance;
	}

	protected WSConfigurationFile() throws Exception {
		String fileName = "";
		try {

			String configurationPath = System.getProperty("jboss.server.config.dir");

			fileName = configurationPath + "/DateGenerateWS.properties";
			FileInputStream file = new FileInputStream(fileName);
			props.load(file);
			LOGGER.info("WSConfigurationFiles.fileName:" + fileName);

			setJndiDateGenerate(getPropertieValue("jndi.DateGenerate.Bean", false));

			setUrlDateGenerateRemote(getPropertieValue("url.DateGenerate.Remote", false));

			setGenericServiceError(props.getProperty("genericServiceError.message"));
			setGenericServiceErrorCode(Integer.parseInt(props.getProperty("genericServiceError.code")));

			setGenericError(props.getProperty("genericError.message"));
			setGenericErrorCode(Integer.parseInt(props.getProperty("genericError.code")));
			setDbError(props.getProperty("dbError.message"));
			setDbErrorCode(Integer.parseInt(props.getProperty("dbError.code")));

		} catch (IOException ex) {

			LOGGER.error(ex.getMessage());

			throw new ConfigurationException(ex.getMessage(), 997, "The file could not be loaded: " + fileName);
		} catch (Exception ex) {

			LOGGER.error(ex.getMessage());

			throw ex;
		}
	}

	private String getEnvironment(String environment) throws Exception {
		String result = "";
		LOGGER.info("environment:" + environment);
		try {
			result = System.getenv(environment);
			if (result == null || result.equals("")) {
				LOGGER.error("RESULT NULO");
			}
		} catch (Exception e) {
			if (result == null) {
				throw new ConfigurationException(e.getMessage(), 996, "there is no environment: " + result);
			} else if (result.equals("")) {
				throw new ConfigurationException(e.getMessage(), 995, "The environment is empty: " + result);
			} else {
				throw e;
			}
		}
		return result;
	}

	private String getPropertieValue(String string, Boolean env) throws Exception {
		String result = "";
		String salida = "";
		String[] arrVariables;
		String strfinal = "";
		result = "";
		LOGGER.info(string);

		try {
			strfinal = props.getProperty(string);
			result = strfinal;
			if (result == null || result.equals("")) {
				throw new Exception();
			}
			arrVariables = result.split("\\$");
			if (arrVariables.length > 1) {
				for (int i = 0; i < arrVariables.length; i++) {

					int inicio = result.indexOf("{") + 1;
					int fin = result.indexOf("}");

					if (fin > 0) {
						String substr = result.substring(inicio, fin);
						String variable = substr;

						if (env) {
							salida = props.getProperty(variable);
							salida = getEnvironment(salida);
							strfinal = strfinal.replace("${" + variable + "}", salida);
						} else {
							salida = props.getProperty(variable);
							strfinal = strfinal.replace("${" + variable + "}", salida);
						}

						result = strfinal;
					}
				}
			}

			LOGGER.info("result:" + result);

		} catch (Exception e) {

			LOGGER.error("ERROR", e);

			if (result == null || result.equals("")) {
				throw new ConfigurationException(e.getMessage(), 995,
						"The property is empty: " + string + " " + result);
			} else {
				throw new ConfigurationException(e.getMessage(), 996, "there is no property: " + " " + result);
			}
		}

		result = strfinal;
		return result;
	}

	public String getGenericServiceError() {
		return genericServiceError;
	}

	public void setGenericServiceError(String genericServiceError) {
		this.genericServiceError = genericServiceError;
	}

	public Integer getGenericServiceErrorCode() {
		return genericServiceErrorCode;
	}

	public void setGenericServiceErrorCode(Integer genericServiceErrorCode) {
		this.genericServiceErrorCode = genericServiceErrorCode;
	}

	public String getDbError() {
		return dbError;
	}

	public void setDbError(String dbError) {
		this.dbError = dbError;
	}

	public Integer getDbErrorCode() {
		return dbErrorCode;
	}

	public void setDbErrorCode(Integer dbErrorCode) {
		this.dbErrorCode = dbErrorCode;
	}

	public String getGenericError() {
		return genericError;
	}

	public void setGenericError(String genericError) {
		this.genericError = genericError;
	}

	public Integer getGenericErrorCode() {
		return genericErrorCode;
	}

	public void setGenericErrorCode(Integer genericErrorCode) {
		this.genericErrorCode = genericErrorCode;
	}

	public String getJndiDateGenerate() {
		return jndiDateGenerate;
	}

	public void setJndiDateGenerate(String jndiDateGenerate) {
		this.jndiDateGenerate = jndiDateGenerate;
	}

	public String getUrlDateGenerateRemote() {
		return urlDateGenerateRemote;
	}

	public void setUrlDateGenerateRemote(String urlDateGenerateRemote) {
		this.urlDateGenerateRemote = urlDateGenerateRemote;
	}

}
