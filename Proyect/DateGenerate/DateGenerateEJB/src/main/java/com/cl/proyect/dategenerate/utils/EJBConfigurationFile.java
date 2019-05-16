
package com.cl.proyect.dategenerate.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.jboss.logging.Logger;

import com.cl.proyect.dategenerate.exceptions.ConfigurationException;
import com.cl.proyect.dategenerate.interceptor.Audit;
import com.cl.proyect.dategenerate.interceptor.Logging;

@Audit
@Logging
public class EJBConfigurationFile {

	private static final Logger LOGGER = Logger.getLogger(EJBConfigurationFile.class);
	static private EJBConfigurationFile instance = null;

	private String success;
	private Integer successCode;

	private Integer idCodeNull;

	private String invalidInput;
	private Integer invalidInputCode;
	private String emptyList;
	private Integer emptyListCode;

	private String genericServiceError;
	private Integer genericServiceErrorCode;

	private String namingException;
	private Integer namingExceptionCode;
	private String serviceError;
	private Integer serviceErrorCode;
	private String missingRequiredData;
	private Integer missingRequiredDataCode;
	private String genericError;
	private Integer genericErrorCode;
	private String dbError;
	private Integer dbErrorCode;

	private String insertState;
	private Integer insertStateCode;
	private String onMemoryState;
	private Integer onMemoryStateCode;
	private String successState;
	private Integer successStateCode;
	private String errorState;
	private Integer errorStateCode;
	private String successInformedState;
	private Integer successInformedStateCode;
	private String errorInformedState;
	private Integer errorInformedStateCode;
	private String cancelState;
	private Integer cancelStateCode;

	private static Properties props = new Properties();

	public static EJBConfigurationFile instance() throws Exception {
		if (instance == null) {
			try {
				instance = new EJBConfigurationFile();
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

	protected EJBConfigurationFile() throws Exception {
		String fileName = "";
		try {

			String configurationPath = System.getProperty("jboss.server.config.dir");

			fileName = configurationPath + "/DateGenerateEJB.properties";
			FileInputStream file = new FileInputStream(fileName);
			props.load(file);
			LOGGER.info("WSConfigurationFiles.fileName:" + fileName);

			setSuccess(props.getProperty("success.message"));
			setSuccessCode(Integer.parseInt(props.getProperty("success.code")));

			setIdCodeNull(Integer.parseInt(props.getProperty("idCodeNull")));

			setInvalidInput(props.getProperty("invalidInput.message"));
			setInvalidInputCode(Integer.parseInt(props.getProperty("invalidInput.code")));
			setEmptyList(props.getProperty("emptyList.message"));
			setEmptyListCode(Integer.parseInt(props.getProperty("emptyList.code")));
			setGenericServiceError(props.getProperty("genericServiceError.message"));
			setGenericServiceErrorCode(Integer.parseInt(props.getProperty("genericServiceError.code")));

			setNamingException(props.getProperty("namingException.message"));
			setNamingExceptionCode(Integer.parseInt(props.getProperty("namingException.code")));
			setServiceError(props.getProperty("serviceError.message"));
			setServiceErrorCode(Integer.parseInt(props.getProperty("serviceError.code")));
			setMissingRequiredData(props.getProperty("missingRequiredData.message"));
			setMissingRequiredDataCode(Integer.parseInt(props.getProperty("missingRequiredData.code")));
			setGenericError(props.getProperty("genericError.message"));
			setGenericErrorCode(Integer.parseInt(props.getProperty("genericError.code")));
			setDbError(props.getProperty("dbError.message"));
			setDbErrorCode(Integer.parseInt(props.getProperty("dbError.code")));

			setInsertState(props.getProperty("insertState.message"));
			setInsertStateCode(Integer.parseInt(props.getProperty("insertState.code")));
			setOnMemoryState(props.getProperty("onMemoryState.message"));
			setOnMemoryStateCode(Integer.parseInt(props.getProperty("onMemoryState.code")));
			setSuccessState(props.getProperty("successState.message"));
			setSuccessStateCode(Integer.parseInt(props.getProperty("successState.code")));
			setErrorState(props.getProperty("errorState.message"));
			setErrorStateCode(Integer.parseInt(props.getProperty("errorState.code")));
			setSuccessInformedState(props.getProperty("successInformedState.message"));
			setSuccessInformedStateCode(Integer.parseInt(props.getProperty("successInformedState.code")));
			setErrorInformedState(props.getProperty("errorInformedState.message"));
			setErrorInformedStateCode(Integer.parseInt(props.getProperty("errorInformedState.code")));
			setCancelState(props.getProperty("cancelState.message"));
			setCancelStateCode(Integer.parseInt(props.getProperty("cancelState.code")));

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

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Integer getSuccessCode() {
		return successCode;
	}

	public void setSuccessCode(Integer successCode) {
		this.successCode = successCode;
	}

	public Integer getIdCodeNull() {
		return idCodeNull;
	}

	public void setIdCodeNull(Integer idCodeNull) {
		this.idCodeNull = idCodeNull;
	}

	public String getInvalidInput() {
		return invalidInput;
	}

	public void setInvalidInput(String invalidInput) {
		this.invalidInput = invalidInput;
	}

	public String getEmptyList() {
		return emptyList;
	}

	public void setEmptyList(String emptyList) {
		this.emptyList = emptyList;
	}

	public Integer getInvalidInputCode() {
		return invalidInputCode;
	}

	public void setInvalidInputCode(Integer invalidInputCode) {
		this.invalidInputCode = invalidInputCode;
	}

	public Integer getEmptyListCode() {
		return emptyListCode;
	}

	public void setEmptyListCode(Integer emptyListCode) {
		this.emptyListCode = emptyListCode;
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

	public String getNamingException() {
		return namingException;
	}

	public void setNamingException(String namingException) {
		this.namingException = namingException;
	}

	public Integer getNamingExceptionCode() {
		return namingExceptionCode;
	}

	public void setNamingExceptionCode(Integer namingExceptionCode) {
		this.namingExceptionCode = namingExceptionCode;
	}

	public String getServiceError() {
		return serviceError;
	}

	public void setServiceError(String serviceError) {
		this.serviceError = serviceError;
	}

	public Integer getServiceErrorCode() {
		return serviceErrorCode;
	}

	public void setServiceErrorCode(Integer serviceErrorCode) {
		this.serviceErrorCode = serviceErrorCode;
	}

	public String getMissingRequiredData() {
		return missingRequiredData;
	}

	public void setMissingRequiredData(String missingRequiredData) {
		this.missingRequiredData = missingRequiredData;
	}

	public Integer getMissingRequiredDataCode() {
		return missingRequiredDataCode;
	}

	public void setMissingRequiredDataCode(Integer missingRequiredDataCode) {
		this.missingRequiredDataCode = missingRequiredDataCode;
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

	public String getInsertState() {
		return insertState;
	}

	public void setInsertState(String insertState) {
		this.insertState = insertState;
	}

	public Integer getInsertStateCode() {
		return insertStateCode;
	}

	public void setInsertStateCode(Integer insertStateCode) {
		this.insertStateCode = insertStateCode;
	}

	public String getOnMemoryState() {
		return onMemoryState;
	}

	public void setOnMemoryState(String onMemoryState) {
		this.onMemoryState = onMemoryState;
	}

	public Integer getOnMemoryStateCode() {
		return onMemoryStateCode;
	}

	public void setOnMemoryStateCode(Integer onMemoryStateCode) {
		this.onMemoryStateCode = onMemoryStateCode;
	}

	public String getSuccessState() {
		return successState;
	}

	public void setSuccessState(String successState) {
		this.successState = successState;
	}

	public Integer getSuccessStateCode() {
		return successStateCode;
	}

	public void setSuccessStateCode(Integer successStateCode) {
		this.successStateCode = successStateCode;
	}

	public String getErrorState() {
		return errorState;
	}

	public void setErrorState(String errorState) {
		this.errorState = errorState;
	}

	public Integer getErrorStateCode() {
		return errorStateCode;
	}

	public void setErrorStateCode(Integer errorStateCode) {
		this.errorStateCode = errorStateCode;
	}

	public String getSuccessInformedState() {
		return successInformedState;
	}

	public void setSuccessInformedState(String successInformedState) {
		this.successInformedState = successInformedState;
	}

	public Integer getSuccessInformedStateCode() {
		return successInformedStateCode;
	}

	public void setSuccessInformedStateCode(Integer successInformedStateCode) {
		this.successInformedStateCode = successInformedStateCode;
	}

	public String getErrorInformedState() {
		return errorInformedState;
	}

	public void setErrorInformedState(String errorInformedState) {
		this.errorInformedState = errorInformedState;
	}

	public Integer getErrorInformedStateCode() {
		return errorInformedStateCode;
	}

	public void setErrorInformedStateCode(Integer errorInformedStateCode) {
		this.errorInformedStateCode = errorInformedStateCode;
	}

	public String getCancelState() {
		return cancelState;
	}

	public void setCancelState(String cancelState) {
		this.cancelState = cancelState;
	}

	public Integer getCancelStateCode() {
		return cancelStateCode;
	}

	public void setCancelStateCode(Integer cancelStateCode) {
		this.cancelStateCode = cancelStateCode;
	}

}
