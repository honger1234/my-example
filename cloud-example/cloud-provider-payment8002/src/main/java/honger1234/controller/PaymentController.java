package honger1234.controller;

import com.honger1234.entities.CommonResult;
import com.honger1234.entities.Payment;
import honger1234.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        log.info("*****插入结果："+i);
        if (i<1){
            return new CommonResult(444,"插入失败serverPort:"+port,null);
        }
        return new CommonResult(200,"插入成功serverPort:"+port,i);
    }

    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment==null){
            return new CommonResult(444,"没有查到数据serverPort:"+port,payment);
        }
        return new CommonResult(200,"查询成功serverPort:"+port,payment);
    }
}
