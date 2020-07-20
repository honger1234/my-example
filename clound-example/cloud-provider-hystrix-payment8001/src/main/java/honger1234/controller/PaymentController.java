package honger1234.controller;

import com.honger1234.entities.CommonResult;
import com.honger1234.entities.Payment;
import honger1234.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 控制层
 * @author: zt
 * @date: 2020年3月26日
 */
@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {

    @Value("${server.port}")
    private int port;

    @Autowired
    private IPaymentService paymentService;

   @GetMapping(value = "/hystrix/ok/{id}")
   public Object paymentInfoOk(@PathVariable("id") Integer id){
       String s = paymentService.paymentinfoOk(id);
       return s;
   }

    @GetMapping(value = "/hystrix/timeout/{id}")
    public Object paymentInfoTimeout(@PathVariable("id") Integer id){
        String s = paymentService.paymentTimeout(id);
        return s;
    }

    /**
     * 服务熔断
     * @param id
     * @return
     */
    @GetMapping(value = "/hystrix/Breaker/{id}")
    public Object paymentCircuitBreaker(@PathVariable("id") Integer id){
        String s = paymentService.paymentCircuitBreaker(id);
        return s;
    }
}
