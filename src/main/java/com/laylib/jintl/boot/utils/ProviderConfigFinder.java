package com.laylib.jintl.boot.utils;

import com.laylib.jintl.annotation.ProviderType;
import com.laylib.jintl.boot.exception.ProviderConfigNotFoundException;
import com.laylib.jintl.config.BaseProviderConfig;
import com.laylib.jintl.config.LocalProviderConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.util.*;

/**
 * Message Provider Finder
 *
 * @author Lay
 */
public class ProviderConfigFinder {
    private static final Set<String> scanPackages = new HashSet<>();

    static {
        scanPackages.add(BaseProviderConfig.class.getPackageName());
    }

    public static void addPackages(Set<String> packages) {
        scanPackages.addAll(packages);
    }

    public static BaseProviderConfig find(Environment env) {
        String type = env.getProperty("intl.provider.type");
        try {
            if (type == null) {
                return new LocalProviderConfig();
            }

            List<BeanDefinition> beans = getBeanDefinitions();

            for (BeanDefinition bean : beans) {
                ProviderType providerType = AnnotationUtils.findAnnotation(Class.forName(bean.getBeanClassName()), ProviderType.class);
                if (providerType != null && type.equals(providerType.value())) {
                    Binder binder = Binder.get(env);
                    return (BaseProviderConfig) binder.bind("intl.provider", Class.forName(bean.getBeanClassName())).get();
                }
            }
        } catch (Exception e) {
            throw new ProviderConfigNotFoundException(type, e);
        }

        throw new ProviderConfigNotFoundException(type, null);
    }

    public static List<BeanDefinition> getBeanDefinitions()  {
        LinkedHashSet<BeanDefinition> candidateComponents = new LinkedHashSet<>();
        // scan provider classes
        ClassPathScanningCandidateComponentProvider pathScanningCandidateComponentProvider = new ClassPathScanningCandidateComponentProvider(false);
        pathScanningCandidateComponentProvider.addIncludeFilter(new ProviderConfigFilter());
        for (String basePackage : scanPackages) {
            candidateComponents.addAll(pathScanningCandidateComponentProvider.findCandidateComponents(basePackage));
        }

        return new ArrayList<>(candidateComponents);
    }


    static class ProviderConfigFilter implements TypeFilter {
        @Override
        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) {
            ClassMetadata metadata = metadataReader.getClassMetadata();
            return BaseProviderConfig.class.getName().equals(metadata.getSuperClassName());
        }
    }
}
