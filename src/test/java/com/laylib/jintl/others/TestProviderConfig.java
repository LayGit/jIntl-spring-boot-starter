package com.laylib.jintl.others;

import com.laylib.jintl.annotation.ProviderType;
import com.laylib.jintl.config.BaseProviderConfig;
import com.laylib.jintl.loader.LocalSourceLoader;
import com.laylib.jintl.loader.SourceLoader;
import com.laylib.jintl.provider.DefaultMessageProvider;
import com.laylib.jintl.provider.MessageProvider;

/**
 * Test Provider Config
 *
 * @author Lay
 */
@ProviderType("test")
public class TestProviderConfig extends BaseProviderConfig {
    @Override
    public Class<? extends MessageProvider> getProviderClass() {
        return DefaultMessageProvider.class;
    }

    @Override
    public Class<? extends SourceLoader> getLoaderClass() {
        return LocalSourceLoader.class;
    }
}
