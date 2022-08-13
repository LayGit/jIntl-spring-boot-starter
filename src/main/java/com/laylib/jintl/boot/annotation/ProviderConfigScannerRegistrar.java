package com.laylib.jintl.boot.annotation;

import com.laylib.jintl.boot.utils.ProviderConfigFinder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Message Config Scanner Registrar
 *
 * @author Lay
 */
public class ProviderConfigScannerRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes providerScanAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(ProviderConfigScan.class.getName()));
        if (providerScanAttrs != null) {
            String[] annoPackages = (String[]) providerScanAttrs.get("basePackages");
            if (annoPackages != null && annoPackages.length > 0) {
                ProviderConfigFinder.addPackages(Arrays.stream(annoPackages).collect(Collectors.toSet()));
            }
        }
    }
}
