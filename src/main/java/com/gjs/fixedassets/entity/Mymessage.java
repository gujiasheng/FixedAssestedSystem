package com.gjs.fixedassets.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Mymessage implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 消息id主键
     */
    private Integer messageId;
    /**
     * 消息名
     */
    private String messageTitle;
    /**
     * 消息类型
     */
    private Integer messageType;
    /**
     * 消息内容id
     */
    private Integer messageContent;
    /**
     * 消息提交日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date messageDate;
    /**
     * 是否阅读该消息
     */
    private Integer isNew;
    /**
     * 接收人d
     */
    private Integer receiver;
    /**
     * 发起人id
     */
    private Integer promoter;

    /**
     * 发起人信息
     */
    private User user;
}