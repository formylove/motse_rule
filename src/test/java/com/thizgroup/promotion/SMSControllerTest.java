package com.thizgroup.promotion;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author : Sarah Xu
 * @date : 2019-05-29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BindingRecorderApplication.class)
@EntityScan("com.thizgroup.promotion.*")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SMSControllerTest {
  @Autowired private WebApplicationContext wac; // Spring Boot上下文
  private MockMvc mockMvc; // 伪造接口对象
  private final String mobile = "15705158033";
  private final String base = "/binding/manager/";

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void t11addCategoryTest() throws Exception {
    //        mockMvc
    //                .perform(
    //                        MockMvcRequestBuilders.get(base +
    // "mobile/"+mobile+"/verification/code")
    //                                .accept(MediaType.APPLICATION_JSON))
    //                .andExpect(MockMvcResultMatchers.status().isOk())
    ////                .andExpect(MockMvcResultMatchers.jsonPath("$.smsToken").isNotEmpty())
    //                .andDo(MockMvcResultHandlers.print());
  }
}
