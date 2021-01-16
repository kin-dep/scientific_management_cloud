package com.sicnu.check.mapper;


import com.sicnu.check.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {
    User findByUserAct(String user_act);

    void addUser(User user);

    List<User> findAllUser();

    void updatePwd(String user_act, String user_pwd, String user_email, String user_number, String user_id_number);

    void changeStatus(Integer user_id, Integer user_state);

    User findUserById(Integer user_id);

    int selectUserId(String user_email);

    User findByUserName(String user_name);



    void updateUserRole(Integer role_id, Integer user_id);
    List<Integer> selectAllUserId();
    List<User> findNameId(String user_name);
    List<User> selectUserByCondition(Map<String, Object> map);
    Integer selectTotalUserByCondition(Map<String, Object> map);
    void updateUserMessage(User user);
}
