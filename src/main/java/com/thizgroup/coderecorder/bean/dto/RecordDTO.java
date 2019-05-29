package com.thizgroup.coderecorder.bean.dto;

import com.thizgroup.coderecorder.bean.bo.RecordBean;

import java.io.Serializable;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 **/
public class RecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    //手机号
    public String mobile;
    //邀请码
    public String inviteCode;
    //创建时间
    public Long createTime;
    public RecordDTO(RecordBean recordBean){
        this.mobile=recordBean.getMobile();
        this.inviteCode=recordBean.getInviteCode();
        this.createTime=recordBean.getCreateTime();
    }
}
