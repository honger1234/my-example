package honger1234.controller;

import com.honger1234.entities.CommonResult;
import com.honger1234.entities.Payment;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import honger1234.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zt
 * @date: 2020年3月26日
 */
@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "globalFallback")
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/hystrix/ok/{id}")
    public Object paymentInfoOk(@PathVariable("id") Integer id){
        String o = paymentFeignService.paymentInfoOk(id);
        return o;
    }
    @GetMapping(value = "/consumer/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "timeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value ="1500" )
//    })
//    @HystrixCommand
    public Object paymentInfoTimeout(@PathVariable("id") Integer id){
        return paymentFeignService.paymentInfoTimeout(id);
    }

    public String timeoutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"系统繁忙或者出错，请稍后再试"+"\t"+"hystrix超时处理";
    }

    public String globalFallback(){
        return "globle全局异常处理,请稍等！";
    }

}
