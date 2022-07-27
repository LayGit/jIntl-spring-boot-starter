package com.laylib.jintl.starter;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

/**
 * jIntl tests
 *
 * @author Lay
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IntlApplication.class })
@Import(IntlAutoConfiguration.class)
public class IntlApplicationTests {

    @Before
    public void init() {
        // set locale
        LocaleContextHolder.setLocale(Locale.ENGLISH);
    }
}
