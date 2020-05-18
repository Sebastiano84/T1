package com.seb.anime;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by efreseb on 06/04/2017.
 */
@SpringBootTest(classes = Main.class)
public class AbstractSpringMVCTest {
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
    }

}
