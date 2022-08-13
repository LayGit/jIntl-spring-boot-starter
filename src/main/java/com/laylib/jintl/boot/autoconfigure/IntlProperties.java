package com.laylib.jintl.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.util.Map;

/**
 * jIntl config properties
 *
 * @author Lay
 */
@ConfigurationProperties(prefix = "intl")
@ConfigurationPropertiesScan
public class IntlProperties {
    /**
     * is return code when message is null
     */
    private boolean useCodeAsDefaultMessage;

    /**
     * is fallback to Locale without country
     */
    private boolean fallbackLanguageOnly;

    /**
     * provider
     */
    private Provider provider;

    public boolean isUseCodeAsDefaultMessage() {
        return useCodeAsDefaultMessage;
    }

    public void setUseCodeAsDefaultMessage(boolean useCodeAsDefaultMessage) {
        this.useCodeAsDefaultMessage = useCodeAsDefaultMessage;
    }

    public boolean isFallbackLanguageOnly() {
        return fallbackLanguageOnly;
    }

    public void setFallbackLanguageOnly(boolean fallbackLanguageOnly) {
        this.fallbackLanguageOnly = fallbackLanguageOnly;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

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
