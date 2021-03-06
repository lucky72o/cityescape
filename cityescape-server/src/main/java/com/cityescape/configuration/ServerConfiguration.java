package com.cityescape.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.util.regex.Pattern;

/**
 * Base configuration for the bonus offer application, excluding all web related resources.
 *
 */

@Configuration
@ComponentScan(basePackages = {"com.com.cityescape"}, excludeFilters = {
        @ComponentScan.Filter(value = ServerConfiguration.WebExclusionFilter.class,
                type = FilterType.CUSTOM)
})
public class ServerConfiguration {
    public static class WebExclusionFilter extends RegexPatternTypeFilter {
        public WebExclusionFilter() {
            super(Pattern.compile("com.cityescape.web.*"));
        }
    }
}
