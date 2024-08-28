package org.jmesa.view.editor;

import org.apache.commons.lang3.StringUtils;
import org.jmesa.util.ItemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * An editor to work with Java Time types, specifically anything that implements {@link TemporalAccessor}.
 * Send in a valid pattern for the column's type, and it will be formatted.
 *
 * @since 5.0.2
 * @author Mike Partridge
 */
public class JavaTimeCellEditor extends AbstractPatternCellEditor {

    private final Logger logger = LoggerFactory.getLogger(JavaTimeCellEditor.class);

    public JavaTimeCellEditor() {}

    public JavaTimeCellEditor(String pattern) {

        setPattern(pattern);
    }

    @Override
    public Object getValue(Object item, String property, int rowcount) {

        Object itemValue = null;

        try {
            itemValue = ItemUtils.getItemValue(item, property);
            if (itemValue == null || StringUtils.isBlank(String.valueOf(itemValue))) {
                return null;
            }

            TemporalAccessor temporalAccessor = (TemporalAccessor) itemValue;
            itemValue = DateTimeFormatter.ofPattern(getPattern()).format(temporalAccessor);
        } catch (Exception e) {
            logger.warn("Could not process java time editor with property {}", property, e);
        }

        return itemValue;
    }
}
