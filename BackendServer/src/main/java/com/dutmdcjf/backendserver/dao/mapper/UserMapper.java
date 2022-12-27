package com.dutmdcjf.backendserver.dao.mapper;

import com.dutmdcjf.backendserver.model.Users.UserInfoDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    UserInfoDetail getUserDetail(@Param("accountIdx") Long accountIdx);

    int setUserPassword(@Param("accountIdx") Long accountIdx, @Param("newpassword") String newpassword);
}
