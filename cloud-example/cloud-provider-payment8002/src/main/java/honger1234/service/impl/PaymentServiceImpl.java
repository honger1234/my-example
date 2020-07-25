package honger1234.service.impl;

import com.honger1234.entities.Payment;
import honger1234.dao.IPaymentDao;
import honger1234.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 实现类
 * @author: zt
 * @date: 2020年3月26日
 */
@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private IPaymentDao paymentDao;

    public int create(Payment payment){
        int i = paymentDao.create(payment);
        return i;
    }

    public Payment getPaymentById(long id) {
        return paymentDao.getPaymentById(id);
    }


}
