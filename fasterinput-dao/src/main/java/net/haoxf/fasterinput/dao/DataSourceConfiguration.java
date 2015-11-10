package net.haoxf.fasterinput.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Created by Administrator on 2015/11/6 0006.
 */
@Configuration
@ComponentScan("net.haoxf.fasterinput.dao")
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource() throws Exception {
        Properties props = new Properties();
        URL url = this.getClass().getResource("/druid.properties");
        InputStream is = null;
        DataSource dataSource = null;
        try {
             is = url.openStream();
            props.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(is!=null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) throws IOException {
        return new JdbcTemplate(dataSource, false);
    }
}
