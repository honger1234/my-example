package honger1234;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: zt
 * @date: 2020/7/22
 */
@SpringBootApplication
@EnableEurekaClient
public class SteamMQConsumer8803 {
    public static void main(String[] args){
        SpringApplication.run(SteamMQConsumer8803.class,args);
    }
}
