package com.sicnu.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 期刊
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Periodical {
    private Integer periodical_id;
    private String periodical_name;
    private Integer pp_score;

    public Integer getPp_score() {
        return pp_score;
    }

    public void setPp_score(Integer pp_score) {
        this.pp_score = pp_score;
    }

    public Integer getPeriodical_id() {
        return periodical_id;
    }

    public void setPeriodical_id(Integer periodical_id) {
        this.periodical_id = periodical_id;
    }

    public String getPeriodical_name() {
        return periodical_name;
    }

    public void setPeriodical_name(String periodical_name) {
        this.periodical_name = periodical_name;
    }

}
