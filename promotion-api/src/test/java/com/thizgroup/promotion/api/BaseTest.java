package com.thizgroup.promotion.api;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/** @Author: fate @Date: 2019/5/16 15:37 @Version 1.0 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PromotionApplication.class)
@ActiveProfiles("dev")
public class BaseTest {

  @Autowired protected WebApplicationContext context;
  protected MockMvc mockMvc;

  protected final String MOBILE = "15788887777";
  protected final String BASE_URL = "/binding/manager/";
  protected static String TOKEN = null;

  @Before
  public void init() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }
}
