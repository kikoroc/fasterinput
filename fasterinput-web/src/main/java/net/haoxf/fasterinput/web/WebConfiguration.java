package net.haoxf.fasterinput.web;

import net.haoxf.fasterinput.dao.DataSourceConfiguration;
import net.haoxf.fasterinput.service.ServiceConfiguration;
import net.haoxf.fasterinput.web.interceptors.TokenInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-11-5
 */
@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan("net.haoxf.fasterinput.web")
public class WebConfiguration extends WebMvcConfigurerAdapter implements WebApplicationInitializer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/api/account/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/")
                .setCachePeriod(7*24*3600);
    }

    /**
     * 替代web.xml
     * @param servletContext
     */
    @Override
    public void onStartup(ServletContext servletContext){
        servletContext.addListener(org.springframework.web.util.IntrospectorCleanupListener.class);

        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(
                EnumSet.allOf(DispatcherType.class),
                false,
                "/*"
        );

        ServletRegistration.Dynamic servlet = servletContext.addServlet("fasterinput", DispatcherServlet.class);
        servlet.setLoadOnStartup(1);
        servlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
        servlet.addMapping("/");
    }

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{
                DataSourceConfiguration.class,
                ServiceConfiguration.class,
                WebConfiguration.class
        }, args);
    }

}
