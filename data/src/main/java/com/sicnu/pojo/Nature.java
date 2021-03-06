package com.sicnu.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 项目性质
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nature {
    private Integer nature_id;
    private String nature_name;



    public Integer getNature_id() {
        return nature_id;
    }

    public void setNature_id(Integer nature_id) {
        this.nature_id = nature_id;
    }

    public String getNature_name() {
        return nature_name;
    }

    public void setNature_name(String nature_name) {
        this.nature_name = nature_name;
    }

    @Override
    public String toString() {
        return "Nature{" +
                "nature_id=" + nature_id +
                ", nature_name='" + nature_name + '\'' +
                '}';
    }
}
