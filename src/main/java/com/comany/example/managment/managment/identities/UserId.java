package com.comany.example.managment.managment.identities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zam zam on 10/12/2019.
 */
@Embeddable
public class UserId implements Serializable{

    private String userCode;
    private String loginId;


    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
