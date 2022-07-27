package com.laylib.jintl.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * intl config
 *
 * @author Lay
 * @since 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "intl")
public class IntlProperties {
    /**
     * is return code when message is null
     */
    private boolean useCodeAsDefaultMessage;

    /**
     * is fallback to Locale without country
     */
    private boolean fallbackLanguageOny;

    /**
     * source charset
     */
    private String charset;

    /**
     * provider
     */
    private Provider provider;

    public static class Provider {
        /**
         * provider type
         */
        private String type;

        /**
         * provider config
         */
        private Map<String, Object> config;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Map<String, Object> getConfig() {
            return config;
        }

        public void setConfig(Map<String, Object> config) {
            this.config = config;
        }
    }
}
