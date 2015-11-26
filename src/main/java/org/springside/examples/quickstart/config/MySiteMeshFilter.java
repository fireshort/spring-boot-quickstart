package org.springside.examples.quickstart.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * Created by Ivan on 2015/11/26.
 */
public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/WEB-INF/layouts/default.jsp");
        builder.addExcludedPath("/static/*");
        builder.addExcludedPath("/api/*");
    }
}
