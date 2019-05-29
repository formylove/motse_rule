package com.thizgroup.promotion.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 **/
@Data
@Entity
@Table(name = "jfa_invitecode_record")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class RecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            unique = true,
            nullable = false,
            updatable = false,
            length = 64,
            columnDefinition = "CHAR(64)")
    private Integer id;
    //手机号
    @Column(name = "mobile", nullable = false, columnDefinition = "VARCHAR(20)")
    private String mobile;
    //邀请码
    @Column(name = "invite_code", nullable = false, columnDefinition = "VARCHAR(8)")
    private String inviteCode;
    @CreatedDate
    @Column(name = "creation_time", nullable = false, columnDefinition = "BIGINT")
    private Long createTime;

    @Builder
public RecordEntity(String mobile,String inviteCode){
    this.mobile=mobile;
    this.inviteCode=inviteCode;
}
}
