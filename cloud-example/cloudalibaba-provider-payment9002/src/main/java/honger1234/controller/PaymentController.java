package honger1234.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zt
 * @date: 2020/7/22
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/nacos/payment/get/{id}")
    public String getPayment(@PathVariable("id") Integer id){
        return "这是nacos服务提供者，id:"+id+"port:"+port;
    }
}
