package org.jmesa.test;

import org.jmesa.limit.LimitUtils;
import org.springframework.mock.web.MockHttpServletRequest;

public class RequestParameters implements Parameters {
	private final MockHttpServletRequest request;

	public RequestParameters(MockHttpServletRequest request) {
		this.request = request;
	}

	public void addParameter(String parameter, Object value) {
		request.addParameter(parameter, LimitUtils.getValue(value));
	}
}
