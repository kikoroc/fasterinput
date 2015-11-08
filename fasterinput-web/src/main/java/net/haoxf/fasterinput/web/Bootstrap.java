package net.haoxf.fasterinput.web;

import net.haoxf.fasterinput.dao.DataSourceConfiguration;
import net.haoxf.fasterinput.service.ServiceConfiguration;
import net.haoxf.fasterinput.web.config.DispatchServletConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-11-8
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("net.haoxf.fasterinput.web")
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{
                DataSourceConfiguration.class,
                ServiceConfiguration.class,
                Bootstrap.class
        }, args);
    }

}
