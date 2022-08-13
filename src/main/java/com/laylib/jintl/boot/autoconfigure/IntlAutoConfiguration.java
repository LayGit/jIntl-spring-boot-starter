package com.laylib.jintl.boot.autoconfigure;

import com.laylib.jintl.IntlSource;
import com.laylib.jintl.boot.context.IntlSourceHolder;
import com.laylib.jintl.boot.utils.ProviderConfigFinder;
import com.laylib.jintl.config.BaseProviderConfig;
import com.laylib.jintl.config.IntlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * SpringBoot Auto Configuration
 *
 * @author Lay
 */
@Configuration
@EnableConfigurationProperties(IntlProperties.class)
public class IntlAutoConfiguration {

    private final IntlProperties intlProperties;

    private final Environment env;

    @Autowired
    public IntlAutoConfiguration(IntlProperties intlProperties, Environment env) {
        this.intlProperties = intlProperties;
        this.env = env;
    }

    @Bean
    public IntlSource intlSource() {
        // resolve intl config
        IntlConfig config = new IntlConfig();
        config.setUseCodeAsDefaultMessage(intlProperties.isUseCodeAsDefaultMessage());
        config.setFallbackLanguageOnly(intlProperties.isFallbackLanguageOnly());

        // resolve provider config
        BaseProviderConfig providerConfig = ProviderConfigFinder.find(env);
        IntlSource intlSource =  new IntlSource(config, providerConfig);

        // static cached to holder
        IntlSourceHolder.init(intlSource);
        return intlSource;
    }
}
