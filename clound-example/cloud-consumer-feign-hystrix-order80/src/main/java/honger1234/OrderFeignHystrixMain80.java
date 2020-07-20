package honger1234;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: 启动类
 * @author: zt
 * @date: 2020年3月26日
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderFeignHystrixMain80 {
    public static void main(String[] args){
        SpringApplication.run(OrderFeignHystrixMain80.class,args);
    }
}
