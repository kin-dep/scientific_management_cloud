package com.sicnu.data.mapper;

import com.sicnu.data.Anounce;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface AnounceMapper {
    void addAnounce(Anounce anounce);
    void delAnounceById(Integer anounce_id);
    void updateAnounce(Anounce anounce);
    Integer countAnounce();
    List<Anounce> selectAllAnounce(Map<String, Object> map);
    Anounce selectAnounceById(Integer anounce_id);
}
