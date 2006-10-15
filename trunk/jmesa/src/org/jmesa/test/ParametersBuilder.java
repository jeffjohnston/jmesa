/*
 * Copyright 2004 original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jmesa.test;

import java.util.ArrayList;
import java.util.List;

import org.jmesa.limit.Action;
import org.jmesa.limit.Order;

/**
 * Build up the parameters that is used by the LimitFactory.
 * 
 * @author Jeff Johnston
 */
public class ParametersBuilder {
	private final String prefixId;
	private final Parameters parameters;

	public ParametersBuilder(String id, Parameters parameters) {
		this.prefixId = id + "_";
		this.parameters = parameters;
	}

	public void setPage(int page) {
		String key = prefixId + Action.PAGE;
		parameters.addParameter(key, new Integer[] { page });
	}

	public void setMaxRows(int maxRows) {
		String key = prefixId + Action.MAX_ROWS;
		parameters.addParameter(key, maxRows);
	}

	public void addFilter(String property, String value) {
		String key = prefixId + Action.FILTER + property;
		List<String> filterList = new ArrayList<String>();
		filterList.add(value);
		parameters.addParameter(key, filterList);
	}

	public void setClearFilter() {
		String key = prefixId + Action.CLEAR.getCode();
		parameters.addParameter(key, "true");
	}

	public void addSort(String property, Order order) {
		String key = prefixId + Action.SORT + property;
		parameters.addParameter(key, new String[] { order.getCode() });
	}
}
