package com.laylib.jintl.starter;

import com.laylib.jintl.config.AbstractProviderConfig;
import com.laylib.jintl.starter.exception.ProviderConfigNotFoundException;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * provider config finder
 *
 * @author Lay
 * @since 1.0.0
 */
public class ProviderConfigFinder {

    @SuppressWarnings("unchecked")
    public static AbstractProviderConfig find(String packageName, IntlProperties.Provider provider) throws ProviderConfigNotFoundException {
        AbstractProviderConfig instance;
        try {
            List<Class<?>> lstClass = getClasspath(packageName);
            for (Class<?> cls : lstClass) {
                if (AbstractProviderConfig.class.isAssignableFrom(cls) && !Modifier.isAbstract(cls.getModifiers())) {
                    instance = (AbstractProviderConfig) cls.getDeclaredConstructor().newInstance();
                    if (provider.getType().equals(instance.getType())) {
                        // fill config props
                        Map<String, Object> config = provider.getConfig();
                        if (config != null && config.size() > 0) {
                            BeanUtils.populate(instance, config);
                        }

                        return instance;
                    }
                }
            }
        } catch (Exception e) {
            throw new ProviderConfigNotFoundException(provider.getType(), e);
        }

        throw new ProviderConfigNotFoundException(provider.getType(), new Throwable("no class defined"));
    }

    public static List<Class<?>> getClasspath(String packagePath) throws Exception {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
        // 加载系统所有类资源
        Resource[] resources = resourcePatternResolver.getResources("classpath*:" + packagePath.replaceAll("[.]", "/") + "/**/*.class");
        List<Class<?>> list = new ArrayList<Class<?>>();
        // 把每一个class文件找出来
        for (Resource r : resources) {
            MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(r);
            Class<?> clazz = ClassUtils.forName(metadataReader.getClassMetadata().getClassName(), null);
            list.add(clazz);
        }
        return list;
    }
}
