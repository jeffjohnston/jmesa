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
package org.jmesa.view.html.editor;

import org.jmesa.limit.Limit;
import org.jmesa.limit.Order;
import org.jmesa.limit.Sort;
import org.jmesa.view.editor.AbstractHeaderEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.HtmlConstants;
import org.jmesa.view.html.HtmlUtils;
import org.jmesa.view.html.component.HtmlColumn;

/**
 * @since 2.2
 * @author Jeff Johnston
 */
public class HtmlHeaderEditor extends AbstractHeaderEditor {
    @Override
    public HtmlColumn getColumn() {
        return (HtmlColumn) super.getColumn();
    }

    public Object getValue() {
        HtmlBuilder html = new HtmlBuilder();

        Limit limit = getCoreContext().getLimit();
        HtmlColumn column = getColumn();

        if (column.isSortable()) {
            Sort sort = limit.getSortSet().getSort(column.getProperty());

            html.onmouseover("this.style.cursor='pointer'");
            html.onmouseout("this.style.cursor='default'");

            if (sort != null) {
                Order nextOrder = nextSortOrder(sort.getOrder(), column);
                html.append(onclick(nextOrder, column, limit));
            } else {
                Order[] sortOrder = column.getSortOrder();
                if (sortOrder[0] == Order.NONE) {
                    Order nextOrder = nextSortOrder(sortOrder[0], column);
                    html.append(onclick(nextOrder, column, limit));
                } else {
                    Order nextOrder = sortOrder[0];
                    html.append(onclick(nextOrder, column, limit));
                }
            }
        }

        html.close();
        html.append(column.getTitle());

        if (column.isSortable()) {
            String imagesPath = HtmlUtils.imagesPath(getWebContext(), getCoreContext());
            Sort sort = limit.getSortSet().getSort(column.getProperty());
            if (sort != null) {
                if (sort.getOrder() == Order.ASC) {
                    html.nbsp();
                    html.img();
                    html.src(imagesPath + getCoreContext().getPreference(HtmlConstants.SORT_ASC_IMAGE));
                    html.style("border:0");
                    html.alt("Arrow");
                    html.end();
                } else if (sort.getOrder() == Order.DESC) {
                    html.nbsp();
                    html.img();
                    html.src(imagesPath + getCoreContext().getPreference(HtmlConstants.SORT_DESC_IMAGE));
                    html.style("border:0");
                    html.alt("Arrow");
                    html.end();
                }
            }
        }

        return html.toString();
    }

    protected String onclick(Order currentOrder, HtmlColumn column, Limit limit) {
        HtmlBuilder html = new HtmlBuilder();

        int position = column.getRow().getColumns().indexOf(column);

        if (currentOrder == Order.NONE) {
            html.onclick("removeSortFromLimit('" + limit.getId() + "','" + column.getProperty() + "');onInvokeAction('" + limit.getId() + "')");
        } else {
            html.onclick("addSortToLimit('" + limit.getId() + "','" + position + "','" + column.getProperty() + "','" + currentOrder.toParam()
                    + "');onInvokeAction('" + limit.getId() + "')");
        }

        return html.toString();
    }

    /**
     * @param currentOrder The current sort Order.
     * @param column The current column.
     * @return The next sort order in the array.
     */
    protected Order nextSortOrder(Order currentOrder, HtmlColumn column) {
        Order[] sortOrder = column.getSortOrder();

        for (int i = 0; i < sortOrder.length; i++) {
            Order order = sortOrder[i];
            if (order == currentOrder) {
                if (i + 1 == sortOrder.length) {
                    return sortOrder[0];
                }

                return sortOrder[i + 1];
            }
        }

        return Order.NONE;
    }

}
