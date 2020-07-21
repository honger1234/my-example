package honger1234.service;

import org.springframework.stereotype.Component;

/**
 * @author: zt
 * @date: 2020/7/8
 */
@Component
public class PaymentFallbackService implements PaymentFeignService{
    public String paymentInfoOk(Integer id) {
        return "---------PaymentFallbackService fallback ---paymentInfoOk-----------------";
    }

    public Object paymentInfoTimeout(Integer id) {
        return "---------PaymentFallbackService fallback ---paymentInfoTimeout-----------------";
    }
}
