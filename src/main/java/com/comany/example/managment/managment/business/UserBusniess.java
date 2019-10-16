package com.comany.example.managment.managment.business;

import com.comany.example.managment.managment.bean.UserBean;
import com.comany.example.managment.managment.bean.WsResponse;
import com.comany.example.managment.managment.dto.UserDTO;
import com.comany.example.managment.managment.identities.UserId;
import com.comany.example.managment.managment.repository.UserRepositoy;
import com.comany.example.managment.managment.utilities.ApplicationConstraints;
import com.comany.example.managment.managment.utilities.BeanTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zam zam on 10/13/2019.
 */
@Service
public class UserBusniess extends Busniess {


    @Autowired
    private UserRepositoy userRepositoy;

    public WsResponse getUsers() {
        List<UserBean> userBeanList = userRepositoy.findAll();
        WsResponse wsResponse = null;


        List<UserDTO> userDTOList = BeanTransformer.transformUserBeanListToDTO(userBeanList);
        if (userDTOList.size() > 0) {
            wsResponse = new WsResponse(ApplicationConstraints.SUCCESS, "Record fetch successfully", userDTOList);
        } else {
            wsResponse = new WsResponse(ApplicationConstraints.NOT_FOUND, "Record not exists", null);
        }

        return wsResponse;
    }


    @Transactional
    public WsResponse createUser(Map<String, String> map, UserRepositoy userRepositoy) {
        WsResponse wsResponse = null;


        UserBean userBean = new UserBean();
        userBean.setLoginId(map.get("loginid"));
        userBean.setFirstName(map.get("firstname"));
        userBean.setMiddleName(map.get("middlename"));
        userBean.setLastName(map.get("lastname"));
        userBean.setAddress(map.get("address"));
        UserBean user = null;
        try {
            user = userRepositoy.getUserByLoginId(userBean.getLoginId());
            if (user != null) {
                wsResponse = new WsResponse(ApplicationConstraints.SUCCESS, "Login id already exists", user);
            } else {

                user = userRepositoy.saveAndFlush(userBean);

                if (user != null) {
                    wsResponse = new WsResponse(ApplicationConstraints.SUCCESS, "Record save successfully", user);
                } else {
                    wsResponse = new WsResponse(ApplicationConstraints.FAILED, "Record not save ", user);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            wsResponse = new WsResponse(ApplicationConstraints.DB_ERROR, "Technical error contact to administrator", null);
        }

        return wsResponse;
    }


    public WsResponse getUserByCode(Map<String, String> map, UserRepositoy userRepositoy) {
        WsResponse wsResponse = null;
        UserDTO userDTO = null;

        String userCode = map.get("usercode");

        try {
            UserBean user = userRepositoy.getUserByCode(userCode);

            if (user != null) {
                userDTO = BeanTransformer.transformUserBeanToDTO(user);
                wsResponse = new WsResponse(ApplicationConstraints.SUCCESS, "Fetch user successfully", userDTO);
            } else {
                wsResponse = new WsResponse(ApplicationConstraints.FAILED, "User not exits", userDTO);
            }
        } catch (Exception e) {
            wsResponse = new WsResponse(ApplicationConstraints.DB_ERROR, "Technical error contact to administrator", null);
        }
        return wsResponse;
    }


    @Transactional
    public WsResponse removeUser(Map<String, String> map, UserRepositoy userRepositoy) {
        WsResponse wsResponse = null;
        UserDTO userDTO = null;

        String userCode = map.get("usercode");
        try {
            UserBean user = userRepositoy.getUserByCode(userCode);
            userDTO = BeanTransformer.transformUserBeanToDTO(user);
            if (user == null) {
                wsResponse = new WsResponse(ApplicationConstraints.FAILED, "User not exits", userDTO);
            } else {
                userRepositoy.deleteById(userCode);
                wsResponse = new WsResponse(ApplicationConstraints.SUCCESS, "Delete user successfully", userDTO);

            }

        } catch (Exception e) {
            e.printStackTrace();
            wsResponse = new WsResponse(ApplicationConstraints.DB_ERROR, "Technical error contact to administrator", null);
        }
        return wsResponse;
    }

    @Override
    public WsResponse applyBusniess(Map<String, String> map, String callFrom) {
        WsResponse wsResponse = null;
        switch (callFrom) {
            case ApplicationConstraints.CREATE_USER:
                wsResponse = createUser(map, userRepositoy);
                break;

            case ApplicationConstraints.GETUSER_BY_CODE:
                wsResponse = getUserByCode(map, userRepositoy);
                break;

            case ApplicationConstraints.DELETE_USER:
                wsResponse = removeUser(map, userRepositoy);
                break;
        }
        return wsResponse;
    }
}
