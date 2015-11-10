package net.haoxf.fasterinput.web;

import net.haoxf.fasterinput.web.interceptors.TokenInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;

/**
 * Created by Administrator on 2015/11/9 0009.
 */
@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

    protected Log logger = LogFactory.getLog(getClass());

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("------------------add interceptors.");
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/api/account/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        logger.info("------------------add resources handler.");
        registry.addResourceHandler("/resources/static/**")
                .addResourceLocations("/resources/static/")
                .setCachePeriod(7*24*3600);
    }

    @Bean
    public Filter applicationContextFilter(ApplicationContext context){
        logger.info("------------------load characterEncoding filter.");
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

}
