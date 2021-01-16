package com.sicnu.login.mapper;

import com.sicnu.login.pojo.UserAuth;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleAuthMapper {
    void addRole(Integer role_id, Integer auth_id);

    void delAuth(Integer role_id);
    List<UserAuth> findUserAuth(Integer role_id);
}
