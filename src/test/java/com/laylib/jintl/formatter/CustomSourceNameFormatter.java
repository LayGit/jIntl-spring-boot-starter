package com.laylib.jintl.formatter;

import java.util.Locale;

/**
 * Custom Source Name Formatter
 *
 * @author Lay
 */
public class CustomSourceNameFormatter extends AbstractSourceNameFormatter {

    public CustomSourceNameFormatter(String fileExtension) {
        super(fileExtension);
    }

    @Override
    public String format(String tag, Locale locale) {
        return String.format("%s.%s.%s", tag, locale.toLanguageTag(), fileExtension);
    }
}
