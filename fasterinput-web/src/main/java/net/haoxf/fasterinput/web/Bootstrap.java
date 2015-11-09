package net.haoxf.fasterinput.web;

import net.haoxf.fasterinput.dao.DataSourceConfiguration;
import net.haoxf.fasterinput.service.ServiceConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    protected static Log logger = LogFactory.getLog(Bootstrap.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        logger.info("--------------------------->load spring application context.");
        return application.sources(
                DataSourceConfiguration.class,
                ServiceConfiguration.class,
                Bootstrap.class);
    }

    public static void main(String[] args) {
        logger.info("--------------------------->Bootstrap executing...");
        SpringApplication.run(new Object[]{
                DataSourceConfiguration.class,
                ServiceConfiguration.class,
                Bootstrap.class
        }, args);
    }

}
