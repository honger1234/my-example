package honger1234.controller;

import com.honger1234.entities.CommonResult;
import com.honger1234.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zt
 * @date: 2020年3月26日
 */
@RestController
@Slf4j
@RequestMapping(value = "consumer")
public class OrderController {

//    public static final String url="http://localhost:8001";
    public static final String url="http://cloud-payment-consul-service";

    @Autowired
    private RestTemplate restTemplate;



    @GetMapping("/payment/consul")
    public Object getPayment( ){
        return restTemplate.getForObject(url+"/payment/consul",String.class);
    }

}
