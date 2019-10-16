package com.comany.example.managment.managment.repository;

import com.comany.example.managment.managment.bean.UserBean;
import com.comany.example.managment.managment.identities.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by zam zam on 10/12/2019.
 */


@Repository
public interface UserRepositoy extends JpaRepository<UserBean, String> {

     @Query(value = "SELECT * FROM SM_US_USER  WHERE USER_CODE=?1",nativeQuery = true)
     UserBean getUserByCode(String userCode);

     @Query(value = "SELECT * FROM SM_US_USER  WHERE LOGIN_ID=?1",nativeQuery = true)
     UserBean getUserByLoginId(String loginId);
}

