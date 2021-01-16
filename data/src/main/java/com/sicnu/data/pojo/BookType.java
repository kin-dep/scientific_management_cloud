package com.sicnu.data.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 著作
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookType {
    private Integer bt_id;
    private  String bt_name;

    public Integer getBt_id() {
        return bt_id;
    }

    public void setBt_id(Integer bt_id) {
        this.bt_id = bt_id;
    }

    public String getBt_name() {
        return bt_name;
    }

    public void setBt_name(String bt_name) {
        this.bt_name = bt_name;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "bt_id=" + bt_id +
                ", bt_name='" + bt_name + '\'' +
                '}';
    }
}
