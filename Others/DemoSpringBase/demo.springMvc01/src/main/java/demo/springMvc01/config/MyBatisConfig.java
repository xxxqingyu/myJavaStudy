package demo.springMvc01.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan("demo.springMvc01.dao.mapper")
public class MyBatisConfig {
	@Value("${jdbc.driver}")
    private String jdbcDriver;
 
    @Value("${db.url}")
    private String dbUrl;
 
    @Value("${db.username}")
    private String username;
 
    @Value("${db.password}")
    private String password;
 
    @Value("${db.maxtotal}")
    private Integer maxTotal;
 
    @Value("${db.minidle}")
    private Integer minIdle;
 
    @Value("${db.maxidle}")
    private Integer maxIdle;
    		

    
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
       // logger.info("mysql url:"+dbUrl);
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxTotal(maxTotal);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxIdle(maxIdle);
        return dataSource;
    }
 
    @Bean("transactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
 
    @Bean
   // @Scope
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        //sessionFactory.setl
        //Resouces
        //sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperLocations = resolver.getResources("classpath:mapper/*Mapper.xml");
        
        sessionFactory.setMapperLocations(mapperLocations);
        return sessionFactory.getObject();
    }
	
    public MapperScannerConfigurer mapperScannerConfigurer(){
    	MapperScannerConfigurer config=new 	MapperScannerConfigurer();
    	config.setBasePackage("demo.springMvc01.dao.mapper");
    	return config;
    	
    }
}
