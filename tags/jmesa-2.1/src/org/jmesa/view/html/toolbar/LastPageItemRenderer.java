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

import org.jmesa.core.CoreContext;
import org.jmesa.limit.Limit;
import org.jmesa.view.html.HtmlUtils;

/**
 * @since 2.0
 * @author Jeff Johnston
 */
public class LastPageItemRenderer extends AbstractItemRenderer {
    public LastPageItemRenderer(ToolbarItem item, CoreContext coreContext) {
        setToolbarItem(item);
        setCoreContext(coreContext);
    }

    public String render() {
        Limit limit = getCoreContext().getLimit();
        int page = limit.getRowSelect().getPage();
        int totalPages = HtmlUtils.totalPages(getCoreContext());

        StringBuilder action = new StringBuilder("javascript:");
        action.append("setPageToLimit('" + limit.getId() + "','" + totalPages + "');" + getOnInvokeAction() + "('" + limit.getId() + "')");
        ToolbarItem item = getToolbarItem();
        item.setAction(action.toString());

        if (!HtmlUtils.isLastPageEnabled(page, totalPages)) {
            return item.disabled();
        }

        return item.enabled();
    }
}
