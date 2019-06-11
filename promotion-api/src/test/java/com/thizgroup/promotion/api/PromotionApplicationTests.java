package com.thizgroup.promotion.api;

import com.thizgroup.promotion.dao.util.ConstantUtils;
import com.thizgroup.promotion.service.util.JsonUtils;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PromotionApplicationTests extends BaseTest {

  @Test
  public void T10_getVerificationCode() throws Exception {

    String resp =
        mockMvc
            .perform(
                MockMvcRequestBuilders.get(BASE_URL + "/mobile/" + MOBILE + "/verification/code")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.smsToken").isNotEmpty())
            .andReturn()
            .getResponse()
            .getContentAsString();

    JSONObject jsonObject = new JSONObject(resp);
    TOKEN = jsonObject.getJSONObject("data").getString("smsToken");
    System.out.println("##########################" + TOKEN + "##########################");
  }

  @Test
  public void T20_addRecord() throws Exception {
    PromotionApi.RecordAddingRequest requestBean =
        PromotionApi.RecordAddingRequest.builder()
            .mobile(MOBILE)
            .inviteCode("999999")
            .smsToken(TOKEN)
            .verificationCode(ConstantUtils.TEST_VERIFY_CODE)
            .build();
    String reqStr = JsonUtils.toJson(requestBean);

    String resp =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post(BASE_URL + "/record")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(reqStr))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
  }

  @Test
  public void T30_getRecords() throws Exception {
    String resp =
        mockMvc
            .perform(
                MockMvcRequestBuilders.get(BASE_URL + "/records/")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.length()").value(1))
            .andReturn()
            .getResponse()
            .getContentAsString();
  }
}
