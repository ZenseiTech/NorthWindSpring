package com.zenseitech.northwind.orderdetail;

import com.zenseitech.northwind.configuration.DataSourceConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = DataSourceConfiguration.class)
@ActiveProfiles(DataSourceConfiguration.PROFILE_SQLITE)
public class OrderDetailRepositorySQLiteTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    private OrderDetailServiceDefault orderDetailServiceDefault;

    @Before
    public void setup() {
        orderDetailServiceDefault = new OrderDetailServiceDefault(orderDetailRepository);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(new BigDecimal(10248));
        assertThat(orderDetailList.size()).isEqualTo(3);

        for (OrderDetail orderDetail : orderDetailList) {
            System.out.println(orderDetail.toString());
        }
    }

}
