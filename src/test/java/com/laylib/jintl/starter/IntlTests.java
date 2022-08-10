package com.laylib.jintl.starter;

import com.laylib.jintl.IntlSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.annotation.Resource;

/**
 * test cases
 *
 * @author Lay
 * @since 1.0.0
 */
public class IntlTests extends IntlApplicationTests {

    @Resource
    private IntlSource intlSource;

    @Test
    public void test() {
        String code = "http.serverError";
        String msg = intlSource.getMessage(code, LocaleContextHolder.getLocale());
        Assertions.assertEquals("Internal Server Error", msg);
    }
}
