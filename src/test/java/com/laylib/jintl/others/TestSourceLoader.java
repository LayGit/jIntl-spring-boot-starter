package com.laylib.jintl.others;

import com.laylib.jintl.config.LocalProviderConfig;
import com.laylib.jintl.loader.LocalSourceLoader;
import org.springframework.beans.BeanUtils;

/**
 * Test Source Loader
 *
 * @author Lay
 */
public class TestSourceLoader extends LocalSourceLoader {
    public TestSourceLoader(TestProviderConfig testProviderConfig) {
        super(copyConfig(testProviderConfig));
    }

    private static LocalProviderConfig copyConfig(TestProviderConfig testProviderConfig) {
        LocalProviderConfig localConfig = new LocalProviderConfig();
        BeanUtils.copyProperties(testProviderConfig, localConfig);
        return localConfig;
    }
}
