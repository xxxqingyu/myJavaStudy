package dubbo_demo.dubbo_demo_provider3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

@Configuration
@DubboComponentScan
public class DubboConfiguration {

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("provider-test");
        
        return applicationConfig;
    }
    
    @Bean
    public ProtocolConfig  protocolConfig(){
    	// 服务提供者协议配置
    	ProtocolConfig protocol = new ProtocolConfig();
    	protocol.setName("dubbo");
    	protocol.setPort(20882);
    	protocol.setServer("netty4");
    	//protocol.setThreads(200);
    	return protocol;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        registryConfig.setClient("curator");
        return registryConfig;
    }
}