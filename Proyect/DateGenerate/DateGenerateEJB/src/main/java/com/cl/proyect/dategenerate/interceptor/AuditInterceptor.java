package com.cl.proyect.dategenerate.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * AuditInterceptor binds to {@link @Audit} annotation, so methods or beans
 * which @Audit annotation is applied to, will be intercepted.
 *
 * @author ievgen.shulga
 */
@Interceptor
@Audit
public class AuditInterceptor {

	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {
		String methodName = ic.getMethod().getName();
		if (methodName.equals("create")) {
		} else if (methodName.equals("getList")) {
		}
		return ic.proceed();
	}
}
