package honger1234.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zt
 * @date: 2020/7/7
 */
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level FeignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
