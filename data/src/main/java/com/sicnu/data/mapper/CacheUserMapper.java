package com.sicnu.data.mapper;

import com.sicnu.data.CacheUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CacheUserMapper {

    void addCacheUser(CacheUser cacheUser);

    List<CacheUser> findAllCacheUser();

    void delCacheUser();
}
