package com.laylib.jintl.boot.converter;

import com.laylib.jintl.formatter.SourceNameFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Source Name Formatter Class Property Converter
 *
 * @author Lay
 */
@Component
@ConfigurationPropertiesBinding
public class SourceNameFormatterClassPropertyConverter implements Converter<String, Class<SourceNameFormatter>> {

    private static final Logger logger = LoggerFactory.getLogger(MessageFormatterClassPropertyConverter.class);

    @SuppressWarnings("unchecked")
    @Override
    public Class<SourceNameFormatter> convert(String source) {
        try {
            return (Class<SourceNameFormatter>) Class.forName(source);
        } catch (ClassNotFoundException e) {
            logger.error("Cannot found Class of MessageFormatter", e);
        }
        return null;
    }
}
