package com.cl.proyect.dategenerate.ws.servicelocator;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.cl.proyect.dategenerate.ws.interceptor.Audit;
import com.cl.proyect.dategenerate.ws.interceptor.Logging;


@Audit
@Logging
public class ClientUtility {

	private static Context initialContext;

	private static final String JNP_INTERFACES = "org.jboss.ejb.client.naming";												  
	
	@Audit
	@Logging
	public static Context getInitialContext(String providerURL) throws NamingException {

		if (initialContext == null) {
			Properties properties = new Properties();

			properties.put(Context.URL_PKG_PREFIXES, JNP_INTERFACES);
			properties.put(Context.PROVIDER_URL, providerURL);
			properties.put("jboss.naming.client.ejb.context", true);

			initialContext = new InitialContext(properties);
		}
		return initialContext;
	}
}

