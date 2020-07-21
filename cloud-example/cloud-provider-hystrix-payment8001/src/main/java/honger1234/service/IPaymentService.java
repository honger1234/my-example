package honger1234.service;

import com.honger1234.entities.Payment;

/**
 * @Description: 实现类接口
 * @author: zt
 * @date: 2020年3月26日
 */
public interface IPaymentService {

    String paymentinfoOk(Integer id);

    String paymentTimeout(Integer id);

    /**
     * 服务熔断
     * @param id
     * @return
     */
    String paymentCircuitBreaker(Integer id);
}
