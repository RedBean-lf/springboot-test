package com.redbean.test.redis.bean;

import lombok.Data;

import java.util.Properties;

@Data
public class Movie {
    private Properties properties;
    private Integer id;
    private String name;
    private String test;
}
