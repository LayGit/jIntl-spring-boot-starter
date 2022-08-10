package com.laylib.jintl.others;

import com.laylib.jintl.config.BaseProviderConfig;
import com.laylib.jintl.provider.AbstractMessageProvider;
import com.laylib.jintl.provider.DefaultMessageProvider;

/**
 * Test Provider Config
 *
 * @author Lay
 */
public class TestProviderConfig extends BaseProviderConfig {
    @Override
    public String getType() {
        return "test";
    }

    @Override
    public Class<? extends AbstractMessageProvider<? extends BaseProviderConfig>> getProviderClass() {
        return (Class<? extends AbstractMessageProvider<? extends BaseProviderConfig>>) new DefaultMessageProvider<>(new TestProviderConfig()).getClass();
    }
}
