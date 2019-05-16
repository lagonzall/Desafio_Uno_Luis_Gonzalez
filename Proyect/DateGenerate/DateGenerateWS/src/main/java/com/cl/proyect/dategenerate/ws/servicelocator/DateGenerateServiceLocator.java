package com.cl.proyect.dategenerate.ws.servicelocator;

import javax.naming.Context;
import javax.naming.NamingException;

import com.cl.proyect.dategenerate.remote.DateGenerateBeanRemote;
import com.cl.proyect.dategenerate.ws.utils.WSConfigurationFile;

public class DateGenerateServiceLocator {

	private static DateGenerateServiceLocator clienteServiceLocator;

	private DateGenerateServiceLocator() {
		throw new IllegalStateException("ConfigurationServiceLocator class");
	}

	public static DateGenerateServiceLocator getInstance() {
		if (clienteServiceLocator == null) {
			clienteServiceLocator = new DateGenerateServiceLocator();
		}
		return clienteServiceLocator;
	}

	public static DateGenerateBeanRemote doLookup() throws NamingException, Exception {

		String LOOKUP_STRING = WSConfigurationFile.instance().getJndiDateGenerate();
		String url = WSConfigurationFile.instance().getUrlDateGenerateRemote();
		Context context = null;
		DateGenerateBeanRemote bean = null;

		context = ClientUtility.getInitialContext(url);
		bean = (DateGenerateBeanRemote) context.lookup(LOOKUP_STRING);

		return bean;
	}

}
