package demo.springMvc01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import demo.springMvc01.service.UserSerivceImpl;
import demo.springMvc01.service.UserService;

@Configuration
@ComponentScan({"demo.springMvc01","demo.springMvc01.web"})
@PropertySource(value={"classpath:jdbc.properties"})
@EnableTransactionManagement

//@Import({CDPlayerConfig.class})
//@ImportResource("classpath:beans.xml")
public class RootConfig {

}
