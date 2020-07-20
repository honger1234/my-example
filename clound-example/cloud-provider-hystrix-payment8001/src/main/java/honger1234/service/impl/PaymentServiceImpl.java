package honger1234.service.impl;

import cn.hutool.core.util.IdUtil;
import com.honger1234.entities.Payment;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import honger1234.dao.IPaymentDao;
import honger1234.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 实现类
 * @author: zt
 * @date: 2020年3月26日
 */
@Service
public class PaymentServiceImpl implements IPaymentService {



    public String paymentinfoOk(Integer id) {
        return "线程池："+Thread.currentThread().getName()+"OK"+id+"\t"+"哈哈哈";
    }

    @HystrixCommand(fallbackMethod = "timeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value ="5000" )
    })
    public String paymentTimeout(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"timeout"+id+"\t"+"哈哈哈";
    }

    public String timeoutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"系统繁忙或者出错，请稍后再试"+"\t"+"hystrix超时处理";
    }

    //---服务的熔断
    @HystrixCommand(
            fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸

    }
    )
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id<0) {
            throw new RuntimeException("******id不能为负数");
        }
        String simpleUUID = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t" + "成功调用，流水号是：" + simpleUUID;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id不能为负数，请稍后再试............"+id;
    }
}
