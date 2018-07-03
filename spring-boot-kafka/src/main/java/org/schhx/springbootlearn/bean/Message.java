package org.schhx.springbootlearn.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author shanchao
 * @date 2018-06-07
 */
@Data
public class Message {

    private Long id;

    private String msg;

    private Date sendTime;
}
