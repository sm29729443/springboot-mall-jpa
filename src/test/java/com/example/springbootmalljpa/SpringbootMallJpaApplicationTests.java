package com.example.springbootmalljpa;

import com.example.springbootmalljpa.dao.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootMallJpaApplicationTests {
    @Autowired
    private ProductDao productDao;

    @Test
    void contextLoads() {
    }
    
    @Test
    public void test1(){
        System.out.println(productDao.testProduct());
    }

}
