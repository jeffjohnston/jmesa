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
package org.jmesa.view.html.toolbar;

import org.jmesa.view.html.HtmlBuilder;

/**
 * @since 2.0
 * @author Jeff Johnston
 */
public class MaxRowsItem extends AbstractItem {
	private int maxRows;
	private int[] increments = new int[0];
	
	public int getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

	public int[] getIncrements() {
		return increments;
	}

	public void setIncrements(int[] increments) {
		this.increments = increments;
	}

	@Override
	public String disabled() {
    	HtmlBuilder html = new HtmlBuilder();
        return html.toString();
	}

	@Override
	public String enabled() {
    	HtmlBuilder html = new HtmlBuilder();
    	
        html.select().name("maxRows");
        html.onchange(getAction());
        html.close();

        html.newline();
        html.tabs(4);

        int[] increments = getIncrements();
		for (int i = 0; i < increments.length; i++) {
			int increment = increments[i];
	        html.option().value(String.valueOf(increment));
	        if (increment == maxRows) {
	            html.selected();
	        }
	        html.close();
	        html.append(String.valueOf(increment));
	        html.optionEnd();
		}

        html.newline();
        html.tabs(4);
        html.selectEnd();    	
    	
        return html.toString();
	}
}
