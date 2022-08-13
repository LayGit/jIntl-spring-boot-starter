package com.laylib.jintl.boot;

import com.laylib.jintl.boot.autoconfigure.IntlAutoConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * jIntl tests
 *
 * @author Lay
 * @since 1.0.0
 */
@SpringBootTest(classes = { IntlApplication.class })
@Import(IntlAutoConfiguration.class)
public class IntlApplicationTests {

    @BeforeEach
    public void init() {
        // set locale
        LocaleContextHolder.setLocale(Locale.ENGLISH);
    }
}
