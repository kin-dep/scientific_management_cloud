package com.sicnu.check.mapper;


import com.sicnu.check.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PaperMapper {
    void addPaper(Paper paper);

    Paper selectPaperByNumber(String include_number);

    List<Paper> selectPaperByCondition(Map<String, Object> map);

    void updatePaper(Paper paper);

    void delPaperById(Integer paper_id);

    Integer selectTotalPaper(Map<String, Object> map);
    Integer selectCountPaper(Map<String, Object> map);
    List<Paper> findPaperByLeaderId(Integer leader_id);
    Integer selectPaperId(Integer leader_id,String paper_name);
    Paper findPaperById(Integer paper_id);
    Integer countPaper();
}
