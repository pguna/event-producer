package com.study.event_producer.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Message implements Serializable {

    private String message;
    private Date createdAt;

}
