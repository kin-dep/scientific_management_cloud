package com.sicnu.data.mapper;

import com.sicnu.data.CheckEmail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
@CrossOrigin

public interface CheckEmailMapper {
    void addCheckCode(String email, Integer check_code, Date overtime);

    List<CheckEmail> findAllCode();

    CheckEmail findByCode(Integer check_code);

    void delCode(Integer check_code);
}
