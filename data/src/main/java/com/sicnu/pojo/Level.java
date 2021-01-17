package com.sicnu.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * 项目等级
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Level implements Serializable {
    private Integer level_id;
    private String level_name;
    private Integer pl_score;

    public Integer getPl_score() {
        return pl_score;
    }

    public void setPl_score(Integer pl_score) {
        this.pl_score = pl_score;
    }



    public Integer getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Integer level_id) {
        this.level_id = level_id;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    @Override
    public String toString() {
        return "Level{" +
                "level_id=" + level_id +
                ", nature_name='" + level_name + '\'' +
                '}';
    }
}
