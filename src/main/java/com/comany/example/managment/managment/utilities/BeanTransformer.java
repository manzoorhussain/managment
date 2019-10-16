package com.comany.example.managment.managment.utilities;

import com.comany.example.managment.managment.bean.UserBean;
import com.comany.example.managment.managment.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zam zam on 10/14/2019.
 */
public class BeanTransformer {

    public static UserDTO transformUserBeanToDTO(UserBean userBean) {


        UserDTO userDTO = new UserDTO();
        userDTO.setUserCode(userBean.getUserCode());
        userDTO.setLoginId(userBean.getLoginId());
        userDTO.setFirstName(userBean.getFirstName());
        userDTO.setMiddleName(userBean.getMiddleName());
        userDTO.setLastName(userBean.getLastName());
        userDTO.setAddress(userBean.getAddress());

        return userDTO;
    }

    public static List<UserDTO> transformUserBeanListToDTO(List<UserBean> userBeanList) {


        List<UserDTO> userDTOList = new ArrayList();
        if (userBeanList.size() > 0) {
            userBeanList.forEach(user -> {
                UserDTO userDTO = new UserDTO();
                userDTO.setUserCode(user.getUserCode());
                userDTO.setLoginId(user.getLoginId());
                userDTO.setFirstName(user.getFirstName());
                userDTO.setMiddleName(user.getMiddleName());
                userDTO.setLastName(user.getLastName());
                userDTO.setAddress(user.getAddress());
                userDTOList.add(userDTO);

            });


        }
        return userDTOList;
    }
}
