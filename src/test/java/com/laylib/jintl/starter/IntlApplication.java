package com.laylib.jintl.starter;

import com.laylib.jintl.annotation.ProviderType;
import com.laylib.jintl.others.TestProviderConfig;
import com.laylib.jintl.starter.annotation.MessageConfigScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot Application
 *
 * @author Lay
 */
@SpringBootApplication
@MessageConfigScan(basePackages = "com.laylib.jintl.others")
public class IntlApplication {
    public static void main(String[] args) {
        System.out.println(TestProviderConfig.class.getAnnotation(ProviderType.class).value());
        SpringApplication.run(IntlApplication.class);
    }
}
