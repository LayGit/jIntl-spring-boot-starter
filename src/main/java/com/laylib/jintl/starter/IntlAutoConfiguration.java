package com.laylib.jintl.starter;

import com.laylib.jintl.IntlSource;
import com.laylib.jintl.config.AbstractProviderConfig;
import com.laylib.jintl.config.IntlConfig;
import com.laylib.jintl.starter.exception.ProviderConfigNotFoundException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.nio.charset.Charset;

/**
 * SpringBoot Auto Configuration
 *
 * @author Lay
 * @since 1.0.0
 */
@Configuration
@ConditionalOnProperty(value = "intl.provider.type")
@EnableConfigurationProperties({ IntlProperties.class })
public class IntlAutoConfiguration {

    @Resource
    private Environment env;

    @Resource
    IntlProperties intlProperties;

    @Bean
    public IntlSource intlSource() throws ProviderConfigNotFoundException {
        IntlConfig intlConfig = new IntlConfig();
        intlConfig.setUseCodeAsDefaultMessage(intlProperties.isUseCodeAsDefaultMessage());
        intlConfig.setFallbackLanguageOny(intlProperties.isFallbackLanguageOny());

        // charset
        if (!StringUtils.isEmpty(intlProperties.getCharset())) {
            intlConfig.setCharset(Charset.forName(intlProperties.getCharset()));
        }

        // find provider config class
        AbstractProviderConfig providerConfig = ProviderConfigFinder.find("com.laylib.jintl.config", intlProperties.getProvider());
        intlConfig.setProviderConfig(providerConfig);
        return new IntlSource(intlConfig);
    }
}
