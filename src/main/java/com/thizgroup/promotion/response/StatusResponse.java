package com.thizgroup.promotion.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 **/
@Data
@NoArgsConstructor
public class StatusResponse {
    public Integer errCode=0;
    public String errMsg = "OK";
    public StatusResponse(Integer errCode,String errMsg){
            this.errCode=errCode;
            this.errMsg=errMsg;
    }
}