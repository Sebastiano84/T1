package com.seb.anime;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ApplicationStartListener extends PropertySourcesPlaceholderConfigurer{
    private static final String ENVIRONMENT_PROPERTIES = "environmentProperties";

    @Override
    public void postProcessBeanFactory(
            final ConfigurableListableBeanFactory beanFactory) {

        super.postProcessBeanFactory(beanFactory);

        final AbstractEnvironment propertySources =
                (AbstractEnvironment) super.getAppliedPropertySources().get(ENVIRONMENT_PROPERTIES).getSource();

        propertySources.getPropertySources().forEach((PropertySource propertySource) -> {
            if (propertySource.getSource() instanceof Map) {
                System.out.println("#######" + propertySource.getName() + "#######");

                final Map<String, String> properties = mapValueAsString( propertySource.getSource());
                System.out.println(properties);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> mapValueAsString(Object object) {
        final Map<String, Object> map = (Map<String, Object>) object;
        return map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> toString(entry.getValue())));
    }

    private String toString(final Object object) {

        return Optional.ofNullable(object).map(Object::toString).orElse(null);
    }
}
