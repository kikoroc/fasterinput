package net.haoxf.fasterinput.web.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Administrator on 2015/10/29 0029.
 */
@ComponentScan("net.haoxf.fasterinput")
@EnableAutoConfiguration
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

}
