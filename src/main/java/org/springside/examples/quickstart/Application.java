package org.springside.examples.quickstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springside.examples.quickstart.config.MySiteMeshFilter;

import javax.servlet.Filter;
import javax.validation.Validator;
import java.io.FileNotFoundException;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
//@Configuration定义配置类
//you can use @ComponentScan to automatically pickup all Spring components, including @Configuration classes.
public class Application {

    public static void main(String[] args) throws FileNotFoundException {
        new SpringApplication(Application.class).run(args);
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/WEB-INF/views/error/401.jsp");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/WEB-INF/views/error/404.jsp");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/WEB-INF/views/error/500.jsp");

                container.addErrorPages(error401Page, error404Page, error500Page);
            }
        };
    }

    @Bean
    //JSR303 Validator定义
    public Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

//    @Bean
//    public FilterRegistrationBean sitemeshFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new SiteMeshFilter());
//        registration.addInitParameter("sitemesh.configfile","classpath:sitemesh/sitemesh.xml");
//        registration.addUrlPatterns("/*");
//        registration.setOrder(Ordered.LOWEST_PRECEDENCE);
//        return registration;
//    }

    @Bean
    public Filter sitemeshFilter() {
        return new MySiteMeshFilter();
    }

}