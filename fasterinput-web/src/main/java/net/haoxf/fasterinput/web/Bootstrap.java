package net.haoxf.fasterinput.web;

import net.haoxf.fasterinput.dao.DataSourceConfiguration;
import net.haoxf.fasterinput.service.ServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-11-8
 */
@SpringBootApplication
public class Bootstrap extends SpringBootServletInitializer  {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(
                DataSourceConfiguration.class,
                ServiceConfiguration.class,
                Bootstrap.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{
                DataSourceConfiguration.class,
                ServiceConfiguration.class,
                Bootstrap.class
        }, args);
    }

}
