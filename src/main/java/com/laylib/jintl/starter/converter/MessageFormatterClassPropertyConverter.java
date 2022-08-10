package com.laylib.jintl.starter.converter;

import com.laylib.jintl.formatter.MessageFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Message Formatter Class Property Converter
 *
 * @author Lay
 */
@Component
@ConfigurationPropertiesBinding
public class MessageFormatterClassPropertyConverter implements Converter<String, Class<MessageFormatter>> {

    private static final Logger logger = LoggerFactory.getLogger(MessageFormatterClassPropertyConverter.class);

    @SuppressWarnings("unchecked")
    @Override
    public Class<MessageFormatter> convert(String source) {
        try {
            return (Class<MessageFormatter>) Class.forName(source);
        } catch (ClassNotFoundException e) {
            logger.error("Cannot found Class of MessageFormatter", e);
        }
        return null;
    }
}
