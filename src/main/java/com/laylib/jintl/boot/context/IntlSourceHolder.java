package com.laylib.jintl.boot.context;

import com.laylib.jintl.IntlSource;

/**
 * Intl Source Holder
 *
 * @author Lay
 */
public class IntlSourceHolder {
    /**
     * intl source instance
     */
    private static IntlSource instance;

    public static void init(IntlSource intlSource) {
        if (instance == null) {
            instance = intlSource;
        }
    }

    public static IntlSource get() {
        return instance;
    }
}
