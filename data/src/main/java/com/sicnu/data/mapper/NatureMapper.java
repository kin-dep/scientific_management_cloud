package com.sicnu.data.mapper;

import com.sicnu.data.Nature;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NatureMapper {
    void addNature(String nature_name);

    void delNature(Integer nature_id);

    Nature selectNatureByName(String nature_name);

    List<Nature> findAllNature();
    void updateNature(Nature nature);
}
